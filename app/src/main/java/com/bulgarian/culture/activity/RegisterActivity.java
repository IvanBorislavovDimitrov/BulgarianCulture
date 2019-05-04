package com.bulgarian.culture.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bulgarian.culture.R;
import com.bulgarian.culture.exception.UserException;
import com.bulgarian.culture.factory.UserServiceFactory;
import com.bulgarian.culture.factory.UserTableHelperFactory;
import com.bulgarian.culture.model.dto.UserBindingModel;
import com.bulgarian.culture.service.api.UserService;

import static com.bulgarian.culture.constants.Constants.INVALID_FORM;

public class RegisterActivity extends AppCompatActivity {
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initDependencies();
        registerUser();
    }

    private void registerUser() {
        findViewById(R.id.registerConfirm).setOnClickListener(listener -> {
            TextView usernameTextView = findViewById(R.id.usernameRegister);
            String username = String.valueOf(usernameTextView.getText());
            TextView emailTextView = findViewById(R.id.emailRegister);
            String email = String.valueOf(emailTextView.getText());
            TextView passwordTextView = findViewById(R.id.passwordRegister);
            String password = String.valueOf(passwordTextView.getText());
            TextView confirmPasswordTextView = findViewById(R.id.confirmPasswordRegister);
            String confirmPassword = String.valueOf(confirmPasswordTextView.getText());
            UserBindingModel userBindingModel = createUser(username, email, password, confirmPassword);
            registerUser(userBindingModel);
        });
    }

    private void registerUser(UserBindingModel userBindingModel) {
        try {
            userService.registerUser(userBindingModel);
        } catch (UserException e) {
            new AlertDialog.Builder(this)
                    .setTitle(INVALID_FORM)
                    .setMessage(e.getMessage())
                    .show();
            return;
        }
        startActivity(new Intent(this, MainActivity.class));
    }

    private UserBindingModel createUser(String username, String email, String password, String confirmPassword) {
        return new UserBindingModel.Builder()
                .username(username)
                .email(email)
                .password(password)
                .confirmPassword(confirmPassword)
                .build();
    }

    private void initDependencies() {
        userService = UserServiceFactory.getDefaultUserService(UserTableHelperFactory.getUserTableHelper(this));
    }
}
