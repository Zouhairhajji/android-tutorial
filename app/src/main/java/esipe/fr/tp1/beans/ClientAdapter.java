package esipe.fr.tp1.beans;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import esipe.fr.tp1.R;

/**
 * Created by zouhairhajji on 05/10/2017.
 */

public class ClientAdapter extends ArrayAdapter<Client> {


    public ClientAdapter(@NonNull Context context, @NonNull List<Client> clients) {
        super(context, 0, clients);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Client client = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.client_row, parent, false);
        }

        TextView pseudo = (TextView) convertView.findViewById(R.id.pseudo);
        TextView text =(TextView) convertView.findViewById(R.id.text);
        ImageView avatar = (ImageView) convertView.findViewById(R.id.avatar);

        pseudo.setText(client.getFirstName() + " " + client.getLastName());
        avatar.setImageDrawable(new ColorDrawable(client.isSexe() ? Color.BLUE : Color.CYAN));
        return convertView;
    }
}
