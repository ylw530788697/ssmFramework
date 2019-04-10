package cn.evan.ssm.service;

import cn.evan.ssm.model.User;

public interface UserService {
    public User findUserByUserNameAndPassword(User user);
}
