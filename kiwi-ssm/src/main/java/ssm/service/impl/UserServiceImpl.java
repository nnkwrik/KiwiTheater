package ssm.service.impl;

import ssm.dao.RoomMapper;
import ssm.dao.UserMapper;
import ssm.model.User;
import ssm.service.UserService;
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
