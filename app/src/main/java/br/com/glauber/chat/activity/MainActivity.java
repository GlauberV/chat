package br.com.glauber.chat.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

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
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private int idDoCliente = 1;

    @BindView(R.id.Button_enviar) Button buttonEnviar;
    @BindView(R.id.EditText_mensagem) EditText editTextMensagem;
    @BindView(R.id.ListView_listaDeMensagens) ListView listaDeMensagens;
    @BindView(R.id.ImageView_imagem) ImageView avatar;

    private List<Mensagem> mensagens;
    private MensagemAdapter adapter;

    @Inject public ChatService chatService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mensagens = new ArrayList<>();
        adapter = new MensagemAdapter(idDoCliente, mensagens, this);
        listaDeMensagens.setAdapter(adapter);

        ChatApplication app = (ChatApplication) getApplication();
        ChatComponent component = app.getComponent();
        component.inject(this);

        Picasso.with(this)
                .load("https://api.adorable.io/avatars/285/" + idDoCliente +".png")
                .into(avatar);

        chatService.callOuvirMensagens();
        ouvirMensagens();

    }

    @OnClick(R.id.Button_enviar)
    public void enviarMensagem(){
        chatService.enviar(new Mensagem(idDoCliente, editTextMensagem.getText().toString()))
                .enqueue(new EnviarMensagensCallback());
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
