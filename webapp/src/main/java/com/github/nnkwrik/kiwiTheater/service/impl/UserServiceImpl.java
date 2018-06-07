package com.github.nnkwrik.kiwiTheater.service.impl;

import com.github.nnkwrik.kiwiTheater.dao.RoomMapper;
import com.github.nnkwrik.kiwiTheater.dao.UserMapper;
import com.github.nnkwrik.kiwiTheater.model.Room;
import com.github.nnkwrik.kiwiTheater.model.User;
import com.github.nnkwrik.kiwiTheater.model.Vo;
import com.github.nnkwrik.kiwiTheater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoomMapper roomMapper;

    public User findUserById(Integer userid){

        return userMapper.findUserById(userid);
    }

    public void updateUserInfo(User user){
        userMapper.updateUserInfo(user);
    }

}
