package com.bulgarian.culture.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.bulgarian.culture.R;

import java.util.Map;

import static com.bulgarian.culture.constants.Constants.USERNAME;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Object user = sharedPreferences.getAll().get(USERNAME);

        Button registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(listener -> startActivity(new Intent(this, RegisterActivity.class)));

        Button loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(listener -> startActivity(new Intent(this, LoginActivity.class)));
    }
}
