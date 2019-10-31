package com.mp.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
/**
* @Description:    定义方法
* @Author:         taoxudong
* @CreateDate:     2019/10/31 20:28
* @Version:        1.0
*/
public class DeleteAllMethod extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        //执行的sql
        String sql = "delete from " + tableInfo.getTableName();
        //mapper接口方法名
        String method = "deleteAll";

        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);

        return addDeleteMappedStatement(mapperClass, method, sqlSource);
    }
}
