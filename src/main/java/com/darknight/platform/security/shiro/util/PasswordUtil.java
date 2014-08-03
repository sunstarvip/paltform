package com.darknight.platform.security.shiro.util;

import com.darknight.platform.account.user.entity.User;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by DarKnight on 14-1-31.
 */
public class PasswordUtil {
    //生成随机数生成器
    private static final RandomNumberGenerator rng = new SecureRandomNumberGenerator();
    private static String algorithmName = "md5";
    private static final int hashIterations = 2;

    //通过随机数生成器获得用户的Salt
    public static String getSalt() {
        //获得加密使用的Salt
        String salt = Base64.encodeToString(rng.nextBytes().getBytes());
        return salt;
    }

    //获得通过MD5加密处理的密码
    public static String getPassword(User user) {
        String newstr = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),
                hashIterations).toHex();
        user.setPassword(newstr);
        return newstr;
    }
}
