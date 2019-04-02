package com.example.assignment6.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assignment6.R;
import com.example.assignment6.listener.RecyclerTouchListener;
import com.example.assignment6.model.User;

import java.util.List;

/**
 * adapter for arraylist of users
 */

public class UserListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<User> userList;
    private RecyclerTouchListener mListener;

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView userId, userName;

        UserViewHolder(@NonNull View view) {
            super(view);
            userId = view.findViewById(R.id.tv_id);
            userName = view.findViewById(R.id.tv_name);
        }
    }

    public UserListAdapter(List userList, RecyclerTouchListener mListener)
    {
        this.userList = userList;
        this.mListener = mListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View userItemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_user, viewGroup, false);
        return new UserViewHolder(userItemView);

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int position) {
        UserViewHolder userViewHolder = (UserViewHolder) viewHolder;
        User user = userList.get(viewHolder.getAdapterPosition());
        userViewHolder.userId.setText(String.valueOf(user.getId()));
        userViewHolder.userName.setText(user.getName());

        userViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(viewHolder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
