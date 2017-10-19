package esipe.fr.tp1.fragements;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import esipe.fr.tp1.R;
import esipe.fr.tp1.beans.Client;

public class DetailClient extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_detail_client, container, true);

        return view;
    }

    public void updateClient(int id) {
        TextView tvFirstName = (TextView) getActivity().findViewById(R.id.tvFirstName);
        TextView tvLastName = (TextView) getActivity().findViewById(R.id.tvLastName);
        TextView tvAge = (TextView) getActivity().findViewById(R.id.tvAge);

        Client client = ListClientActivity.clients.get(id);

        tvFirstName.setText(client.getFirstName());
        tvLastName.setText(client.getLastName());
        tvAge.setText(client.getBidrthday().toString());
    }
}
