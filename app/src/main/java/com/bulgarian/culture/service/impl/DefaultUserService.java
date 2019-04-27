package com.bulgarian.culture.service.impl;

import com.bulgarian.culture.database.UserTableHelper;
import com.bulgarian.culture.exception.PasswordsMismatchException;
import com.bulgarian.culture.model.dto.UserBindingModel;
import com.bulgarian.culture.model.enity.User;
import com.bulgarian.culture.repository.api.UserRepository;
import com.bulgarian.culture.repository.impl.DefaultUserRepository;
import com.bulgarian.culture.service.api.UserService;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Objects;

public class DefaultUserService implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public DefaultUserService(UserTableHelper userTableHelper) {
        modelMapper = new ModelMapper();
        userRepository = new DefaultUserRepository(userTableHelper);
    }

    @Override
    public void registerUser(UserBindingModel userBindingModel) {
        User user = modelMapper.map(userBindingModel, User.class);
        if (!Objects.equals(userBindingModel.getPassword(), userBindingModel.getConfirmPassword())) {
            throw new PasswordsMismatchException();
        }

        userRepository.save(user);
    }

    @Override
    public List<String> getUsers() {
        return userRepository.getUsers();
    }
}
