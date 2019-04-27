package com.bulgarian.culture.model.dto;

import com.bulgarian.culture.model.enity.User;

public class UserBindingModel {

    private String username;
    private String email;
    private String password;
    private String confirmPassword;

    protected UserBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public static class Builder {
        private UserBindingModel userBindingModel;

        public Builder() {
            userBindingModel = new UserBindingModel();
        }

        public Builder username(String username) {
            userBindingModel.username = username;
            return this;
        }

        public Builder email(String email) {
            userBindingModel.email = email;
            return this;
        }

        public Builder password(String password) {
            userBindingModel.password = password;
            return this;
        }

        public Builder confirmPassword(String confirmPassword) {
            userBindingModel.confirmPassword = confirmPassword;
            return this;
        }

        public UserBindingModel build() {
            return userBindingModel;
        }
    }
}
