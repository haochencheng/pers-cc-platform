package pers.platform.common.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class SpringConfig extends SpringBootServletInitializer {

     @Bean
     public DataSource dataSourceOne(){
     return DruidDataSourceBuilder.create().build();
     }

     private ServletRegistrationBean servletRegistrationBean;
    //
    // /**
    // * 注册DruidServlet
    // *
    // * @return
    // */
     @Bean
     public ServletRegistrationBean druidServletRegistrationBean() {
     if (servletRegistrationBean == null) {
     servletRegistrationBean = new ServletRegistrationBean();
     servletRegistrationBean.setServlet(new StatViewServlet());
     servletRegistrationBean.addUrlMappings("/druid/*");
     }
     return servletRegistrationBean;
     }
    //
     private FilterRegistrationBean filterRegistrationBean;
    //
    // /**
    // * 注册DruidFilter拦截
    // *
    // * @return
    // */
     @Bean
     public FilterRegistrationBean druidFilterRegistrationBean() {
     if (filterRegistrationBean == null) {
     filterRegistrationBean = new FilterRegistrationBean();
     filterRegistrationBean.setFilter(new WebStatFilter());
     Map<String, String> initParams = new HashMap<String, String>();
     // 设置忽略请求
     initParams.put("exclusions",
     "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*,/static/*");
     filterRegistrationBean.setInitParameters(initParams);
     filterRegistrationBean.addUrlPatterns("/*");
     }
     return filterRegistrationBean;
     }

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(SpringConfig.class);
    }

}
