package esipe.fr.tp1.fragements;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import esipe.fr.tp1.R;
import esipe.fr.tp1.beans.Client;

public class ListClientActivity extends ListFragment {

    public static final String TAG = "HOLLA";
    public static List<Client> clients;
    private onClickClientSelectedListener listener;

    static {
        clients = new ArrayList<Client>();
        for(int i = 0; i<20; i++){
            clients.add(new Client("zouhair" + new Random().nextInt(100), "HAJJI" + new Random().nextInt(100), false));
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: " + clients.size());
        ArrayAdapter<Client> adapter = new ArrayAdapter<Client>(this.getActivity(), android.R.layout.simple_list_item_1, clients);
        setListAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(getActivity() instanceof onClickClientSelectedListener){
            listener = (onClickClientSelectedListener) getActivity();
        }
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        this.listener.onClickSelected(position);
    }


    public interface onClickClientSelectedListener {
        void onClickSelected(int id);
    }
}
