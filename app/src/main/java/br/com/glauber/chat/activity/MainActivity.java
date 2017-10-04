package br.com.glauber.chat.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.glauber.chat.R;
import br.com.glauber.chat.adapter.MensagemAdapter;
import br.com.glauber.chat.app.ChatApplication;
import br.com.glauber.chat.callback.EnviarMensagensCallback;
import br.com.glauber.chat.callback.OuvirMensagensCallback;
import br.com.glauber.chat.component.ChatComponent;
import br.com.glauber.chat.event.FailMensagemEvent;
import br.com.glauber.chat.event.MensagemEvent;
import br.com.glauber.chat.modelo.Mensagem;
import br.com.glauber.chat.service.ChatService;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    public static final String BUNDLE_MENSAGENS_KEY = "mensagens";
    private int idDoCliente = 1;

    @BindView(R.id.Button_enviar)
    Button buttonEnviar;
    @BindView(R.id.EditText_mensagem)
    EditText editTextMensagem;
    @BindView(R.id.ListView_listaDeMensagens)
    ListView listaDeMensagens;
    @BindView(R.id.ImageView_imagem)
    ImageView avatar;

    private List<Mensagem> mensagens;
    private MensagemAdapter adapter;

    @Inject
    public ChatService chatService;
    @Inject
    public EventBus eventBus;
    @Inject
    public InputMethodManager inputMethodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChatApplication app = (ChatApplication) getApplication();
        ChatComponent component = app.getComponent();
        component.inject(this);

        ButterKnife.bind(this);
        eventBus.register(this);

        if (savedInstanceState != null) {
            mensagens = (List<Mensagem>) savedInstanceState.getSerializable(BUNDLE_MENSAGENS_KEY);
        } else {
            mensagens = new ArrayList<>();
        }

        adapter = new MensagemAdapter(idDoCliente, mensagens, this);
        listaDeMensagens.setAdapter(adapter);

        Picasso.with(this)
                .load("https://api.adorable.io/avatars/285/" + idDoCliente + ".png")
                .into(avatar);

        ouvirMensagens(new MensagemEvent());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(BUNDLE_MENSAGENS_KEY, (ArrayList<Mensagem>) mensagens);
    }

    @Override
    protected void onStop() {
        super.onStop();

        eventBus.unregister(this);
    }

    @OnClick(R.id.Button_enviar)
    public void enviarMensagem() {
        chatService.enviar(new Mensagem(idDoCliente, editTextMensagem.getText().toString()))
                .enqueue(new EnviarMensagensCallback());

        editTextMensagem.getText().clear();

        inputMethodManager.hideSoftInputFromInputMethod(editTextMensagem.getWindowToken(), 0);

        Log.e("EnviarMensagem", "passou aqui, Mensagem: " + editTextMensagem.getText().toString());
    }

    @Subscribe
    public void colocaNaLista(MensagemEvent mensagemEvent) {
        mensagens.add(mensagemEvent.getMensagem());

        adapter = new MensagemAdapter(idDoCliente, mensagens, this);
        listaDeMensagens.setAdapter(adapter);

        Log.e("ColocaNaLista", "passou aqui, Mensagem: " + mensagemEvent.getMensagem().toString());
    }

    @Subscribe
    public void ouvirMensagens(MensagemEvent mensagemEvent) {
        Call<Mensagem> call = chatService.pegarMensagem();
        call.enqueue(new OuvirMensagensCallback(eventBus, this));

        Log.e("OuvirMensagem", "passou aqui");
    }

    @Subscribe
    public void onFailMensagemEvent(FailMensagemEvent failMensagemEvent) {
        ouvirMensagens(null);
    }
}
