package com.darknight.security.shiro.password;

import com.darknight.account.user.entity.User;
import com.darknight.account.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by DarKnight on 2014/4/29 0029.
 */
@Component
public class PasswordMaker {
    private String algorithmName = AlgorithmName.MD5;
    private final int hashIterations = 2;

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public User encryptPassword(User user) {
        if(StringUtils.isEmpty(user.getSalt())) {
            user.setSalt(randomNumberGenerator.nextBytes().toHex());
        }

        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(userService.getCredentialsSalt(user.getAccountName())),
                hashIterations).toHex();
        user.setPassword(newPassword);
        return user;
    }

    public interface AlgorithmName {
        public static String MD5 = "md5";// md5加密规则
    }
}
