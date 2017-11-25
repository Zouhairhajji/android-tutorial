package fr.esipe.creteil.retrofitInterfaces;

import java.util.List;

import fr.esipe.creteil.beans.Client;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;

/**
 * Created by zouhairhajji on 25/11/2017.
 */

public interface ClientRestService {


    @GET("/rest/client")
    public Call<List<Client>> getAllClients();


    @POST("/rest/client")
    public Call<Void> addClient(@Body  Client client);
}
