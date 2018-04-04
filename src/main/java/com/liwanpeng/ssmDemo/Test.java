package com.liwanpeng.ssmDemo;

import com.liwanpeng.ssmDemo.pojo.TestPojo;
import com.liwanpeng.ssmDemo.service.TestService;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by liwanpeng on 2018/4/4.
 */
//整合junit和spring，让junit在启动时候加载springIOC容器
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件,需要用到的配置文件，如果是dao的话可以不用spring-service.xml
@ContextConfiguration({ "classpath:ssmDemoResource/spring-mybatis.xml"})
public class Test {
    @Autowired
    private TestService testService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @org.junit.Test
    public void testGetSeckillList() {
        List<TestPojo> seckills = testService.getAllTest();
        logger.info("list={}", seckills);
    }

}
