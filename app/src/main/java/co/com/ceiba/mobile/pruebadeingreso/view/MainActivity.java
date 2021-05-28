package co.com.ceiba.mobile.pruebadeingreso.view;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.adapters.UserListAdapter;
import co.com.ceiba.mobile.pruebadeingreso.base.BaseApplication;
import co.com.ceiba.mobile.pruebadeingreso.crud.CRUDUser;
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityMainBinding;
import co.com.ceiba.mobile.pruebadeingreso.dialog.LoaderDialog;
import co.com.ceiba.mobile.pruebadeingreso.interfaces.UserClick;
import co.com.ceiba.mobile.pruebadeingreso.models.User;
import co.com.ceiba.mobile.pruebadeingreso.viewModel.ApplicationViewModel;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements UserClick {

    UserListAdapter userListAdapter;
    ApplicationViewModel appViewModel = new ApplicationViewModel();
    ActivityMainBinding binding;
    Realm realm;
    List<User> filterList = new ArrayList<>();
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoaderDialog loaderDialog = new LoaderDialog(MainActivity.this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        realm = Realm.getDefaultInstance();
        SharedPreferences preferences = getSharedPreferences("userList", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();


       data = preferences.getString("user","");
        Log.e("Andres","data is -> " + data);

        initUI();
        //if (data.equals("")) {
            Log.e("Andres","onCreate");
            if (BaseApplication.getInstance().isNetworkAvailable()){
                binding.progressBar.setVisibility(View.VISIBLE);
                appViewModel.getUser().observe(this, new Observer<List<User>>() {
                    @Override
                    public void onChanged(List<User> users) {
                        userListAdapter.addUserList(users);
                        userListAdapter.notifyDataSetChanged();
                        Log.e("Andres","DataChanged");
                        binding.progressBar.setVisibility(View.GONE);
                        editor.putString("user", new Gson().toJson(users));
                        //editor.putString("user", "Hola");
                        Log.e("Andres", "-> " +new Gson().toJson(users));
                        editor.apply();
                        //loadPreferences();
                        //loaderDialog.hideLoader();
                        initSearch(users);

                    }
                });
            }
/*        } else {
            //User user = ;
            //appViewModel.setUser();

            Log.e("Andres","else ");
            userListAdapter.notifyDataSetChanged();
        }*/

            data = preferences.getString("user","");

        Log.e("Andres","data 2  -> " + data);
        CRUDUser.getAllUsers();
    }


    private void initUI() {
        binding.recyclerViewSearchResults.setHasFixedSize(true);
        binding.recyclerViewSearchResults.setLayoutManager(new LinearLayoutManager(this));
        userListAdapter = new UserListAdapter();
        userListAdapter.setUserClick(this);
        binding.recyclerViewSearchResults.setAdapter(userListAdapter);
    }


    @Override
    public void click(User user) {
        Intent intent = new Intent(MainActivity.this, PostActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    private void initSearch(List<User> users){
        binding.editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                filterList.clear();
                if (editable.toString().isEmpty()){
                    userListAdapter.addUserList(users);
                    userListAdapter.notifyDataSetChanged();
                } else {
                    filterSearch(editable.toString(), users);
                }
            }
        });
    }

    private void filterSearch(String text, List<User> userList) {

        for (User user: userList){
            if (user.getName().contains(text)){
                filterList.add(user);
            }
        }

        if (filterList.size() == 0) {
            binding.tvEmpty.setVisibility(View.VISIBLE);
        } else {
            binding.tvEmpty.setVisibility(View.GONE);
            userListAdapter.addUserList(filterList);
            userListAdapter.notifyDataSetChanged();
        }
    }
}