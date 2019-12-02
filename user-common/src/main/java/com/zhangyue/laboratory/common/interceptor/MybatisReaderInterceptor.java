package com.zhangyue.laboratory.common.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import com.zhangyue.laboratory.common.datasource.RoutingDataSourceAdvice;

/**
 * @author Zhang Yong
 * @date 2019-12-02
 */
@Intercepts({@Signature(
        type = Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class MybatisReaderInterceptor implements Interceptor {
    Logger logger = LoggerFactory.getLogger(MybatisReaderInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        logger.info("mybatis reader interceptor execute ......");
        RoutingDataSourceAdvice.setSelector(RoutingDataSourceAdvice.selectSlaveKey);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
