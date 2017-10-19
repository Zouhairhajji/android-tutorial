package esipe.fr.tp1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import esipe.fr.tp1.R;
import esipe.fr.tp1.fragements.DetailClient;
import esipe.fr.tp1.fragements.ListClientActivity;

/**
 * Created by zouhairhajji on 05/10/2017.
 */

public class DashBoardActivity extends AppCompatActivity implements ListClientActivity.onClickClientSelectedListener {


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.dashboard_main);
    }


    @Override
    public void onClickSelected(int id) {

        if (findViewById(R.id.detail_client_fragments) == null) {
            // mode landscape
            Intent intent = new Intent(this, ClientDetailActivity.class);
            intent.putExtra("id_client", id);
            startActivity(intent);
        } else {
            // mode portrait
            DetailClient fragement = (DetailClient) getSupportFragmentManager().findFragmentById(R.id.detail_client_fragments);
            fragement.updateClient(id);
        }
    }
}
