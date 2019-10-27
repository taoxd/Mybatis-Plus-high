package com.mp;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mp.dao.UserMapper;
import com.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MyTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void deleteById() {
        //UPDATE user SET deleted=1 WHERE id=1094592041087729666 AND deleted=0;
        int rows = userMapper.deleteById(1094592041087729666L);
        System.out.println("影响行数: " + rows);
    }

    @Test
    public void select() {
        //没加注解@TableField(select = false)，只查询未删除的
        //SELECT id,name,age,email,manager_id,create_time,update_time,version,deleted FROM user WHERE deleted=0

        //加注解@TableField(select = false)之后
        //SELECT id,name,age,email,manager_id,create_time,update_time,version FROM user WHERE deleted=0
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void updateById() {
        //只更新未删除的
        //UPDATE user SET age=26 WHERE id=1088248166370832385 AND deleted=0;
        User user = new User();
        user.setAge(26);
        user.setId(1088248166370832385L);
        int rows = userMapper.updateById(user);
        System.out.println("影响行数: " + rows);
    }

    @Test
    public void mySelect() {
        //自定义sql，@TableField(select = false)无效，删除的也能查询出来
        //select * FROM user WHERE age > 25;
        List<User> list = userMapper.mySelectList(Wrappers.<User>lambdaQuery().gt(User::getAge, 25));
        list.forEach(System.out::println);

        //只能自己加限定条件
        userMapper.mySelectList(Wrappers.<User>lambdaQuery().gt(User::getAge, 25).eq(User::getDeleted, 0));
    }
}
