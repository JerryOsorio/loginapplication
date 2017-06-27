package com.example.jerry.loginapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.jerry.loginapplication.model.GitHubRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jerry on 6/27/17.
 */

public class Repositories extends AppCompatActivity{
    String recievedUserName;
    TextView userNameTV;
    RecyclerView mRecyclerView;
    List<GitHubRepo> myDataSource = new ArrayList<>();
    RecyclerView.Adapter myAdapter;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
    }
}
