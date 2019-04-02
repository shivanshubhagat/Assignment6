package com.example.assignment6.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.util.SortedList;
import android.support.v7.util.SortedList.Callback;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.assignment6.R;
import com.example.assignment6.activity.ShowPostActivity;
import com.example.assignment6.callback.UsersApi;
import com.example.assignment6.model.Post;
import com.example.assignment6.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowDetailsFragment extends Fragment {

    private TextView tvId, tvName, tvUserName, tvEmail;
    private Button btnShowPost;
    private int userIdToFetchPost;
    private LinearLayout llNoUser,llHasUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_details, container, false);

        initViews(view);
        return view;
    }

    private void initViews(View view) {
        tvId = view.findViewById(R.id.tvId);
        tvName = view.findViewById(R.id.tvName);
        tvUserName = view.findViewById(R.id.tvUserName);
        tvEmail =view.findViewById(R.id.tvEmail);
        btnShowPost = view.findViewById(R.id.btnShowPosts);
        llNoUser = view.findViewById(R.id.llNoUserAdded);
        llHasUser = view.findViewById(R.id.ll_user);

        llNoUser.setVisibility(View.VISIBLE);
        btnShowPost.setVisibility(View.INVISIBLE);
        llHasUser.setVisibility(View.INVISIBLE);

        btnShowPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowPostActivity.class);
                intent.putExtra("userId",userIdToFetchPost);
                startActivity(intent);
            }
        });
    }


    public void setDetails(User user) {

        userIdToFetchPost = user.getId();
        tvId.setText(String.valueOf(user.getId()));
        tvName.setText(user.getName());
        tvUserName.setText(user.getUserName());
        tvEmail.setText(user.getEmail());
        btnShowPost.setVisibility(View.VISIBLE);
        llNoUser.setVisibility(View.INVISIBLE);
        llHasUser.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
