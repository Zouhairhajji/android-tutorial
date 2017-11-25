package fr.esipe.creteil.configs;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zouhairhajji on 25/11/2017.
 */

public class RetrofitAPI {


    private static HttpLoggingInterceptor LOGGING_INTERCEPTOR;

    private static Retrofit RETROFIT;

    private static OkHttpClient.Builder OK_HTTP_CLIENT_BUILDER;

    public static void initService() {
        LOGGING_INTERCEPTOR = new HttpLoggingInterceptor();

        LOGGING_INTERCEPTOR.setLevel(HttpLoggingInterceptor.Level.BODY);

        OK_HTTP_CLIENT_BUILDER = new OkHttpClient.Builder();
        OK_HTTP_CLIENT_BUILDER.addInterceptor(LOGGING_INTERCEPTOR);


        OkHttpClient okHttpClient = OK_HTTP_CLIENT_BUILDER.build();

        RETROFIT = new Retrofit.Builder()
                .baseUrl("http://ama-gestion-clients.appspot.com")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static <T> T createService(Class<T> clazz) {
        if(RETROFIT == null){
            initService();
        }
        return RETROFIT.create(clazz);
    }

}
