package com.bn.admin.utils;

import com.bn.admin.PasswordData;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

public class PasswordUtil {
    /** 盐生成 */
    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    /** 加密算法 */
    public static final String algorithmName = "md5";
    /** 算法运算次数 */
    public static final int hashIterations = 2;

    /**
     * 使用盐加密密码
     */
    public static PasswordData encryptPassword(String passwrod){
        PasswordData passwordData = new PasswordData();
        // 随机盐
        String salt = randomNumberGenerator.nextBytes().toString();
        passwordData.setSalt(salt);
        //将用户的注册密码经过散列算法替换成一个不可逆的新密码保存进数据，使用过程使用了盐
        String newPassword = new SimpleHash(algorithmName, passwrod, salt, hashIterations).toString();
        passwordData.setPassword(newPassword);
        return passwordData;
    }

    public static String encryptPassword(String password, String salt){
        //将用户的注册密码经过散列算法替换成一个不可逆的新密码保存进数据，使用过程使用了盐
        String newPassword = new SimpleHash(algorithmName, password, salt, hashIterations).toString();
        return newPassword;
    }

}
