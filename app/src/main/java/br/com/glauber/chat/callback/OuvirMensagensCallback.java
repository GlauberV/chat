package br.com.glauber.chat.callback;

import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import br.com.glauber.chat.event.FailMensagemEvent;
import br.com.glauber.chat.event.MensagemEvent;
import br.com.glauber.chat.modelo.Mensagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Glauber on 28/09/2017.
 */

public class OuvirMensagensCallback implements Callback<Mensagem> {

    private Context context;
    private EventBus eventBus;

    public OuvirMensagensCallback(EventBus eventBus, Context context) {
        this.context = context;
        this.eventBus = eventBus;
    }


    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response) {
        if (response.isSuccessful()) {
            Mensagem mensagem = response.body();

            eventBus.post(new MensagemEvent(mensagem));
            Log.e("OMC success", "Evento disparado! Mensagem: " + mensagem.getText().toString());
        }
    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable t) {
        eventBus.post(new FailMensagemEvent());
        Log.i("OMC error", t.getMessage().toString());
    }
}
