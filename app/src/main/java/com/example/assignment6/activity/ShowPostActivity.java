package com.example.assignment6.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.assignment6.R;
import com.example.assignment6.adapter.PostListAdapter;
import com.example.assignment6.callback.UsersApi;
import com.example.assignment6.model.Post;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.assignment6.util.Constant.BASE_URL;
import static com.example.assignment6.util.Constant.USER_ID;

/**
 * second activity which will show the list of posts of the user
 */
public class ShowPostActivity extends AppCompatActivity {

    private RecyclerView rvPostList;
    private ArrayList<Post> postArrayList;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_post);
        postArrayList = new ArrayList<>();
        rvPostList = findViewById(R.id.rvPostList);
        progressBar = findViewById(R.id.progBar);
        progressBar.drawableHotspotChanged(10,10);
        rvPostList.setLayoutManager(new LinearLayoutManager(this));
        int userId = getIntent().getIntExtra(USER_ID, -1);
        fetchPost(userId);
    }

    /**
     * fetching the array list of posts of user
     * @param userId
     */
    private void fetchPost(int userId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UsersApi usersApi = retrofit.create(UsersApi.class);

        Call<ArrayList<Post>> call = usersApi.getPost(userId);

        call.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                postArrayList = response.body();
                rvPostList.setAdapter(new PostListAdapter(postArrayList));
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
            }
        });
    }
}