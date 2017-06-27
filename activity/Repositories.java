package com.example.jerry.loginapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.jerry.loginapplication.R;
import com.example.jerry.loginapplication.model.GitHubRepo;
import com.example.jerry.loginapplication.model.ReposAdapter;
import com.example.jerry.loginapplication.rest.APIClient;
import com.example.jerry.loginapplication.rest.GitHubRepoEndPoint;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jerry on 6/27/17.
 */

public class Repositories extends AppCompatActivity{
    String recievedUserName;
    TextView userNameTV;
    RecyclerView mRecyclerView;
    List<GitHubRepo> myDataSource = new ArrayList<>();
    RecyclerView.Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repositories);

        Bundle extras = getIntent().getExtras();
        recievedUserName = extras.getString("username");

        userNameTV = (TextView) findViewById(R.id.userNameTV);
        userNameTV.setText("User: " + recievedUserName);

        mRecyclerView = (RecyclerView) findViewById(R.id.repos_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new ReposAdapter(myDataSource, R.layout.list_item_repo,
                getApplicationContext());
        mRecyclerView.setAdapter(myAdapter);

        loadRepositories();
    }

    public void loadRepositories(){
        GitHubRepoEndPoint apiService =
                APIClient.getClient().create(GitHubRepoEndPoint.class);

        Call<List<GitHubRepo>> call = apiService.getRepo(recievedUserName);
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                myDataSource.clear();
                myDataSource.addAll(response.body());
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                Log.e("Repos", t.toString());
            }
        });
    }

}
