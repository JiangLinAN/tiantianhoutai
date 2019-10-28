package com.noe.dao;

import com.noe.pojo.Good;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodDAO {
    //查询所有
    List<Good> findAllGood();
    //根据id
    Good queryGoodById(@Param("id") Integer id);
    //删除
    Integer deleGoodById(@Param("id") Integer id);
    //修改
    Integer updateGood(Good good);
    //添加
    Integer insertGood(Good good);
}
