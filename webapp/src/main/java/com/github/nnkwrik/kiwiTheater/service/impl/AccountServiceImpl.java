package com.github.nnkwrik.kiwiTheater.service.impl;

import com.github.nnkwrik.kiwiTheater.dao.UserMapper;
import com.github.nnkwrik.kiwiTheater.exception.CustomException;
import com.github.nnkwrik.kiwiTheater.model.User;
import com.github.nnkwrik.kiwiTheater.service.AccountService;
import com.github.nnkwrik.kiwiTheater.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserMapper userMapper;

    public User signin(String username, String password) throws CustomException {
        if (username == null || password == null ||
                username.trim().equals("")|| password.trim().equals("")) {
            throw new CustomException("登录信息不完整");
        }
        User user = userMapper.signin(username, MD5.MD5(password));
        if (user == null) {
            throw new CustomException("用户名或密码错误");
        }

        return user;
    }

    public void checkNamePass(String username,String password) throws CustomException {
        if (username == null || password == null ||
                username.trim().equals("") || password.trim().equals("")) {
            throw new CustomException("登录信息不完整");
        }
        if (userMapper.checkName(username) > 0) {
            throw new CustomException("该用户名已被注册");
        }
    }

    public User signup(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setNickname(username); //默认与username相同
        user.setPassword(MD5.MD5(password));
        //自动注入id
        userMapper.signup(user);

        //删除前端不需要的情报
        user.setCoin(5.0);//注册时免费获得5块
        user.setUsername(null);
        user.setPassword(null);

        return user;
    }

}
