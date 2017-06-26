package com.example.jerry.loginapplication.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jerry.loginapplication.R;
import com.example.jerry.loginapplication.activity.model.ImageDownloader;
import com.example.jerry.loginapplication.model.GitHubUser;
import com.example.jerry.loginapplication.rest.APIClient;
import com.example.jerry.loginapplication.rest.GitHubUserEndPoints;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity{
    ImageView avatarImg;
    TextView userNameTV;
    TextView followersTV;
    TextView followingTV;
    TextView login;
    TextView email;
    Button ownedrepos;
    Bundle extras;
    String newString;

    Bitmap myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        avatarImg = (ImageView) findViewById(R.id.avatar);
        userNameTV = (TextView) findViewById(R.id.username);
        followersTV = (TextView) findViewById(R.id.followers);
        followingTV = (TextView) findViewById(R.id.following);
        login = (TextView) findViewById(R.id.logIn);
        email = (TextView) findViewById(R.id.email);
        ownedrepos = (Button) findViewById(R.id.ownedReposBtn);

        extras = getIntent().getExtras();
        newString = extras.getString("data");

        System.out.println(newString);
        loadData();

    }

    public void loadOwnRepos(View view ){
        Intent i = new Intent(UserActivity.class, Repositories.class);
        i.putExtra("username",newString);
        startActivity(i);
    }

    public void loadData(){
        final GitHubUserEndPoints apiService =
                APIClient.getClient().create(GitHubUserEndPoints.class);

        Call<GitHubUser> call = apiService.getUser(newString);
        call.enqueue(new Callback<GitHubUser>(){
            @Override
            public void onResponse(Call<GitHubUser> call, Response<GitHubUser> response) {
                ImageDownloader task = new ImageDownloader();
                try{
                    myImage = task.execute(response.body().getAvatar()).get();
                    avatarImg.setImageBitmap(myImage);
                    avatarImg.getLayoutParams().height=220;
                    avatarImg.getLayoutParams().width=220;
                }catch (Exception e){
                    e.printStackTrace();
                }



                if(response.body().getName()==null){
                    userNameTV.setText("No Username Provided");
                }
                else{
                    userNameTV.setText("Username: " + response.body().getName());
                }

                followersTV.setText("Followers: " + response.body().getFollowers());
                followingTV.setText("Following: " + response.body().getFollowing());

                login.setText("Login : " + response.body().getLogin());

                if(response.body().getEmail()==null){
                    email.setText("Email not found");
                }
                else{
                    email.setText("Email : " + response.body().getEmail());
                }
            }

            @Override
            public void onFailure(Call<GitHubUser> call, Throwable t) {

            }
        });

    }
}
