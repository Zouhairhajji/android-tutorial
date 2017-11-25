package fr.esipe.creteil.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import fr.esipe.creteil.activities.AddClientActivity;
import fr.esipe.creteil.activities.HomeActivity;
import fr.esipe.creteil.beans.Client;
import fr.esipe.creteil.configs.RetrofitAPI;
import fr.esipe.creteil.retrofitInterfaces.ClientRestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SynchroService extends IntentService {


    public static final String SYNCHRO_SERVICE_NAME = "synchroService";
    private ClientRestService clientRestService;
    private NotificationManager notificationManager;

    public SynchroService() {
        super(SYNCHRO_SERVICE_NAME);
        this.clientRestService = RetrofitAPI.createService(ClientRestService.class);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        final Notification notification = this.getNotification("synchronisation en cours");
        this.notificationManager.notify(1, notification);


        Call<List<Client>> callClients = this.clientRestService.getAllClients();
        callClients.enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                List<Client> clients = response.body();
                Client.setClients(clients);
                sendBroadcast(new Intent(AddClientActivity.EXTRA_CLIENT_ADDED));
                final Notification notification = getNotification("synchronisation terminée");
                notificationManager.notify(1, notification);
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                Toast.makeText(SynchroService.this, "Erreur de Synchro", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public Notification getNotification(String message){
        // Sets an ID for the notification
        int mNotificationId = 001;
        NotificationCompat.Builder notif = new NotificationCompat.Builder(this);
        notif.setContentTitle("Synchronisation des clients");
        notif.setContentText(message);
        notif.setWhen(System.currentTimeMillis());
        notif.setSmallIcon(android.R.drawable.stat_notify_sync);

        Intent[] intents = {new Intent(this, HomeActivity.class)};

        notif.setContentIntent(PendingIntent.getActivities(this, 1, intents, 0));
        return notif.build();
    }
}
