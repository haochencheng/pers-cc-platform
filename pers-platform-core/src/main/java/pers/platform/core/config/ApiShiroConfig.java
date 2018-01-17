package pers.platform.core.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import pers.platform.core.realm.ApiShiroRealm;
import pers.platform.core.realm.RetryLimitHashedCredentialsMatcher;

import java.util.LinkedHashMap;
import java.util.Map;

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

    /**
     * Shiro的Web过滤器Factory 命名:shiroFilter<br />
     * * * @param securityManager * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(
            SecurityManager securityManager) {
        return null;
    }

    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher credentialsMatcher() {
        return new RetryLimitHashedCredentialsMatcher(getCacheManage());
    }

    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;
    }

    @Bean
    public CookieRememberMeManager rememberManager() {
        CookieRememberMeManager meManager = new CookieRememberMeManager();
        meManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        meManager.setCookie(rememberMeCookie());
        return meManager;
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
