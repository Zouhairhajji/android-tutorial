package fr.esipe.ing3.android.clientapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by antho on 05/10/2017.
 */

public class ClientAdapter extends ArrayAdapter<Client> {
    public ClientAdapter(Context context, List<Client> clients) {
        super(context,0,clients);
    }


    public View getView(int position, View view, ViewGroup parent) {
        if(view== null) {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view= inflater.inflate(R.layout.client_entry, parent, false);
        }
        Client c = getItem(position);

        TextView email = (TextView) view.findViewById(R.id.emailClient);
        email.setText(c.getId());

        TextView nom = (TextView) view.findViewById(R.id.nomClient);
        nom.setText(c.getLastname());

        TextView prenom = (TextView) view.findViewById(R.id.prenomClient);
        prenom.setText(c.getFirstname());



       /* TextView age = (TextView) view.findViewById(R.id.ageClient);
        age.setText(String.valueOf(c.getAge()));*/

       /* TextView genre = (TextView) view.findViewById(R.id.genderClient);
        genre.setText(c.getGender().toString());

        TextView niveau = (TextView) view.findViewById(R.id.levelClient);
       niveau.setText(c.getLevel()); */


        return view;
    }

}
