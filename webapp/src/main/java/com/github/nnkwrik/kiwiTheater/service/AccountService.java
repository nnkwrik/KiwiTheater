package com.github.nnkwrik.kiwiTheater.service;

import com.github.nnkwrik.kiwiTheater.exception.CustomException;
import com.github.nnkwrik.kiwiTheater.model.User;

public interface AccountService {
    User signin(String username, String password) throws CustomException;

    void checkNamePass(String username, String password) throws CustomException;

    User signup(String username, String password);
}
