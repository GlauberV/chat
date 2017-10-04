package br.com.glauber.chat.service;

import br.com.glauber.chat.activity.MainActivity;
import br.com.glauber.chat.modelo.Mensagem;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Glauber on 26/09/2017.
 */

public interface ChatService {

    void ChatService(MainActivity activity);

    @POST("polling")
    Call<Void> enviar(@Body Mensagem mensagem);

    @GET("polling")
    Call<Mensagem> pegarMensagem();

}
