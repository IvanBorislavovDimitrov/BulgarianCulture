package com.bulgarian.culture.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bulgarian.culture.R;
import com.bulgarian.culture.model.dto.UserBindingModel;
import com.bulgarian.culture.service.api.UserService;
import com.bulgarian.culture.service.impl.DefaultUserService;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final UserService userService;

    public MainActivity() {
        userService = new DefaultUserService();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(listener -> {
            TextView usernameTextView = findViewById(R.id.username);
            String username = String.valueOf(usernameTextView.getText());
            TextView emailTextView = findViewById(R.id.email);
            String email = String.valueOf(emailTextView.getText());
            TextView passwordTextView = findViewById(R.id.password);
            String password = String.valueOf(passwordTextView.getText());
            TextView confirmPasswordTextView = findViewById(R.id.confirmPassword);
            String confirmPassword = String.valueOf(confirmPasswordTextView.getText());
            UserBindingModel userBindingModel = new UserBindingModel.Builder()
                    .username(username)
                    .email(email)
                    .password(password)
                    .confirmPassword(confirmPassword)
                    .build();
            userService.registerUser(userBindingModel);
        });
    }
}
