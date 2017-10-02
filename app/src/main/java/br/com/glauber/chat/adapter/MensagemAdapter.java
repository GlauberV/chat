package br.com.glauber.chat.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.glauber.chat.R;
import br.com.glauber.chat.modelo.Mensagem;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Glauber on 26/09/2017.
 */

public class MensagemAdapter extends BaseAdapter {

    private List<Mensagem> mensagem;
    private Activity activity;
    private int idDoCliente;

    @BindView(R.id.TextView_texto) TextView texto;
    @BindView(R.id.ImageView_avatar_usuario) ImageView avatar;

    public MensagemAdapter(int idDoCliente, List<Mensagem> mensagem, Activity activity) {
        this.mensagem = mensagem;
        this.activity = activity;
        this.idDoCliente = idDoCliente;
    }

    @Override
    public int getCount() {
        return mensagem.size();
    }

    @Override
    public Mensagem getItem(int position) {
        return mensagem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View linha = activity.getLayoutInflater().inflate(R.layout.mensagem, parent, false);

        ButterKnife.bind(this, linha);

        Mensagem mensagem = getItem(position);
        if (idDoCliente != mensagem.getId()) linha.setBackgroundColor(Color.CYAN);
        texto.setText(mensagem.getText());

        int idDaMensagem = mensagem.getId();
        Picasso.with(activity)
                .load("https://api.adorable.io/avatars/285/" + idDaMensagem + ".png")
                .into(avatar);

        return linha;
    }
}
