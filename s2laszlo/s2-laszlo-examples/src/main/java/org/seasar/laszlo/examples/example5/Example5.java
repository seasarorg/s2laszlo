package org.seasar.laszlo.examples.example5;

import java.util.List;

import javax.servlet.http.Cookie;

public interface Example5 {

    public Cookie[] getCookies();

    public void createSessionCookie();

    public List getHeaderList();

    public void resetSession();

    public void addSessionAttribute(String key, String value);

    public List getSessionAttributes();

}
