package com.zhangyue.laboratory.common.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import com.zhangyue.laboratory.common.datasource.RoutingDataSourceAdvice;

/**
 * @author Zhang Yong
 * @date 2019-12-02
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class MybatisWriterInterceptor implements Interceptor {

    private static Logger logger = LoggerFactory.getLogger(MybatisWriterInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        logger.info("execute mybatis writer interceptor ... ...");
        RoutingDataSourceAdvice.setSelector(RoutingDataSourceAdvice.selectMasterKey);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        logger.info("add mybatis plugin ... ...");
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
