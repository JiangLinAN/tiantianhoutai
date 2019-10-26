package com.noe.dao;

import com.noe.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDAO {
    //根据名字查询用户信息
    User queryUserByname(@Param("username") String username);
}
