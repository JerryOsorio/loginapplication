package com.example.jerry.loginapplication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jerry on 6/26/17.
 */

public class GitHubRepo {

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("language")
    private String language;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public GitHubRepo(
            String language,
            String description,
            String name){

        this.setLanguage(language);
        this.setDescription(description);
        this.setName(name);

    }
}
