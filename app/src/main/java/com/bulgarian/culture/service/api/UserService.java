package com.bulgarian.culture.service.api;

import com.bulgarian.culture.model.dto.UserBindingModel;

import java.util.List;

public interface UserService {

    void registerUser(UserBindingModel userBindingModel);

    List<String> getUsers();

    boolean isValidUser(String usernameText, String passwordText);
}
