package com.bulgarian.culture.service.api;

import com.bulgarian.culture.model.dto.UserBindingModel;
import com.bulgarian.culture.model.enity.User;

public interface UserService {

    User registerUser(UserBindingModel userBindingModel);
}
