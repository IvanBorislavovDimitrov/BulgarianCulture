package com.bulgarian.culture.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bulgarian.culture.R;
import com.bulgarian.culture.database.UserTableHelper;
import com.bulgarian.culture.model.dto.UserBindingModel;
import com.bulgarian.culture.service.api.UserService;
import com.bulgarian.culture.service.impl.DefaultUserService;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initDependencies();
        registerUser();
        List<String> users = userService.getUsers();
    }

    private void registerUser() {
        findViewById(R.id.registerConfirm).setOnClickListener(listener -> {
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
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void initDependencies() {
        userService = new DefaultUserService(new UserTableHelper(this));
    }
}
