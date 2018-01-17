package pers.platform.core.auth.realm;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.atomic.AtomicInteger;

public class RetryLimitHashedCredentialsMatcher
        extends HashedCredentialsMatcher  {

    private Cache<String, AtomicInteger> apiRetryCache;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        this.setHashAlgorithmName("MD5");
        this.setHashIterations(3);
        this.setStoredCredentialsHexEncoded(true);
        this.apiRetryCache = cacheManager.getCache("apiRetryCache");
    }

    //登陆失败10次讲被锁定10分钟
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token,
            AuthenticationInfo info) {
        String apiKey = (String) token.getPrincipal();
        // retry count + 1
        AtomicInteger retryCount = apiRetryCache.get(apiKey);
        if (retryCount == null) {
            retryCount = new AtomicInteger(0);
            apiRetryCache.put(apiKey, retryCount);
        }
        if (retryCount.incrementAndGet() > 10) {
            // if retry count > 5 throw
            throw new ExcessiveAttemptsException();
        }

        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            // clear retry count
            apiRetryCache.remove(apiKey);
        }
        return matches;
    }

}
