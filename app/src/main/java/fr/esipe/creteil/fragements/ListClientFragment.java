package fr.esipe.creteil.fragements;

import android.app.ListFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import fr.esipe.creteil.activities.SettingActivity;
import fr.esipe.creteil.adapters.ClientAdapter;
import fr.esipe.creteil.beans.Client;
import fr.esipe.creteil.listeners.OnClientSelectedListener;
import fr.esipe.creteil.activities.AddClientActivity;
import fr.esipe.ing3.android.clientapp.R;


public class ListClientFragment extends ListFragment {

    private static final String TAG = "log";
    private ClientAdapter ca;

    private OnClientSelectedListener listener;

    public static final String ID_CLIENT = "idClient";

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ca.notifyDataSetChanged();
        }
    };



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.d(TAG, "onListItemClick: clicked" + listener);

        if (listener != null) {
            listener.onClientSelect(position);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create adapter in order to list all users
        ca = new ClientAdapter(getActivity(), Client.getClients());
        setListAdapter(ca);

        // enable top menu
        setHasOptionsMenu(true);


        // get receiver only for the event (EXTRA_CLIENT_ADDED)
        IntentFilter filter = new IntentFilter(AddClientActivity.EXTRA_CLIENT_ADDED);
        // register receiver
        getActivity().registerReceiver(receiver, filter);

        // get the current activity
        // this variable help us to notify homeactivity (customer added, customer deleted ...)
        listener = (OnClientSelectedListener) getActivity();
    }



    /**
     * this method is executed when the activity is destroyed
     * this method delete of listener for exemple ( receivers )
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }

    /**
     *  add the menu ( list_client ) on the package res/menu/list_client
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.list_client, menu);
    }


    /**
     *
     * verify if we clicked on add customer on the top of the activity
     *
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_client) {
            Intent intent = new Intent(getActivity(), AddClientActivity.class);
            startActivity(intent);
            return true;

        } else if (item.getItemId() == R.id.action_preferences) {
            Intent intent = new Intent(getActivity(), SettingActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}

