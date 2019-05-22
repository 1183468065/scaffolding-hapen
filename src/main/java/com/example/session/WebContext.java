package com.example.session;

import com.example.constants.Globals;
import com.example.utils.RequestUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WebContext<T> {

    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        return request;
    }

    public static HttpServletResponse getResponse() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getResponse();
        return response;
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static void removeSessionAttribute(String attribute) {
        getSession().removeAttribute(attribute);
    }

    public static void setSessionAttribute(String attribute, Object data) {
        HttpSession session = WebContext.getSession();
        session.setAttribute(attribute, data);
        session.getAttribute(Globals.SESSION_USER);
    }

    public static String getSessionAttributeString(String attribute) {
        HttpSession session = WebContext.getSession();
        return (String) session.getAttribute(attribute);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getSessionAttribute(String attribute) {
        HttpSession session = WebContext.getSession();
        return (T) session.getAttribute(attribute);
    }

    public static <T> T getSessionUser() {
        return (T) getSessionAttribute(Globals.SESSION_USER);
    }

    public static void removeSessionUser() {
        removeSessionAttribute(Globals.SESSION_USER);
    }

    public static void removeCookie() {
        RequestUtil.deleteCookie(getResponse(), Globals.AUTH_TOKEN_KEY);
    }

    public static <T> void setSessionUser(T user) {
        setSessionAttribute(Globals.SESSION_USER, user);
    }

    public static String getIpAddress() {
        return RequestUtil.getRealRemoteIp(WebContext.getRequest());
    }
}
