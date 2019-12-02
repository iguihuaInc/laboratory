package com.zhangyue.laboratory.common.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhangyue.laboratory.common.datasource.RoutingDataSourceAdvice;
import com.zhangyue.laboratory.common.interceptor.MybatisReaderInterceptor;
import com.zhangyue.laboratory.common.interceptor.MybatisWriterInterceptor;

/**
 * @author Zhang Yong
 * @date 2019-12-02
 */
@Configuration
public class SqlSessionConfig {

    private static Logger logger = LoggerFactory.getLogger(SqlSessionConfig.class);

    @Value("${spring.datasource.master.type}")
    private Class<? extends DataSource> masterType;

    @Value("${spring.datasource.slaves.type}")
    private Class<? extends DataSource> slavesType;

    @ConfigurationProperties(prefix = "spring.datasource.master")
    @Bean(name = "master")
    public DataSource masterSource() {
        logger.info("build master dataSource.......,data source type {}", masterType);
        return DataSourceBuilder.create().type(masterType).build();
    }

    @ConfigurationProperties(prefix = "spring.datasource.slaves")
    @Bean(name = "slaves")
    public List<DataSource> slavesSource() {
        logger.info("build slave dataSource......,data source type {}", slavesType);
        List<DataSource> dataSources = new ArrayList<>();
        DataSource dataSource = DataSourceBuilder.create().type(slavesType).build();
        dataSources.add(dataSource);
        return dataSources;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("master") DataSource masterSource,
            @Qualifier("slaves") List<DataSource> slavesSource) throws Exception {
        RoutingDataSourceAdvice dataSourceAdvice = new RoutingDataSourceAdvice();
        dataSourceAdvice.setMaster(masterSource);
        dataSourceAdvice.setSlaves(slavesSource);
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("master", masterSource);
        //设置默认数据源
        dataSourceAdvice.setTargetDataSources(targetDataSources);
        dataSourceAdvice.setDefaultTargetDataSource(masterSource);
        dataSourceAdvice.afterPropertiesSet();

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSourceAdvice);
        sqlSessionFactoryBean.setPlugins(new MybatisWriterInterceptor(),
                new MybatisReaderInterceptor());
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
        sqlSessionFactory.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
        return sqlSessionFactory;
    }


}
