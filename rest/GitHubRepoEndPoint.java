package com.example.jerry.loginapplication.rest;

import com.example.jerry.loginapplication.model.GitHubRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jerry on 6/26/17.
 */

public interface GitHubRepoEndPoint {

    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>> getRepo(@Path("user") String name);
}
