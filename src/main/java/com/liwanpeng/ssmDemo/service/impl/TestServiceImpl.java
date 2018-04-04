package com.liwanpeng.ssmDemo.service.impl;

import com.liwanpeng.ssmDemo.dao.TestDao;
import com.liwanpeng.ssmDemo.pojo.TestPojo;
import com.liwanpeng.ssmDemo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liwanpeng on 2018/4/4.
 */
@Service
public class TestServiceImpl implements TestService{
    @Autowired
    private TestDao testDao;
    @Override
    public List<TestPojo> getAllTest() {
        List<TestPojo> all = testDao.getAll();
        return all;
    }
}
