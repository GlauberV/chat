package br.com.glauber.chat.event;

import br.com.glauber.chat.modelo.Mensagem;

/**
 * Created by Glauber on 03/10/2017.
 */

public class MensagemEvent {

    private Mensagem mensagem;

    public MensagemEvent() {

    }

    public MensagemEvent(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

    public Mensagem getMensagem(){
        return mensagem;
    }
}
