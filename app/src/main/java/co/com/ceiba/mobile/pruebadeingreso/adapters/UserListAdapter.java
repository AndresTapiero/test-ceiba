package co.com.ceiba.mobile.pruebadeingreso.adapters;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.models.User;
import co.com.ceiba.mobile.pruebadeingreso.interfaces.UserClick;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private List<User> userList;

    public void setUserClick(UserClick userClick) {
        this.userClick = userClick;
    }

    private UserClick userClick;

    public UserListAdapter() { userList = new ArrayList<>();}

    public void addUserList(List<User> currentList){
        this.userList = currentList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_list_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final UserListAdapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        viewHolder.name.setText(userList.get(position).getName());
        viewHolder.phone.setText(userList.get(position).getPhone());
        viewHolder.email.setText(userList.get(position).getEmail());
        viewHolder.btnViewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userClick.click(userList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, phone, email;
        Button btnViewPost;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            email = itemView.findViewById(R.id.email);
            btnViewPost = itemView.findViewById(R.id.btn_view_post);
        }
    }
}
