package fr.esipe.creteil.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fr.esipe.creteil.beans.Client;
import fr.esipe.ing3.android.clientapp.R;

/**
 * Created by antho on 05/10/2017.
 */

public class ClientAdapter extends ArrayAdapter<Client> implements SharedPreferences.OnSharedPreferenceChangeListener{

    private SharedPreferences defaultSharedPreferences;
    private String naming_mode;

    public ClientAdapter(Context context, List<Client> clients) {
        super(context, 0, clients);
        this.defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        this.defaultSharedPreferences.registerOnSharedPreferenceChangeListener(this);

    }


    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.client_entry, parent, false);
        }
        Client c = getItem(position);

        TextView email = (TextView) view.findViewById(R.id.emailClient);
        email.setText(c.getId());

        TextView nom = (TextView) view.findViewById(R.id.nomClient);
        nom.setText(c.getLastname());

        TextView prenom = (TextView) view.findViewById(R.id.prenomClient);
        prenom.setText(c.getFirstname());

        return view;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equalsIgnoreCase("naming_mode_preference")){
            this.naming_mode = this.defaultSharedPreferences.getString("naming_mode_preference", "PRENOM_NOM");
        }
    }
}
