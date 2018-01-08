package pers.platform.monitor.realm;

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
import pers.platform.monitor.api.UserService;
import pers.platform.monitor.model.User;

import javax.annotation.Resource;

/**
 * 自定义Realm
 * 
 * @author Administrator
 *
 */
public class MonitorShiroRealm extends AuthorizingRealm {

    Logger logger = LoggerFactory.getLogger(MonitorShiroRealm.class);

    @Resource
    private UserService userService;

    public MonitorShiroRealm(CacheManager cacheManager, CredentialsMatcher matcher) {
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
        logger.info("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        User bloggerInfo = (User) principals.getPrimaryPrincipal();
        logger.info(bloggerInfo.toString());
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
        logger.info("MonitorShiroRealm.doGetAuthenticationInfo()");
        String userName = (String) token.getPrincipal();
        User user = userService.getUserByUserNameOrPhoneOrEmail(userName,
                userName, userName);
        if (user == null) {
            // 抛出 帐号找不到异常
            throw new UnknownAccountException();
        }
        // 判断是否用户被锁定
        if (user.isLocked()) {
            // 抛出 帐号锁定异常
            throw new LockedAccountException();
        }
        // 将当前用户放入缓存
        SecurityUtils.getSubject().getSession().setAttribute("user", user);
        return new SimpleAuthenticationInfo(user.getPrincipal(),
                user.getPassword(), ByteSource.Util.bytes(user.getCredentials()),
                getName());
    }

}
