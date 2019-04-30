package com.bulgarian.culture.repository.impl;

import com.bulgarian.culture.database.UserTableHelper;
import com.bulgarian.culture.model.enity.User;
import com.bulgarian.culture.repository.api.UserRepository;

import java.util.List;

public class DefaultUserRepository implements UserRepository {

    private final UserTableHelper userTableHelper;

    public DefaultUserRepository(UserTableHelper userTableHelper) {
        this.userTableHelper = userTableHelper;
    }

    @Override
    public void save(User user) {
        userTableHelper.save(user);
    }

    @Override
    public User findById(String id) {
        return null;
    }

    @Override
    public List<String> findUserUsernames() {
        return userTableHelper.findUserUsernames();
    }

    @Override
    public List<String> findUserEmails() {
        return userTableHelper.findUserEmails();
    }
}
