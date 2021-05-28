package co.com.ceiba.mobile.pruebadeingreso.view;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import com.google.gson.Gson;
import java.util.List;
import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.adapters.PostAdapter;
import co.com.ceiba.mobile.pruebadeingreso.base.BaseApplication;
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityPostBinding;
import co.com.ceiba.mobile.pruebadeingreso.models.Post;
import co.com.ceiba.mobile.pruebadeingreso.models.User;
import co.com.ceiba.mobile.pruebadeingreso.viewModel.ApplicationViewModel;

public class PostActivity extends AppCompatActivity {

    ApplicationViewModel appViewModel = new ApplicationViewModel();
    ActivityPostBinding binding;
    PostAdapter postListAdapter = new PostAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post);
        Bundle extras = getIntent().getExtras();
        User user = extras.getParcelable("user");
        initUI(user);
        if (BaseApplication.getInstance().isNetworkAvailable()) {
            binding.progressBar.setVisibility(View.VISIBLE);
            appViewModel.getPosts(user).observe((LifecycleOwner) this, new Observer<List<Post>>() {
                @Override
                public void onChanged(List<Post> posts) {
                    postListAdapter.addPostsList(posts);
                    postListAdapter.notifyDataSetChanged();
                    binding.progressBar.setVisibility(View.GONE);
                }
            });
        }
    }

    private void initUI(User user) {
        binding.name.setText(user.getName());
        binding.phone.setText(user.getPhone());
        binding.email.setText(user.getEmail());
        binding.recyclerViewPostsResults.setHasFixedSize(true);
        binding.recyclerViewPostsResults.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recyclerViewPostsResults.setAdapter(postListAdapter);
    }
}
