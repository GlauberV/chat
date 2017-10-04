package br.com.glauber.chat.module;

import android.app.Application;
import android.view.inputmethod.InputMethodManager;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import br.com.glauber.chat.service.ChatService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.INPUT_METHOD_SERVICE;

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

    @Provides
    public EventBus getEventBus(){
        EventBus eventBus = EventBus.builder().build();
        return eventBus;
    }

    @Provides
    public InputMethodManager getInputMethodManager(){
        InputMethodManager inputMethodManager =
                (InputMethodManager) app.getSystemService(INPUT_METHOD_SERVICE);

        return inputMethodManager;
    }
}
