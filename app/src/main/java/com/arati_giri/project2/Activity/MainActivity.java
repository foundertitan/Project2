package com.arati_giri.project2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.arati_giri.project2.R;

public class MainActivity extends AppCompatActivity {


    Button logInButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logInButton = findViewById(R.id.logInButton);

        logInButton.setOnClickListener(view -> {

            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);

        });
    }
}