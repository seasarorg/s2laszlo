package org.seasar.laszlo.examples.example5;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

public class Example5Impl implements Example5 {

    public Cookie[] getCookies() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        HttpServletRequest req = container.getRequest();
        Cookie[] cookies = req.getCookies();
        return cookies;
    }

    public void createSessionCookie() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        HttpServletRequest req = container.getRequest();
        req.getSession();
    }

    public List getHeaderList() {

        S2Container container = SingletonS2ContainerFactory.getContainer();
        HttpServletRequest req = container.getRequest();
        Enumeration e = req.getHeaderNames();
        List list = new ArrayList();
        while (e.hasMoreElements()) {
            String name = (String) e.nextElement();
            String value = req.getHeader(name);
            Map m = new HashMap(2);
            m.put("headerName", name);
            m.put("headerValue", value);
            list.add(m);
        }
        return list;
    }

    public void resetSession() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        HttpServletRequest req = container.getRequest();
        HttpSession session = req.getSession();
        if (!session.isNew()) {
            session.invalidate();
            req.getSession();
        }
    }

    public void addSessionAttribute(String key, String value) {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        HttpServletRequest req = container.getRequest();
        HttpSession session = req.getSession();
        session.setAttribute(key, value);
    }

    public List getSessionAttributes() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        HttpServletRequest req = container.getRequest();
        HttpSession session = req.getSession();
        Enumeration e = session.getAttributeNames();
        List list = new ArrayList();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = (String) session.getAttribute(key);
            Map m = new HashMap(2);
            m.put("key", key);
            m.put("value", value);
            list.add(m);
        }
        return list;
    }
}
