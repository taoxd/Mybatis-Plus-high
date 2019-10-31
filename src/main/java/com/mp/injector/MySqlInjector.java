package com.mp.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.mp.method.DeleteAllMethod;
import org.springframework.stereotype.Component;

import java.util.List;
/**
* @Description:    注入器
* @Author:         taoxudong
* @CreateDate:     2019/10/31 20:28
* @Version:        1.0
*/
@Component
public class MySqlInjector extends DefaultSqlInjector {
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        //必须先获取父类方法集合，否则会失效
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new DeleteAllMethod());
        return methodList;
    }
}
