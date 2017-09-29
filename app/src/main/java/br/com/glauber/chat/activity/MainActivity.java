package br.com.glauber.chat.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.glauber.chat.R;
import br.com.glauber.chat.adapter.MensagemAdapter;
import br.com.glauber.chat.app.ChatApplication;
import br.com.glauber.chat.callback.EnviarMensagensCallback;
import br.com.glauber.chat.callback.OuvirMensagensCallback;
import br.com.glauber.chat.component.ChatComponent;
import br.com.glauber.chat.modelo.Mensagem;
import br.com.glauber.chat.service.ChatService;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private int idDoCliente = 1;

    private Button buttonEnviar;
    private ListView listaDeMensagens;
    private List<Mensagem> mensagens;
    private MensagemAdapter adapter;

    @Inject public ChatService chatService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaDeMensagens = (ListView) findViewById(R.id.listview_listaDeMensagens);
        mensagens = new ArrayList<>();
        adapter = new MensagemAdapter(idDoCliente, mensagens, this);
        listaDeMensagens.setAdapter(adapter);

        final EditText editTextMensagem = (EditText) findViewById(R.id.editText_mensagem);

        ChatApplication app = (ChatApplication) getApplication();
        ChatComponent component = app.getComponent();
        component.inject(this);

        chatService.callOuvirMensagens();

        ouvirMensagens();

        buttonEnviar = (Button) findViewById(R.id.button_enviar);
        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatService.enviar(new Mensagem(idDoCliente, editTextMensagem.getText().toString()))
                        .enqueue(new EnviarMensagensCallback());
                Toast.makeText(MainActivity.this, "Enviando...", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void colocaNaLista(Mensagem mensagem) {
        mensagens.add(mensagem);
        adapter = new MensagemAdapter(idDoCliente, mensagens, this);
        listaDeMensagens.setAdapter(adapter);
        chatService.callOuvirMensagens();
        ouvirMensagens();
    }

    public void ouvirMensagens() {
        Call<Mensagem> call = chatService.callOuvirMensagens();
        call.enqueue(new OuvirMensagensCallback(this));
    }
}
