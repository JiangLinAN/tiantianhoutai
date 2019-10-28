package com.noe.service;

import com.noe.dao.GoodDAO;
import com.noe.pojo.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:nore
 */
@Service
public class GoodServiceImpl implements GoodService{
    @Autowired
    private GoodDAO goodDAO;
    @Override
    public List<Good> findAllGood() {
        return goodDAO.findAllGood();
    }

    @Override
    public Good queryGoodById(Integer id) {
        return goodDAO.queryGoodById(id);
    }

    @Override
    public Integer deleGoodById(Integer id) {
        return deleGoodById(id);
    }

    @Override
    public Integer updateGood(Good good) {
        return goodDAO.updateGood(good);
    }

    @Override
    public Integer insertGood(Good good) {
        return goodDAO.insertGood(good);
    }
}
