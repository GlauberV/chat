package br.com.glauber.chat.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import br.com.glauber.chat.R;
import br.com.glauber.chat.adapter.MensagemAdapter;
import br.com.glauber.chat.modelo.Mensagem;

public class MainActivity extends AppCompatActivity {

    private int idDoCliente = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listaDeMensagens = (ListView) findViewById(R.id.listview_listaDeMensagens);
        List<Mensagem> mensagems = Arrays.asList(new Mensagem(1, "Saudações Androideiros"),
                new Mensagem(2, "Saudações professor"),
                new Mensagem(1, "Alguém falou com vocês?"));
        MensagemAdapter adapter = new MensagemAdapter(idDoCliente, mensagems, this);
        listaDeMensagens.setAdapter(adapter);

    }
}
