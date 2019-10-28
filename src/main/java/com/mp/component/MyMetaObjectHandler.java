package com.mp.component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Description: 自动填充处理器
 * @Author: taoxudong
 * @CreateDate: 2019/10/28 20:44
 * @Version: 1.0
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //有属性的时候才执行
        boolean hasSetter = metaObject.hasSetter("createTime");
        if (hasSetter) {
            System.out.println("insertFill...");
            setInsertFieldValByName("createTime", LocalDateTime.now(), metaObject);
            //setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //如果updateTime为空，就设值，否则不设值
        Object val = getFieldValByName("updateTime", metaObject);
        if (val == null) {
            System.out.println("updateFill...");
            setUpdateFieldValByName("updateTime", LocalDateTime.now(), metaObject);
            //setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }
}
