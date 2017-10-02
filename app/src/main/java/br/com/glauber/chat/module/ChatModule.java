package br.com.glauber.chat.module;

import android.app.Application;

import com.squareup.picasso.Picasso;

import br.com.glauber.chat.service.ChatService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Glauber on 29/09/2017.
 */

@Module
public class ChatModule {

    private Application app;

    public ChatModule(Application app){
        this.app = app;
    }

    @Provides
    public ChatService getChatService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.3:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChatService chatService = retrofit.create(ChatService.class);
        return chatService;
    }

    @Provides
    public Picasso picasso(){
        Picasso picasso = new Picasso.Builder(app).build();
        return picasso;
    }
}
