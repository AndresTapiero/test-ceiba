package co.com.ceiba.mobile.pruebadeingreso.api;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.models.*;
import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApplicationApi {

    @GET(Endpoints.GET_USERS)
    Call<List<User>> getUser();

    @GET(Endpoints.GET_POST_USER)
    Call<List<Post>> getPosts();

    @GET("/posts?")
    Call<List<Post>> getUserPosts(@Query("userId") int id);

}
