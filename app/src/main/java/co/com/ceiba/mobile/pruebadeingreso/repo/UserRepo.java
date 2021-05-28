package co.com.ceiba.mobile.pruebadeingreso.repo;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import java.util.List;
import co.com.ceiba.mobile.pruebadeingreso.api.ApplicationApi;
import co.com.ceiba.mobile.pruebadeingreso.base.BaseApplication;
import co.com.ceiba.mobile.pruebadeingreso.models.Post;
import co.com.ceiba.mobile.pruebadeingreso.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepo {

    private final String TAG = getClass().getSimpleName();

    public MutableLiveData<List<User>> userRequest() {
        final MutableLiveData<List<User>> mutableLiveData = new MutableLiveData<>();
        ApplicationApi usersApi = BaseApplication.getRetrofitClient().create(ApplicationApi.class);
        usersApi.getUser().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body()!= null){
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable throwable) {
                Log.e(TAG,"getUserList onFailure" + call.toString());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Post>> postRequest(User user) {
        final MutableLiveData<List<Post>> postMutableLiveData = new MutableLiveData<>();
        ApplicationApi apiService = BaseApplication.getRetrofitClient().create(ApplicationApi.class);
        apiService.getUserPosts(user.getId()).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful() && response.body()!= null){
                    postMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable throwable) {
                Log.e(TAG,"getPostList onFailure" + call.toString());
            }
        });
        return postMutableLiveData;
    }
}
