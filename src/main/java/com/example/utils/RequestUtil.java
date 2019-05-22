package com.example.utils;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:RequestUtil 方便于设置和接受cookie
 * @author han.han
 * 
 */
public final class RequestUtil {

	private static final Log log = LogFactory.getLog(RequestUtil.class);

	private RequestUtil() {
	}

	/**
	 * @param response
	 * @param name
	 *            cookieName
	 * @param value
	 *            值
	 * @param second
	 *            秒
	 */
	public static void setCookie(HttpServletResponse response, String name, String value, int second) {
		if (log.isDebugEnabled()) {
			log.debug("Setting cookie '" + name);
		}

		Cookie cookie = new Cookie(name, value);
		cookie.setSecure(false);
		cookie.setPath("/");
		cookie.setMaxAge(second); // 秒
		response.addCookie(cookie);
	}

	/**
	 * 获取cookie
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		Cookie returnCookie = null;
		if (cookies == null) {
			return returnCookie;
		}
		for (final Cookie thisCookie : cookies) {
			if (thisCookie.getName().equals(name) && !"".equals(thisCookie.getValue())) {
				returnCookie = thisCookie;
				break;
			}
		}
		return returnCookie;
	}

	/**
	 * 删除cookie
	 * 
	 * @param response
	 * @param cookiename
	 */
	public static void deleteCookie(HttpServletResponse response, String cookiename) {
		setCookie(response, cookiename, "", 0);
	}

	/**
	 * Convenience method to get the application's URL based on request
	 * variables.
	 * 
	 * @param request
	 *            the current request
	 * @return URL to application
	 */
	public static String getAppURL(HttpServletRequest request) {
		if (request == null)
			return "";

		StringBuffer url = new StringBuffer();
		int port = request.getServerPort();
		if (port < 0) {
			port = 80;
		}
		String scheme = request.getScheme();
		url.append(scheme);
		url.append("://");
		url.append(request.getServerName());
		if ((scheme.equals("http") && (port != 80)) || (scheme.equals("https") && (port != 443))) {
			url.append(':');
			url.append(port);
		}
		url.append(request.getContextPath());
		return url.toString();
	}

	public static String getRealRemoteIp(HttpServletRequest request) {
		String ip = getRealIp(request);

		if (StringUtils.isNotEmpty(ip)) {
			return ip.split(",")[0].trim();
		}
		return "";
	}

	public static String getRealIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}

	/**
	 * 获取User-Agent
	 * 
	 * @param request
	 * @return
	 */
	public static String getUserAgent(HttpServletRequest request) {
		return request.getHeader("User-Agent");
	}
}
