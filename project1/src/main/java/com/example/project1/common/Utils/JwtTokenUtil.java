package com.example.project1.common.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.Map;

/**
 * jwt生成token
 *
 * @Author RookieDe
 * @Date 2019/6/24 22:48
 * @Version 1.0
 */
public class JwtTokenUtil {


    @Autowired
    private static RedisUtil redisUtil;

    //寻找证书文件
    private static InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("jwt.jks");
    private static PrivateKey privateKey = null;
    private static PublicKey publicKey = null;

    //将证书里面的私钥公钥拿出来
    static {
        try {
            //java key store 固定常量
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(inputStream, "123456".toCharArray());
            //jwt为命令生成整数文件时的别名
            privateKey = (PrivateKey) keyStore.getKey("jwt", "123456".toCharArray());
            publicKey = keyStore.getCertificate("jwt").getPublicKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成Token
     *
     * @param subject（主体信息）
     * @param expirationSeconds 过期时间（秒）
     * @return string
     * @Date 2019/6/24 23:01
     */
    public static String generateToken(String subject, int expirationSeconds,Map<String,Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000))
//                      .signWith(SignatureAlgorithm.ES512,salt)//不使用公钥私钥
                .signWith(SignatureAlgorithm.ES256, privateKey)
                .compact();
    }


    /**
     * 解析token,获得subject中的信息
     *
     * @param token
     * @return map
     * @Date 2019/6/24 23:06
     */
    public static String  parseToken(String token, String salt) {
        String subject = null;
        try {
            /*Claims claims = Jwts.parser()
//                    .setSigningKey(salt) // 不使用公钥私钥
                    .setSigningKey(publicKey)
                    .parseClaimsJws(token).getBody();*/
            subject = getTokenBody(token).getSubject();
        } catch (Exception e) {
        }
        return subject;
    }

    /**
     * 获取token自定义属性
     *
     * @param token
     * @return map
     * @Date 2019/6/24 23:07
     */
    public static Map<String, Object> getClaims(String token) {
        Map<String, Object> claims = null;
        try {
            claims = getTokenBody(token);
        } catch (Exception e) {
        }
        return claims;
    }

    /**
     * 是否已过期
     * @param expirationTime
     * @return
     */
    public static boolean isExpiration(String expirationTime) {
        //return getTokenBody(token).getExpiration().before(new Date());
        //通过redis中的失效时间进行判断
        String currentTime = DateUtil.getTime();
        if(DateUtil.compareDate(currentTime,expirationTime)) {
            //当前时间比过期时间小，失效
            return true;
        }else {
            return false;
        }
    }


    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(token)
                .getBody();
    }


}
