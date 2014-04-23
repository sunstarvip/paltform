package com.darknight.security.shiro;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import javax.security.auth.login.AccountLockedException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 如在1个小时内密码最多重试5次，如果尝试次数超过5次就锁定1小时，
 * 1小时后可再次重试，如果还是重试失败，可以锁定如1天，以此类推，防止密码被暴力破解
 * Created by DarKnight on 2014/4/23 0023.
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
    private Ehcache passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher() {
        CacheManager cacheManager = CacheManager.newInstance(CacheManager.class.getClassLoader().getResource("ehcache.xml"));
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String)token.getPrincipal();
        //retry count + 1
        Element element = passwordRetryCache.get(username);
        if(element == null) {
            element = new Element(username , new AtomicInteger(0));
            passwordRetryCache.put(element);
        }
        AtomicInteger retryCount = (AtomicInteger)element.getObjectValue();
        if(retryCount.incrementAndGet() > 5) {
            //if retry count > 5 throw
            throw new ExcessiveAttemptsException();
        }

        boolean matches = super.doCredentialsMatch(token, info);
        if(matches) {
            //clear retry count
            passwordRetryCache.remove(username);
        }
        return matches;
    }
}
