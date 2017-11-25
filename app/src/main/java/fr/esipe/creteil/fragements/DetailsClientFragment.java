package fr.esipe.creteil.fragements;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Parcelable;
import android.provider.MediaStore;
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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import fr.esipe.creteil.beans.Client;
import fr.esipe.creteil.activities.AddClientActivity;
import fr.esipe.ing3.android.clientapp.R;

public class DetailsClientFragment extends Fragment{

    public static final int PICTURE_REQUEST_CODE = 111;
    private TextView textView;
    private TextView birthDaTextView;
    private ImageView client_picture_imageview;
    private Client client;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_client, container, true);
        this.textView = (TextView) view.findViewById(R.id.lastnametw);
        this.client_picture_imageview = (ImageView) view.findViewById(R.id.client_picture_imageview);
        this.client_picture_imageview.setOnClickListener(new OnClickImageViewChange());
        setHasOptionsMenu(true);
        return view;

    }

    public void updateClient(int id) {
        client = Client.getClients().get(id);
        String lastname = client.getLastname();
        textView.setText(lastname);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.details_client, menu);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICTURE_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            Bitmap bitmap = data.getParcelableExtra("data");
            this.client_picture_imageview.setImageBitmap(bitmap);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_delete) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Confirmation");
            builder.setMessage("Voulez vous vraiment supprimer ?");
            builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Client.getClients().remove(client);
                    getActivity().sendBroadcast(new Intent(AddClientActivity.EXTRA_CLIENT_ADDED));
                    // dialog.dismiss();
                }
            });
            builder.show();
        }
        return super.onOptionsItemSelected(item);
    }

    public class OnClickImageViewChange implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, PICTURE_REQUEST_CODE);
        }
    }

}
