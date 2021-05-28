package co.com.ceiba.mobile.pruebadeingreso.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.models.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    List<Post> postsList;

    public PostAdapter() {
        this.postsList = new ArrayList<>();
    }

    public void addPostsList(List<Post> currentList){
        this.postsList = currentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post_list_item, null, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostAdapter.ViewHolder viewHolder, int position) {
        viewHolder.title.setText(postsList.get(position).getTitle());
        viewHolder.body.setText(postsList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, body;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            body = view.findViewById(R.id.body);
        }
    }
}
