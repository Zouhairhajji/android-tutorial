package fr.esipe.ing3.android.clientapp;

import android.app.ListActivity;
import android.app.ListFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListClientFragment  extends ListFragment {

    private static final String TAG = "log" ;
    private ClientAdapter ca;

    public interface OnClientSelectedListener {

        void onClientSelect(int id);
    }
    private OnClientSelectedListener listener;

    public static final String ID_CLIENT = "idClient";

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ca.notifyDataSetChanged();
        }
    };

    /*public static List<Client> clients = new ArrayList<>();*/

   @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
       /* super.onListItemClick(l, v, position, id);
       Intent intent = new Intent(getActivity(),DetailsClientActivity.class);
        intent.putExtra(ID_CLIENT,position);
        startActivity(intent); */
       if(listener != null) {
           listener.onClientSelect(position);
       }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_details_client);
        List<Client> clients = Client.getClients();
        ca = new ClientAdapter(getActivity(),clients);
        //ArrayAdapter<Client> adapter = new ArrayAdapter<Client>(this,android.R.layout.simple_list_item_1,clients);
        setListAdapter(ca);
        setHasOptionsMenu(true);
        IntentFilter filter = new IntentFilter(AddClientActivity.ADD_CLIENT_ACTION);
        getActivity().registerReceiver(receiver,filter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       // if (getActivity().instanceOf OnClientSelectedListener)
        listener = (OnClientSelectedListener) getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.list_client,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_add_client){
           Intent intent= new Intent(getActivity(),AddClientActivity.class);
            startActivity(intent);
            return true;

        }
        return super.onOptionsItemSelected(item);
    }



    }

