package com.mp.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.additional.AlwaysUpdateSomeColumnById;
import com.baomidou.mybatisplus.extension.injector.methods.additional.InsertBatchSomeColumn;
import com.baomidou.mybatisplus.extension.injector.methods.additional.LogicDeleteByIdWithFill;
import com.mp.method.DeleteAllMethod;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 注入器
 * @Author: taoxudong
 * @CreateDate: 2019/10/31 20:28
 * @Version: 1.0
 */
@Component
public class MySqlInjector extends DefaultSqlInjector {
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        //必须先获取父类方法集合，否则会失效
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        //SQL注入自定义方法
        methodList.add(new DeleteAllMethod());
        //排除逻辑删除的字段和age字段
        methodList.add(new InsertBatchSomeColumn(t -> !t.isLogicDelete() && !t.getColumn().equals("age")));
        //逻辑删除并填充
        methodList.add(new LogicDeleteByIdWithFill());
        //排除name字段
        methodList.add(new AlwaysUpdateSomeColumnById(t -> !t.getColumn().equals("name")));
        return methodList;
    }
}
