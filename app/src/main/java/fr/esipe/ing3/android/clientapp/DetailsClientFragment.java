package fr.esipe.ing3.android.clientapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailsClientFragment extends Fragment {

    private TextView textView;
    private TextView birthDaTextView;
    private Client client;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_client,container,true);
       textView = (TextView) view.findViewById(R.id.lastnametw);
      //  birthDaTextView=(TextView) view.findViewById(R.id.birthdatetw);
setHasOptionsMenu(true);
        return view;

    }

    public void updateClient(int id){
        client = Client.getClients().get(id);
        String lastname = client.getLastname();
        textView.setText(lastname);
      /*  Date birthdate = c.getBirthDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        birthDaTextView.setText(simpleDateFormat.format(birthdate));
*/
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.details_client,menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_delete){
            AlertDialog.Builder builder =  new AlertDialog.Builder(getActivity());
            builder.setTitle("Confirmation");
            builder.setMessage("Voulez vous vraiment supprimer ?");
            builder.setNegativeButton("Non",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        builder.setPositiveButton("Oui",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Client.getClients().remove(client);
            getActivity().sendBroadcast(new Intent(AddClientActivity.ADD_CLIENT_ACTION));
               // dialog.dismiss();
            }
        });
            builder.show();
        }
        return super.onOptionsItemSelected(item);
    }

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_client);


        Intent intent = getIntent();
        int id = intent.getIntExtra(ListClientFragment.ID_CLIENT,0);
        Client client = Client.getClients().get(id);
        TextView tw = (TextView) findViewById(R.id.tw);

        tw.setText(client.getLastname());
    }*/
}
