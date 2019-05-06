package com.bulgarian.culture.service.impl;

import com.bulgarian.culture.database.UserTableHelper;
import com.bulgarian.culture.factory.UserValidatorFactory;
import com.bulgarian.culture.model.dto.UserBindingModel;
import com.bulgarian.culture.model.enity.User;
import com.bulgarian.culture.repository.api.UserRepository;
import com.bulgarian.culture.repository.impl.DefaultUserRepository;
import com.bulgarian.culture.service.api.UserService;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import java.util.List;

public class DefaultUserService implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public DefaultUserService(UserTableHelper userTableHelper) {
        modelMapper = new ModelMapper();
        userRepository = new DefaultUserRepository(userTableHelper);
    }

    @Override
    public void registerUser(UserBindingModel userBindingModel) {
        UserValidatorFactory.getDefaultValidator().checkUserBindingModel(userBindingModel,
                userRepository.findUserUsernames(), userRepository.findUserEmails());
        User user = modelMapper.map(userBindingModel, User.class);
        user.setPassword(new String(Hex.encodeHex(DigestUtils.sha256(userBindingModel.getPassword()))));
        userRepository.save(user);
    }

    @Override
    public List<String> getUsers() {
        return userRepository.findUserUsernames();
    }

    @Override
    public boolean isValidUser(String usernameText, String passwordText) {
       return userRepository.userExistsByUsernameAndPassword(usernameText,
               new String(Hex.encodeHex(DigestUtils.sha256(passwordText))));

    }


}
