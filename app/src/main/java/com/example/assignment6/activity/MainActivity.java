package com.example.assignment6.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.assignment6.R;
import com.example.assignment6.adapter.UserListAdapter;
import com.example.assignment6.callback.UsersApi;
import com.example.assignment6.fragment.ShowDetailsFragment;
import com.example.assignment6.listener.OnFragmentInteractionListener;
import com.example.assignment6.listener.RecyclerTouchListener;
import com.example.assignment6.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.assignment6.util.Constant.BASE_URL;
import static com.example.assignment6.util.Constant.userObject;

/**
 * Two fragments in this activity, one having user list and other having user details.
 * and a button which fires another activity which shows the post of that user
 */

public class MainActivity extends AppCompatActivity implements RecyclerTouchListener, OnFragmentInteractionListener {

    private User user;
    private ArrayList<User> userArrayList;
    private RecyclerView rvUserList;
    private UserListAdapter userListAdapter;
    private RecyclerTouchListener recyclerTouchListener;
    private OnFragmentInteractionListener onFragmentInteractionListener;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerTouchListener = this;
        onFragmentInteractionListener = this;

        userArrayList = new ArrayList<>();

        rvUserList = findViewById(R.id.recycler_view_user_list);
        progressBar = findViewById(R.id.progBar);
        progressBar.drawableHotspotChanged(10,10);
        rvUserList.setLayoutManager(new LinearLayoutManager(this));

        if (savedInstanceState != null) {
            user = savedInstanceState.getParcelable(userObject);
            if (user != null) {
                addDetailsToFragment(user);
            }
        }
        fetchFromApi();
    }

    @Override
    public void onItemClick(int position) {
        user = userArrayList.get(position);
        onFragmentInteractionListener.passData(user);
    }

    /**
     * passing the user object from one fragment to another
     * @param user
     */
    public void passData(User user) {
        this.user = user;
        addDetailsToFragment(user);
    }

    /**
     * adding the details to fragment by sending the user object
     * @param user
     */
    public void addDetailsToFragment(User user) {
        ShowDetailsFragment showDetailsFragment = (ShowDetailsFragment) getSupportFragmentManager()
                .findFragmentById(R.id.userDetailsFragment);
        if (showDetailsFragment != null) {
            showDetailsFragment.setDetails(user);
        }
    }

    /**
     * fetching the user list from api
     */
    private void fetchFromApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final UsersApi usersApi = retrofit.create(UsersApi.class);

        Call<ArrayList<User>> call = usersApi.getUser();

        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                userArrayList = response.body();
                userListAdapter = new UserListAdapter(userArrayList, recyclerTouchListener);
                rvUserList.setAdapter(userListAdapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(userObject, user);
    }
}
