package pers.platform.core.auth.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pers.platform.core.auth.model.ApiUserAuth;
import pers.platform.core.auth.repository.ApiUserAuthRepo;

import java.util.List;

/**
 * 自定义Realm
 * 
 * @author Administrator
 *
 */
public class ApiShiroRealm extends AuthorizingRealm {

    Logger logger = LoggerFactory.getLogger(ApiShiroRealm.class);

    @Autowired
    private ApiUserAuthRepo apiUserAuthRepo;

    public ApiShiroRealm(CacheManager cacheManager, CredentialsMatcher matcher) {
        super(cacheManager, matcher);
    }

    /*
     * (non-Javadoc) 为当前的登录的用户授予角色和权限
     * 
     * @see
     * org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache
     * .shiro.subject.PrincipalCollection)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        logger.info("权限验证->ApiShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        User bloggerInfo = (User) principals.getPrimaryPrincipal();
//        logger.info(bloggerInfo.toString());
        return simpleAuthorizationInfo;
    }

    /*
     * (non-Javadoc) 验证当前登录的用户
     * 
     * @see
     * org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.
     * apache.shiro.authc.AuthenticationToken)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) {
        logger.info("ApiShiroRealm.doGetAuthenticationInfo()");
        String apiKey = (String) token.getPrincipal();
        String apiSercuty= (String) token.getCredentials();
        ApiUserAuth apiUserAuth = apiUserAuthRepo.getAllByApiKeyAndApiSecret(apiKey, apiSercuty);

        if (apiUserAuth == null) {
            // 抛出 帐号找不到异常
            throw new UnknownAccountException();
        }
        // 判断是否用户被锁定
        if (apiUserAuth.isLocked()) {
            // 抛出 帐号锁定异常
            throw new LockedAccountException();
        }
        return new SimpleAuthenticationInfo(apiKey,
                apiSercuty,
                getName());
    }

}
