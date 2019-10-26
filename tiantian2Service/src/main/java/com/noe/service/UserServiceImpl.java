package com.noe.service;

import com.noe.dao.UserDAO;
import com.noe.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:nore
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public User queryUserByname(String name) {
        return userDAO.queryUserByname(name);
    }
}
