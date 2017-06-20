package com.example.jerry.loginapplication.rest;

import com.example.jerry.loginapplication.model.GitHubUser;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jerry on 6/20/17.
 */

public interface GiHubUserEndPoints {

    @GET("/users/{user}")
    Call<GitHubUser> getUser(@Path("user") String user);




}
