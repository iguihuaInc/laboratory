package com.zhangyue.laboratory.common.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author Zhang Yong
 * @date 2019-12-02
 */
public class RoutingDataSourceAdvice extends AbstractRoutingDataSource {

    private static final Logger logger = LoggerFactory.getLogger(RoutingDataSourceAdvice.class);

    public static String selectMasterKey = "master";
    public static String selectSlaveKey = "slaves";
    /**
     * 线程变量 key
     */
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     * master data source
     */
    private DataSource master;

    /**
     * slaves data source
     */
    private List<DataSource> slaves;

    @Override
    protected Object determineCurrentLookupKey() {
        String selectKey = threadLocal.get();
        if (StringUtils.isEmpty(selectKey)) {
            logger.error("get dataSource failed,select key is null");
            return master;
        }
        if (selectKey.equals(selectMasterKey)) {
            logger.info("get dataSource select master key,name {}", selectKey);
            return master;
        }
        if (selectKey.equals(selectSlaveKey)) {
            logger.info("get dataSource select slave key,name {}", selectKey);
            if (CollectionUtils.isEmpty(slaves)) {
                return master;
            }
            return slaves.get(0);
        }
        return null;
    }

    public static void setSelector(String selectName) {
        logger.info("set datasource select,select name {}", selectName);
        threadLocal.set(selectName);
    }

    public DataSource getMaster() {
        return master;
    }

    public void setMaster(DataSource master) {
        this.master = master;
    }

    public List<DataSource> getSlaves() {
        return slaves;
    }

    public void setSlaves(List<DataSource> slaves) {
        this.slaves = slaves;
    }
}
