package pers.cc.blog.config;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
// @PropertySources({ // 多配置文件
// @PropertySource(value = "classpath:spring.datasource.properties",
// ignoreResourceNotFound =
// true),
// @PropertySource(value = "httpclient.properties", ignoreResourceNotFound =
// true) })
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
@ComponentScan(value = "pers.cc.blog") // 自动扫描包
@MapperScan("pers.cc.blog.repository") // mybatis自动扫描repository
@SpringBootApplication
public class SpringConfig extends SpringBootServletInitializer {

    @Value("${spring.datasource.druid.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    @Value("${spring.datasource.username}")
    private String jdbcUserName;
    @Value("${spring.datasource.password}")
    private String jdbcPassword;

    @Value(value = "${spring.datasource.initialSize}")
    private String initialSize;

    @Value(value = "${spring.datasource.minIdle}")
    private String minIdle;

    @Value(value = "${spring.datasource.maxActive}")
    private String maxActive;

    @Value(value = "${spring.datasource.maxWait}")
    private String maxWait;

    /**
     * 注册DruidServlet
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        return servletRegistrationBean;
    }

    /**
     * 注册DruidFilter拦截
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean druidFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        Map<String, String> initParams = new HashMap<String, String>();
        // 设置忽略请求
        initParams.put("exclusions",
                "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.setInitParameters(initParams);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    /**
     * 配置DataSource
     * 
     * @return
     * @throws SQLException
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid.one")
    public DruidDataSource dataSourceOne() {
        return DruidDataSourceBuilder.create().build();
    }

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(SpringConfig.class);
    }

}
