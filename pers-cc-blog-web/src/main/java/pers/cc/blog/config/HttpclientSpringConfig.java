package pers.cc.blog.config;

import java.util.concurrent.TimeUnit;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.IdleConnectionEvictor;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class HttpclientSpringConfig {

    @Value("${http.maxTotal}")
    private Integer httpMaxTotal;

    @Value("${http.defaultMaxPerRoute}")
    private Integer httpDefaultMaxPerRoute;

    @Value("${http.connectTimeout}")
    private Integer httpConnectTimeout;

    @Value("${http.connectionRequestTimeout}")
    private Integer httpConnectionRequestTimeout;

    @Value("${http.socketTimeout}")
    private Integer httpSocketTimeout;

    @Value("${http.maxIdleTime}")
    private Integer maxIdleTime;
    @Value("${http.validateAfterInactivityTime}")
    private Integer validateAfterInactivityTime;

    @Autowired
    private PoolingHttpClientConnectionManager manager;

    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        // 最大连接数
        poolingHttpClientConnectionManager.setMaxTotal(httpMaxTotal);
        // 每个主机的最大并发数
        poolingHttpClientConnectionManager
                .setDefaultMaxPerRoute(httpDefaultMaxPerRoute);
        // 连接前测试是否可用（以毫秒为单位）
        poolingHttpClientConnectionManager
                .setValidateAfterInactivity(validateAfterInactivityTime);
        return poolingHttpClientConnectionManager;
    }

    // 定期关闭无效连接
    @Bean
    public IdleConnectionEvictor idleConnectionEvictor() {
        return new IdleConnectionEvictor(manager, maxIdleTime,
                TimeUnit.SECONDS);
    }

    // 定义Httpclient对
    @Bean
    @Scope("prototype")
    // 多例对象
    public CloseableHttpClient closeableHttpClient() {
        return HttpClients.custom().setConnectionManager(this.manager).build();
    }

    // 请求配置
    @Bean
    public RequestConfig requestConfig() {
        return RequestConfig.custom().setConnectTimeout(httpConnectTimeout) // 创建连接的最长时间
                .setConnectionRequestTimeout(httpConnectionRequestTimeout) // 从连接池中获取到连接的最长时间
                .setSocketTimeout(httpSocketTimeout) // 数据传输的最长时间
                .build();
    }

}
