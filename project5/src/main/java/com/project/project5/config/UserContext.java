package com.project.project5.config;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName UserContext
 * @date 2020/6/23 19:05
 *
 */
public class UserContext {

	private static ThreadLocal<String> authorizationToken = new ThreadLocal<>();


	public static String getToken() {
		return authorizationToken.get();
	}

	public static void setToken(String token) {
		authorizationToken.set(token);
	}
}
