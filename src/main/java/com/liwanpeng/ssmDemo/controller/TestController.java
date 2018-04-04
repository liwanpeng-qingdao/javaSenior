package com.liwanpeng.ssmDemo.controller;

import com.liwanpeng.ssmDemo.pojo.TestPojo;
import com.liwanpeng.ssmDemo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by liwanpeng on 2018/4/4.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;
    @ResponseBody
    @RequestMapping("/getAll")
    public List<TestPojo> getAll(){
        List<TestPojo> allTest = testService.getAllTest();
        return allTest;
    }
}
