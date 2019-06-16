package com.bulgarian.culture.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.bulgarian.culture.R;

import static com.bulgarian.culture.constants.Constants.USERNAME;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showButtons();
    }

    private void showButtons() {
        showLoginPageIfLoggedIn();
        showRegisterButtonIfNotLoggedIn();
        showLoginButtonIfNotLoggedIn();
        showLogoutButtonIfLoggedIn();
        showCheckWeatherButtonIfLogged();
        showHistoryButtonIfLogged();
    }

    private void showLoginPageIfLoggedIn() {
        View isLoggedInView = findViewById(R.id.isLoggedIn);
        if (isLoggedIn()) {
            isLoggedInView.setVisibility(View.VISIBLE);
            return;
        }
        isLoggedInView.setVisibility(View.INVISIBLE);
    }

    private void showRegisterButtonIfNotLoggedIn() {
        Button registerButton = findViewById(R.id.register);
        if (isLoggedIn()) {
            registerButton.setVisibility(View.INVISIBLE);
            return;
        }
        registerButton.setOnClickListener(listener -> startActivity(new Intent(this, RegisterActivity.class)));
        registerButton.setVisibility(View.VISIBLE);
    }

    private void showLoginButtonIfNotLoggedIn() {
        Button loginButton = findViewById(R.id.login);
        if (isLoggedIn()) {
            loginButton.setVisibility(View.INVISIBLE);
            return;
        }
        loginButton.setOnClickListener(listener -> startActivity(new Intent(this, LoginActivity.class)));
        loginButton.setVisibility(View.VISIBLE);
    }

    private void showLogoutButtonIfLoggedIn() {
        Button logoutButton = findViewById(R.id.logout);
        if (isLoggedIn()) {
            logoutButton.setVisibility(View.VISIBLE);
            logoutButton.setOnClickListener(listener -> logout());
            return;
        }
        logoutButton.setVisibility(View.INVISIBLE);
    }

    private void showCheckWeatherButtonIfLogged() {
        Button checkWeatherButton = findViewById(R.id.checkWeather);
        if (isLoggedIn()) {
            checkWeatherButton.setVisibility(View.VISIBLE);
            checkWeatherButton.setOnClickListener(listener -> startActivity(new Intent(this, WeatherActivity.class)));
            return;
        }
        checkWeatherButton.setVisibility(View.INVISIBLE);
    }

    private void showHistoryButtonIfLogged() {
        Button historyButton = findViewById(R.id.history);
        if (isLoggedIn()) {
            historyButton.setVisibility(View.VISIBLE);
            historyButton.setOnClickListener(listener -> startActivity(new Intent(this, HistoryActivity.class)));
            return;
        }
        historyButton.setVisibility(View.INVISIBLE);
    }

    private void logout() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        removeUser(sharedPreferences);
        startActivity(new Intent(this, getClass()));
    }

    private void removeUser(SharedPreferences sharedPreferences) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(USERNAME);
        editor.apply();
    }

    private boolean isLoggedIn() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPreferences.getAll().get(USERNAME) != null;
    }
}
