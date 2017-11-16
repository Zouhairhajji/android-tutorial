package fr.esipe.ing3.android.clientapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity implements ListClientFragment.OnClientSelectedListener {

    public static final String ID_CLIENT = "ID_CLIENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeactivity);


    }

    @Override
    public void onClientSelect(int id) {

        if(findViewById(R.id.details_client_fragment) == null){
            Intent intent = new Intent(this,DetailsClientActivity.class);
            intent.putExtra(ID_CLIENT,id);
            startActivity(intent);

        }
        else{
            DetailsClientFragment fragment= (DetailsClientFragment) getSupportFragmentManager().findFragmentById(R.id.details_client_fragment);
            fragment.updateClient(id);
        }

    }
}
