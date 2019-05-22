package com.example.constants;

/**
 * @Description: 全局常量
 */
public final class Globals {
	
	/*****/
	public static final String TAGLIB_PACKAGE = "com.uhealth.taglib.html";
	/**
	 * 登陆后保存在session中的用户信息
	 */
	public static final String SESSION_USER = "session_user";
	
	/** Session保存token name ***/
	public static final String TOKEN_KEY = TAGLIB_PACKAGE + ".TOKEN";
	
	// pageinfo 常量
	public static final String PAGEINFO_KEY = "pageInfo";
	
	// 获取未读站内信数量
	public static final String UNREAD_MSG_NUM = "unread_msg_num";
	
	// 登录后客户端cookie名
	public static final String AUTH_TOKEN_KEY = "_IDENTIY_KEY_";
	
	public static final int AUTH_TOKEN_AGE = 14 * 24 * 3600;
}
