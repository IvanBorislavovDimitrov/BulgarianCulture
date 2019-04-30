package com.bulgarian.culture.repository.api;

import com.bulgarian.culture.model.enity.User;

import java.util.List;

public interface UserRepository extends Repository<User> {

    List<String> findUserUsernames();

    List<String> findUserEmails();
}
