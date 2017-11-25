package fr.esipe.creteil.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.esipe.creteil.listeners.OnClientSelectedListener;
import fr.esipe.creteil.fragements.DetailsClientFragment;
import fr.esipe.ing3.android.clientapp.R;



public class HomeActivity extends AppCompatActivity implements OnClientSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeactivity);
    }

    @Override
    public void onClientSelect(int id) {
        if(findViewById(R.id.details_client_fragment) == null){
            Intent intent = new Intent(this, DetailsClientActivity.class);

            intent.putExtra(DetailsClientActivity.EXTRA_ID_CLIENT,id);

            startActivity(intent);

        }
        else{
            DetailsClientFragment fragment= (DetailsClientFragment) getSupportFragmentManager().findFragmentById(R.id.details_client_fragment);
            fragment.updateClient(id);
        }

    }
}
