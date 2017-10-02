package br.com.glauber.chat.component;

import br.com.glauber.chat.activity.MainActivity;
import br.com.glauber.chat.adapter.MensagemAdapter;
import br.com.glauber.chat.module.ChatModule;
import dagger.Component;

/**
 * Created by Glauber on 29/09/2017.
 */

@Component(modules = ChatModule.class)
public interface ChatComponent {

    void inject(MainActivity activity);
    void inject(MensagemAdapter adapter);

}
