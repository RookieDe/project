package com.project.project5.util;

import com.project.project5.dto.authDTO.response.AuthInfoResponse;
import com.project.project5.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName JwtUtils
 * @date 2020/4/9 10:01
 */
public class JwtUtils {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer";

    public static final String SUBJECT = "congge";

    public static final long EXPIRITION = 1000 * 2 * 60 * 60;

    public static final String APPSECRET_KEY = "congge_secret";

    public static final String ROLE_CLAIMS = "rol";

    public static String generateJsonWebToken(User user){

        if (user.getUserId() == null || user.getUserName() == null ){
            return null;
        }

        Map<String,Object> map = new HashMap<>(16);
        map.put(ROLE_CLAIMS, "rol");

        return Jwts.builder()
                .setSubject(SUBJECT)
                .setClaims(map)
                .claim("id",user.getUserId())
                .claim("name",user.getUserName())
                .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+EXPIRITION))
                .signWith(SignatureAlgorithm.HS256,APPSECRET_KEY).compact();
    }

    /**
     * 生成token
     * @param username
     * @param role
     * @return
     */
    public static String createToken(String username,String role) {
        Map<String, Object> map = new HashMap<>(16);
        map.put(ROLE_CLAIMS, role);

        return Jwts.builder().setSubject(username)
                .setClaims(map)
                .claim("username", username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
                .signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
    }


    public static Claims checkJWT(String token){
        try {
            final Claims body = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
            return body;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    public static AuthInfoResponse getUserInfo(String token){
        Claims body = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        AuthInfoResponse authInfoResponse = new AuthInfoResponse();
        authInfoResponse.setUserId(Integer.parseInt(body.get("id")+""));
        authInfoResponse.setUserName(body.get("name").toString());
        authInfoResponse.setRole(body.get("rol")+"");
        return authInfoResponse;
    }

    /**
     * 获取用户名
     * @param token
     * @return
     */
    public static String getUsername(String token){
        Claims body = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        return body.get("username").toString();
    }

    /**
     * 获取用户角色
     * @param token
     * @return
     */
    public static String getUserRole(String token){
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("rol").toString();
    }

    /**
     * 是否过期
     * @param token
     * @return
     */
    public static Boolean isExpiration(String token){
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getExpiration().before(new Date());
    }



    public static String getRemainingTokenTime(String token){
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        if (claims.getExpiration().before(new Date())){
            throw new RuntimeException("token时间过期！");
        }
        long time = claims.getExpiration().getTime() - System.currentTimeMillis();
        long minute = time/1000/60;
        return "token剩余时间:"+minute+"分钟";
    }

//    public static void main(String[] args) {
//        String name = "acong";
//        String role = "rol";
//        String token = createToken(name,role);
//        System.out.println(token);
//
//        Claims claims = checkJWT(token);
//        System.out.println(claims.get("username"));
//
//        System.out.println(getUsername(token));
//        System.out.println(getUserRole(token));
//        System.out.println(isExpiration(token));
//
//    }

    public static void main(String[] args) {

        User user = new User();
        user.setUserId(1101);
        user.setUserName("chenhongde");
        String token = generateJsonWebToken(user);

        System.out.println(token);

        Claims claims = checkJWT(token);
        if (claims != null) {
            String id = claims.get("id").toString();
            String name = claims.get("name").toString();

            String rol = claims.get("rol").toString();

            System.out.println("id:" + id);
            System.out.println("name:" + name);

            System.out.println("rol:" + rol);


        }
    }
}
