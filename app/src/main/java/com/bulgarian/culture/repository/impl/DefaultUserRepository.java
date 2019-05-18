package com.bulgarian.culture.repository.impl;

import com.bulgarian.culture.database.DatabaseHelper;
import com.bulgarian.culture.model.enity.User;
import com.bulgarian.culture.repository.api.UserRepository;

import java.util.List;

public class DefaultUserRepository implements UserRepository {

    private final DatabaseHelper databaseHelper;

    public DefaultUserRepository(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @Override
    public void save(User user) {
        databaseHelper.saveUser(user);
    }

    @Override
    public User findById(String id) {
        return null;
    }

    @Override
    public List<String> findUserUsernames() {
        return databaseHelper.findUserUsernames();
    }

    @Override
    public List<String> findUserEmails() {
        return databaseHelper.findUserEmails();
    }

    @Override
    public boolean userExistsByUsernameAndPassword(String username, String password) {
        User user= databaseHelper.findUserByUsername(username);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }
}
