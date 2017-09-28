package br.com.glauber.chat.callback;

import br.com.glauber.chat.activity.MainActivity;
import br.com.glauber.chat.modelo.Mensagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Glauber on 28/09/2017.
 */

public class OuvirMensagensCallback implements Callback<Mensagem> {

    private MainActivity activity;

    public OuvirMensagensCallback(MainActivity activity) {
        this.activity = activity;
    }


    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response) {
        if (response.isSuccessful()) {
            Mensagem mensagem = response.body();
            activity.colocaNaLista(mensagem);

        }
    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable t) {
        activity.ouvirMensagens();
    }
}
