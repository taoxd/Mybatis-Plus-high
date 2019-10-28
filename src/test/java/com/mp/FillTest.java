package com.mp;

import com.mp.dao.UserMapper;
import com.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class FillTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        //插入中文有可能出现乱码，配置文件url中添加 characterEncoding=UTF-8 即可
        /*
        INSERT INTO USER ( id, NAME, age, email, manager_id, create_time )
        VALUES
	    ( 1188800863792046081, '刘明超', 31, '250567492@qq.com', 1088248166370832385, '2019-10-28T20:53:07.441' );
         */
        User user = new User();
        user.setName("刘明超");
        user.setAge(31);
        user.setEmail("250567492@qq.com");
        user.setManagerId(1088248166370832385L);
        int rows = userMapper.insert(user);
        System.out.println("影响行数: " + rows);
    }


    @Test
    public void updateById() {
        /*
        UPDATE user SET age=27, update_time='2019-10-28T20:57:40.554'
         WHERE id=1088248166370832385 AND deleted=0;
         */
        User user = new User();
        user.setAge(29);
        user.setId(1088248166370832385L);
        //设值了更新时间，就不会走自动填充处理器
        user.setUpdateTime(LocalDateTime.now());
        int rows = userMapper.updateById(user);
        System.out.println("影响行数: " + rows);
    }

}
