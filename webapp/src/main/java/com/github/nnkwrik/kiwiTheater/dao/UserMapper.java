package com.github.nnkwrik.kiwiTheater.dao;

import com.github.nnkwrik.kiwiTheater.model.Room;
import com.github.nnkwrik.kiwiTheater.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User signin(@Param("username") String username, @Param("password") String password);

    Integer checkName(String username);

    void signup(User user);

    User findUserById(Integer id);

    User findSimpleUserById(Integer id);

    void updateUserInfo(User user);

    void registerAnchor(Room room);

//    List<User> findSimpleUserListById(List<Integer> id);
}
