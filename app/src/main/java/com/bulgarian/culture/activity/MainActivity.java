package com.bulgarian.culture.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.bulgarian.culture.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(listener -> startActivity(new Intent(this, RegisterActivity.class)));

        Button loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(listener -> startActivity(new Intent(this, LoginActivity.class)));
    }
}
