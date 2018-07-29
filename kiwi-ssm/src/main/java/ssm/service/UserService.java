package ssm.service;

import ssm.model.User;

public interface UserService {

    User findUserById(Integer userid);

    void updateUserInfo(User user);
}
