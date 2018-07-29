package ssm.service;

import ssm.exception.CustomException;
import ssm.model.User;

public interface AccountService {
    User signin(String username, String password) throws CustomException;

    void checkNamePass(String username, String password) throws CustomException;

    User signup(String username, String password);
}
