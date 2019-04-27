package com.bulgarian.culture.service.impl;

import com.bulgarian.culture.exception.PasswordsMismatchException;
import com.bulgarian.culture.model.dto.UserBindingModel;
import com.bulgarian.culture.model.enity.User;
import com.bulgarian.culture.service.api.UserService;

import org.modelmapper.ModelMapper;

import java.util.Objects;

public class DefaultUserService implements UserService {

    private final ModelMapper modelMapper;

    public DefaultUserService() {
        modelMapper = new ModelMapper();
    }

    @Override
    public User registerUser(UserBindingModel userBindingModel) {
        User user = modelMapper.map(userBindingModel, User.class);
        if (!Objects.equals(userBindingModel.getPassword(), userBindingModel.getConfirmPassword())) {
            throw new PasswordsMismatchException();
        }
        return null;
    }
}
