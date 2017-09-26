package br.com.glauber.chat.modelo;

/**
 * Created by Glauber on 26/09/2017.
 */

public class Mensagem {

    private int id;
    private String texto;

    public Mensagem(int id, String mensagem) {
        this.id = id;
        this.texto = mensagem;
    }

    public String getText() {
        return texto;
    }

    public int getId() {
        return id;
    }
}
