package fr.esipe.creteil.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.esipe.creteil.activities.HomeActivity;
import fr.esipe.creteil.fragements.DetailsClientFragment;
import fr.esipe.ing3.android.clientapp.R;

public class DetailsClientActivity extends AppCompatActivity {

    public final static String EXTRA_ID_CLIENT = "ID_CLIENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_client);
        int id = getIntent().getIntExtra(EXTRA_ID_CLIENT, 0);
        DetailsClientFragment fragment = (DetailsClientFragment) getSupportFragmentManager().findFragmentById(R.id.details_client_fragment);
        fragment.updateClient(id);
    }
}
