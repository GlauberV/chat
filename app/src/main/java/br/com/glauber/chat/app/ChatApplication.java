package br.com.glauber.chat.app;

import android.app.Application;

import br.com.glauber.chat.component.ChatComponent;
import br.com.glauber.chat.component.DaggerChatComponent;
import br.com.glauber.chat.module.ChatModule;

/**
 * Created by Glauber on 29/09/2017.
 */

public class ChatApplication extends Application {

    private ChatComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerChatComponent.builder()
                .chatModule(new ChatModule(this))
                .build();
    }

    public ChatComponent getComponent() {
        return component;
    }
}
