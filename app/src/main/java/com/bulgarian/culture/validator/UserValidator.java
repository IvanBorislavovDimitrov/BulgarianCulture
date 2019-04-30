package com.bulgarian.culture.validator;

import com.bulgarian.culture.exception.EmailTakenException;
import com.bulgarian.culture.exception.InvalidEmailException;
import com.bulgarian.culture.exception.InvalidUsernameException;
import com.bulgarian.culture.exception.PasswordNotSecured;
import com.bulgarian.culture.exception.PasswordsMismatchException;
import com.bulgarian.culture.exception.UsernameTakenException;
import com.bulgarian.culture.model.dto.UserBindingModel;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private static final int MIN_USERNAME_LENGTH = 2;
    private static final String EMAIL_REGEX = "^[A-Za-z][A-Za-z.0-9]+@([A-Za-z]+(\\.)){1,}[A-Za-z0-9]+$";
    private static final int MIN_PASSWORD_LENGTH = 2;

    public void checkUserBindingModel(UserBindingModel userBindingModel, List<String> usernames, List<String> emails) {
        checkUsername(usernames, userBindingModel.getUsername());
        checkEmail(emails, userBindingModel.getEmail());
        checkPasswords(userBindingModel.getPassword(), userBindingModel.getConfirmPassword());
    }

    private void checkPasswords(String password, String confirmPassword) {
        if (!Objects.equals(password, confirmPassword)) {
            throw new PasswordsMismatchException();
        }
        if (password.length() < MIN_PASSWORD_LENGTH || confirmPassword.length() < MIN_PASSWORD_LENGTH) {
            throw new PasswordNotSecured();
        }
    }

    private void checkEmail(List<String> emails, String email) {
        Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
        Matcher emailMatcher = emailPattern.matcher(email);
        if (email == null || !emailMatcher.matches()) {
            throw new InvalidEmailException();
        }
        if (emails.contains(email)) {
            throw new EmailTakenException();
        }
    }

    private void checkUsername(List<String> usernames, String username) {
        if (username == null || username.length() < MIN_USERNAME_LENGTH) {
            throw new InvalidUsernameException();
        }
        if (usernames.contains(username)) {
            throw new UsernameTakenException();
        }
    }
}
