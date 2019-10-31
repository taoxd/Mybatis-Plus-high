package com.mp;

import com.mp.dao.UserMapper;
import com.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

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

    @Test
    public void insertBatch(){
        /*
    INSERT INTO USER ( id, NAME, email, manager_id, create_time, update_time, version )
    VALUES
	( 1189890404175163393, '周周3', NULL, 1088248166370832385, '2019-10-31T21:02:34.111', NULL, NULL ),
	( 1189890404191940609, '哈哈4', NULL, 1088248166370832385, '2019-10-31T21:02:34.112', NULL, NULL )
         */
        User user1 = new User();
        user1.setName("周周3");
        user1.setAge(34);
        user1.setManagerId(1088248166370832385L);

        User user2 = new User();
        user2.setName("哈哈4");
        user2.setAge(29);
        user2.setManagerId(1088248166370832385L);

        List<User> userList = Arrays.asList(user1, user2);
        int rows = userMapper.insertBatchSomeColumn(userList);
        System.out.println("影响行数: " + rows);
    }

}
