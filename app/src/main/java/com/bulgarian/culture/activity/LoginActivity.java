package com.bulgarian.culture.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bulgarian.culture.R;
import com.bulgarian.culture.factory.UserServiceFactory;
import com.bulgarian.culture.factory.UserTableHelperFactory;
import com.bulgarian.culture.service.api.UserService;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.bulgarian.culture.constants.Constants.INVALID_CREDENTIALS;
import static com.bulgarian.culture.constants.Constants.INVALID_FORM;
import static com.bulgarian.culture.constants.Constants.USERNAME;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginUser();
    }

    private void loginUser() {
        Button loginButton = findViewById(R.id.loginConfirm);
        loginButton.setOnClickListener(listener -> {
            TextView username = findViewById(R.id.usernameLogin);
            String usernameText = username.getText().toString();
            TextView password = findViewById(R.id.passwordLogin);
            String passwordText = password.getText().toString();
            UserService userService = UserServiceFactory.getDefaultUserService(UserTableHelperFactory.getUserTableHelper(this));
            validateUser(usernameText, passwordText, userService);
        });
    }

    private void validateUser(String usernameText, String passwordText, UserService userService) {
        if (userService.isValidUser(usernameText, passwordText)) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putStringSet(USERNAME, Stream.of(new String[]{usernameText}).collect(Collectors.toSet()));
            editor.apply();
            startActivity(new Intent(this, MainActivity.class));
        } else {
            new AlertDialog.Builder(this)
                    .setTitle(INVALID_FORM)
                    .setMessage(INVALID_CREDENTIALS)
                    .show();
        }
    }
}
