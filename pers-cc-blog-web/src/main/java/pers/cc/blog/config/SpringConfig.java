package pers.cc.blog.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
// @PropertySources({ // 多配置文件
// @PropertySource(value = "classpath:jdbc.properties", ignoreResourceNotFound =
// true),
// @PropertySource(value = "httpclient.properties", ignoreResourceNotFound =
// true) })
@PropertySource(value = "classpath:jdbc.properties", ignoreResourceNotFound = true)
@MapperScan("pers.cc.blog.repository") // mybatis自动扫描repository
@ComponentScan(value = "pers.cc.blog") // 自动扫描包
@SpringBootApplication
public class SpringConfig extends SpringBootServletInitializer {

    @Value("${jdbc.driverClassName}")
    private String driverClassName;
    @Value("${jdbc.jdbcUrl}")
    private String jdbcUrl;
    @Value("${jdbc.jdbcUserName}")
    private String jdbcUserName;
    @Value("${jdbc.jdbcPaassword}")
    private String jdbcPaassword;

    @Bean(destroyMethod = "close")
    public DruidDataSource druidDatasource() {
        DruidDataSource druidDatasource = new DruidDataSource();
        // 数据库驱动
        druidDatasource.setDriverClassName(driverClassName);
        // 数据库连接地址
        druidDatasource.setUrl(jdbcUrl);
        // 数据库用户名
        druidDatasource.setUsername(jdbcUserName);
        // 数据库密码
        druidDatasource.setPassword(jdbcPaassword);
        // 数据库中未使用的连接最大存活时间 ,单位是分 , 默认值 60 ,如果要永远存货这只为0
        druidDatasource.setMaxActive(100);
        // 每个分区最小的连接数
        druidDatasource.setMinIdle(3);
        return druidDatasource;
    }

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(SpringConfig.class);
    }

}
