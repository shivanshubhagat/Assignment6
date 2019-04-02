package com.example.assignment6.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assignment6.R;
import com.example.assignment6.model.Post;

import java.util.ArrayList;

/**
 * adapter for arraylist of posts of a user
 */
public class PostListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Post> postArrayList;

    public PostListAdapter(ArrayList<Post> postArrayList) {
        this.postArrayList = postArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_post, viewGroup, false);
        return new PostListAdapter.PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int position) {
        PostListAdapter.PostViewHolder postViewHolder = (PostListAdapter.PostViewHolder) viewHolder;
        postViewHolder.tvId.setText(String.valueOf(postArrayList.get(viewHolder.getAdapterPosition()).getId()));
        postViewHolder.tvTitle.setText(postArrayList.get(viewHolder.getAdapterPosition()).getTitle());
        postViewHolder.tvBody.setText(postArrayList.get(viewHolder.getAdapterPosition()).getBody());
    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        private TextView tvId, tvTitle, tvBody;

        PostViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvPostId);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvBody = itemView.findViewById(R.id.tvBody);
        }
    }
}
