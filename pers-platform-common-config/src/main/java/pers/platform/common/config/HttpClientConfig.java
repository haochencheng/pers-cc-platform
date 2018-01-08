package pers.platform.common.config;

import java.util.concurrent.TimeUnit;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.IdleConnectionEvictor;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

//@Configuration
//@PropertySource(value = "classpath:httpclient.properties")
public class HttpClientConfig {

    @Value(value = "${http.maxTotal}")
    private Integer httpMaxTotal;

    @Value(value = "${http.maxIdleTime}")
    private Integer maxIdleTime;

    @Value("${http.defaultMaxPerRoute}")
    private Integer defaultMaxPerRoute;

    @Value("${http.connectTimeout}")
    private Integer connectTimeout;

    @Value("${http.connectionRequestTimeout}")
    private Integer connectionRequestTimeout;

    @Value("${http.socketTimeout}")
    private Integer socketTimeout;

    @Value("${http.validateAfterInactivityTime}")
    private Integer validateAfterInactivityTime;

    @Autowired
    private PoolingHttpClientConnectionManager manger;

    @Bean
    @ConditionalOnMissingBean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        // 最大连接数
        poolingHttpClientConnectionManager.setMaxTotal(httpMaxTotal);
        // 最大并发数
        poolingHttpClientConnectionManager
                .setDefaultMaxPerRoute(defaultMaxPerRoute);
        // 定义不活动的时间（以毫秒为单位）
        poolingHttpClientConnectionManager
                .setValidateAfterInactivity(validateAfterInactivityTime);
        return poolingHttpClientConnectionManager;
    }

    // 定期关闭无效链接
    @Bean
    public IdleConnectionEvictor idleConnectionEvictor() {
        return new IdleConnectionEvictor(manger, maxIdleTime, TimeUnit.SECONDS);
    }

    // 定义httpclient树
    @Bean
    @Scope("prototype") // 设置为多例对象
    public CloseableHttpClient closeableHttpClient() {
        return HttpClients.custom().setConnectionManager(manger).build();
    }

    // 请求配置
    @Bean
    public RequestConfig requestConfig() {
        return RequestConfig.custom().setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setSocketTimeout(socketTimeout).build();

    }

}
