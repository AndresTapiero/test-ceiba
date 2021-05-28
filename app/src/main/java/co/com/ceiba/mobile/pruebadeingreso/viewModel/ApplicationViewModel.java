package co.com.ceiba.mobile.pruebadeingreso.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import java.util.List;
import co.com.ceiba.mobile.pruebadeingreso.models.Post;
import co.com.ceiba.mobile.pruebadeingreso.models.User;
import co.com.ceiba.mobile.pruebadeingreso.repo.UserRepo;

public class ApplicationViewModel extends ViewModel {
    private UserRepo userRepo;
    private MutableLiveData<List<User>> mutableLiveData;
    private MutableLiveData<List<Post>> postMutableLiveData;


    public ApplicationViewModel(){
        userRepo = new UserRepo();
    }

    public LiveData<List<User>> getUser(){
        if (mutableLiveData == null) {
            mutableLiveData = userRepo.userRequest();
        }
        return mutableLiveData;
    }

    public LiveData<List<Post>> getPosts(User user){
        if (postMutableLiveData == null) {
            postMutableLiveData = userRepo.postRequest(user);
        }
        return postMutableLiveData;
    }

    public LiveData<List<User>> setUser(List<User> users){
        mutableLiveData.setValue(users);
        return mutableLiveData;
    }

}
