package com.mp.entity;


import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Auther: taoxd
 * @Date: 2019/6/4 23:21
 * @Description: 与数据库表对应的实体类
 * 默认数据库下划线对应实体驼峰或下划线，如manager_id对应managerId
 */
@Data
public class User {

    //主键
    private Long id;
    //姓名
    private String name;
    //年龄
    private Integer age;
    //邮箱
    private String email;
    //直属上级
    private Long managerId;
    //创建时间
    private LocalDateTime createTime;
    //修改时间
    private LocalDateTime updateTime;
    //版本
    private Integer version;
    //逻辑删除标识(0未删除1已删除)
    @TableLogic
    private Integer deleted;
}

