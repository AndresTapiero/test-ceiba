package co.com.ceiba.mobile.pruebadeingreso.base;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseApplication extends Application {

    private static BaseApplication mInstance;
    public static Retrofit retrofit = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        realConfig();
    }

    public static synchronized BaseApplication getInstance() {return mInstance;}

    private void realConfig() {
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("realm.ceiba")
                //.name("myrealm.ceiba")
                .schemaVersion(3)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static Retrofit getRetrofitClient() {
        if (retrofit == null) {
            okhttp3.OkHttpClient client = new okhttp3.OkHttpClient.Builder().build();
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .baseUrl(Endpoints.URL_BASE)
                    .build();
        }
        return retrofit;
    }
}
