package com.example.demo;

import com.example.demo.Model.HelloModel;
import com.example.demo.Service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class DemoApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    @Autowired
    private HelloService helloService;

    @Test
    public void selectAll() {
        List<?> list = helloService.selectAll();
        logger.info("get list= {}", list);
        assert list.size() >= 1;
    }

    @Test
    public void insertOne() {
        String uuid = UUID.randomUUID().toString();
        Boolean res = helloService.insert(new HelloModel(uuid, uuid));
        logger.info("insert result= {}", res);
        assert res;
    }
}
