package br.com.glauber.chat.app;

import android.app.Application;

import br.com.glauber.chat.component.ChatComponent;
import br.com.glauber.chat.component.DaggerChatComponent;

/**
 * Created by Glauber on 29/09/2017.
 */

public class ChatApplication extends Application {

    private ChatComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerChatComponent.builder().build();
    }

    public ChatComponent getComponent() {
        return component;
    }
}
