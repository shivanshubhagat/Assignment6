package com.example.assignment6.callback;

import com.example.assignment6.model.Post;
import com.example.assignment6.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.assignment6.util.Constant.POSTS;
import static com.example.assignment6.util.Constant.USERS;
import static com.example.assignment6.util.Constant.USER_ID;

public interface UsersApi {

    @GET(USERS)
    Call<ArrayList<User>> getUser();

    @GET(POSTS)
    Call<ArrayList<Post>> getPost(@Query(USER_ID) int userId);
}
