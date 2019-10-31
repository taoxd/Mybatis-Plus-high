package com.mp;

import com.mp.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class InjectorTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void deleteAll() {
        //delete from user
        int rows = userMapper.deleteAll();
        System.out.println("影响行数: " + rows);
    }

}
