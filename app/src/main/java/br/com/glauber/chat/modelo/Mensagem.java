package br.com.glauber.chat.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Glauber on 26/09/2017.
 */

public class Mensagem implements Serializable{

    private int id;
    @SerializedName("text") private String texto;

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

    @Override
    public String toString() {
        return texto;
    }
}
