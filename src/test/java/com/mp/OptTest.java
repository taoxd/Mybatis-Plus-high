package com.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mp.dao.UserMapper;
import com.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class OptTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void updateById() {
        /*
        UPDATE user SET age=35, update_time='2019-10-28T23:04:48.673', version=2
         WHERE id=1088248166370832385 AND version=1 AND deleted=0;
         */

        //假设数据库中version是1
        int version = 1;

        User user = new User();
        user.setAge(39);
        user.setId(1088248166370832385L);
        user.setVersion(version);
        int rows = userMapper.updateById(user);
        System.out.println("影响行数: " + rows);
    }

    @Test
    public void update() {

        /*
        UPDATE user SET email='250567492@qq.com', update_time='2019-10-28T23:18:32.052', version=3
        WHERE deleted=0 AND name = '刘明超' AND version = 2;
         */
        int version = 2;

        User user = new User();
        user.setEmail("250567492@qq.com");
        user.setVersion(version);
        QueryWrapper<User> query = Wrappers.<User>query();
        query.eq("name", "刘明超");

        int rows = userMapper.update(user, query);
        System.out.println("影响行数: " + rows);

        //wrapper复用出错
        /*
        UPDATE user SET email='491452552@qq.com', update_time='2019-10-28T23:18:32.090', version=4
         WHERE deleted=0 AND name = '刘明超' AND version = 2 AND age = 31 AND version = 3;
         */
        int version2 = 3;
        User user2 = new User();
        user2.setEmail("491452552@qq.com");
        user2.setVersion(version2);
        query.eq("age", 31);

        int rows2 = userMapper.update(user2, query);
        System.out.println("影响行数: " + rows2);
    }
}
