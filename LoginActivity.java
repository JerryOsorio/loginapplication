package com.example.jerry.loginapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    Button login;

    Intent i;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.input_username);
        login = (Button) findViewById(R.id.login_button);
    }

    public void getUser(View view){
        i = new Intent(LoginActivity.this, User.class);
        i.putExtra("data", username.getText().toString());
        startActivity(i);
    }
}
