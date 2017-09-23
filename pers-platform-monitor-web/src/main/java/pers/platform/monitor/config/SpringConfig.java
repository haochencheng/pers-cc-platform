package pers.platform.monitor.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
// @PropertySources({ // 多配置文件
// @PropertySource(value = "classpath:jdbc.properties", ignoreResourceNotFound =
// true),
// @PropertySource(value = "httpclient.properties", ignoreResourceNotFound =
// true) })
@PropertySource(value = "classpath:jdbc.properties", ignoreResourceNotFound = true)
@ComponentScan(basePackages = "pers.monitor.*") // 自动扫描包
@SpringBootApplication
public class SpringConfig extends SpringBootServletInitializer {

    // @Primary
    // @Bean
    // public DataSource dataSourceOne(){
    // return DruidDataSourceBuilder.create().build();
    // }

    // private ServletRegistrationBean servletRegistrationBean;
    //
    // /**
    // * 注册DruidServlet
    // *
    // * @return
    // */
    // @Bean
    // public ServletRegistrationBean druidServletRegistrationBean() {
    // if (servletRegistrationBean == null) {
    // servletRegistrationBean = new ServletRegistrationBean();
    // servletRegistrationBean.setServlet(new StatViewServlet());
    // servletRegistrationBean.addUrlMappings("/druid/*");
    // }
    // return servletRegistrationBean;
    // }
    //
    // private FilterRegistrationBean filterRegistrationBean;
    //
    // /**
    // * 注册DruidFilter拦截
    // *
    // * @return
    // */
    // @Bean
    // public FilterRegistrationBean druidFilterRegistrationBean() {
    // if (filterRegistrationBean == null) {
    // filterRegistrationBean = new FilterRegistrationBean();
    // filterRegistrationBean.setFilter(new WebStatFilter());
    // Map<String, String> initParams = new HashMap<String, String>();
    // // 设置忽略请求
    // initParams.put("exclusions",
    // "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*,/static/*");
    // filterRegistrationBean.setInitParameters(initParams);
    // filterRegistrationBean.addUrlPatterns("/*");
    // }
    // return filterRegistrationBean;
    // }

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(SpringConfig.class);
    }

}
