package cn.evan.ssm.service.impl;

import cn.evan.ssm.dao.UserMapper;
import cn.evan.ssm.model.User;
import cn.evan.ssm.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findUserByUserNameAndPassword(User user) {
        return userMapper.findUserByUserNameAndPassword(user);
    }
}
