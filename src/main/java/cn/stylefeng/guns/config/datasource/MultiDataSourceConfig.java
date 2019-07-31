package cn.stylefeng.guns.config.datasource;

import java.sql.SQLException;
import java.util.HashMap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

import cn.stylefeng.guns.config.datasource.extra.DataConnectionProperties;
import cn.stylefeng.guns.config.datasource.extra.MultiDataSourceExtraProperties;
import cn.stylefeng.guns.core.common.constant.DatasourceEnum;
import cn.stylefeng.roses.core.config.properties.DruidProperties;
import cn.stylefeng.roses.core.datascope.DataScopeInterceptor;
import cn.stylefeng.roses.core.mutidatasource.DynamicDataSource;
import cn.stylefeng.roses.core.mutidatasource.aop.MultiSourceExAop;

/**
 * 多数据源配置<br/>
 * <p>
 * 注：由于引入多数据源，所以让spring事务的aop要在多数据源切换aop的后面
 *
 * @author stylefeng
 * @Date 2017/5/20 21:58
 */
@Configuration
@ConditionalOnProperty(prefix = "guns.muti-datasource", name = "open", havingValue = "true")
@EnableTransactionManagement(order = 2, proxyTargetClass = true)
@MapperScan(basePackages = {"cn.stylefeng.guns.modular.*.mapper"})
public class MultiDataSourceConfig {

    /**
     * druid配置
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidProperties druidProperties() {
        return new DruidProperties();
    }
    
    /**
     * 多数据源配置 扩展配置
     */
    @Bean
    @ConfigurationProperties(prefix = "guns.muti-datasource")
    public MultiDataSourceExtraProperties multiDataSourceExtraProperties() {
        return new MultiDataSourceExtraProperties();
    }


    /**
     * 多数据源切换的aop
     */
    @Bean
    public MultiSourceExAop multiSourceExAop() {
        return new MultiSourceExAop();
    }

    /**
     * guns的数据源
     */
    private DruidDataSource dataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }



    /**
     * 多数据源连接池配置
     */
    @Bean
    public DynamicDataSource mutiDataSource(DruidProperties druidProperties,MultiDataSourceExtraProperties multiDataSourceExtraProperties) {

        HashMap<Object, Object> hashMap = new HashMap<>();
        DruidDataSource dataSourceGuns = dataSource(druidProperties);
        try{

            dataSourceGuns.init();
            hashMap.put(DatasourceEnum.DATA_SOURCE_GUNS,dataSourceGuns);

            for(DataConnectionProperties dataConnectionProperties:multiDataSourceExtraProperties.getDataConnectionPropertiesList()){
                DruidDataSource dataSource = new DruidDataSource();
                druidProperties.config(dataSource);
                dataConnectionProperties.config(dataSource);
                dataSource.init();
                hashMap.put(dataConnectionProperties.getDataSourceName(),dataSource);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        DynamicDataSource dynamicDataSource = new DynamicDataSource();

        dynamicDataSource.setTargetDataSources(hashMap);
        dynamicDataSource.setDefaultTargetDataSource(dataSourceGuns);
        return dynamicDataSource;
    }

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 数据范围mybatis插件
     */
    @Bean
    public DataScopeInterceptor dataScopeInterceptor() {
        return new DataScopeInterceptor();
    }

    /**
     * 乐观锁mybatis插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}