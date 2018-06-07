package com.github.nnkwrik.kiwiTheater.service;

import com.github.nnkwrik.kiwiTheater.model.User;
import com.github.nnkwrik.kiwiTheater.model.Vo;

public interface UserService {

    User findUserById(Integer userid);

    void updateUserInfo(User user);
}
