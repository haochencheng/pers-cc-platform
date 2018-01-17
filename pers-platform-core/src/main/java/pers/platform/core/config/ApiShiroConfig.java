package pers.platform.core.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import pers.platform.core.auth.realm.ApiShiroRealm;
import pers.platform.core.auth.realm.RetryLimitHashedCredentialsMatcher;

@Configuration
public class ApiShiroConfig {

    private final Logger logger = LoggerFactory.getLogger(ApiShiroConfig.class);

    @Autowired
    SecurityManager securityManager;

    /**
     * shiro缓存管理器; 需要注入对应的其它的实体类中： 1、安全管理器：securityManager
     * 可见securityManager是整个shiro的核心；
     *
     * @return
     */
    @Bean(name = "cacheShiroManager")
    public EhCacheManager getCacheManage() {
        EhCacheManager cacheManager = new EhCacheManager();
        return cacheManager;
    }

    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher credentialsMatcher() {
        return new RetryLimitHashedCredentialsMatcher(getCacheManage());
    }

    @Bean
    public ApiShiroRealm monitorShiroRealm() {
        ApiShiroRealm realm = new ApiShiroRealm(getCacheManage(),
                credentialsMatcher());
        realm.setName("apiShiroRealm");
        realm.setAuthenticationCache(
                getCacheManage().getCache(realm.getName()));
        return realm;
    }


    @Bean
    public MethodInvokingFactoryBean getMethodInvokingFactoryBean() {
        MethodInvokingFactoryBean factoryBean = new MethodInvokingFactoryBean();
        factoryBean.setStaticMethod(
                "org.apache.shiro.SecurityUtils.setSecurityManager");
        factoryBean.setArguments(new Object[] { securityManager });
        return factoryBean;
    }

    /**
     * Shiro生命周期处理器
     * 
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * 
     * @return
     */
    @Bean
    @DependsOn({ "lifecycleBeanPostProcessor" })
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }


}
