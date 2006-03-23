package org.seasar.laszlo.examples.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RequestLogFilter implements Filter {

	private static final Log log = LogFactory.getLog(RequestLogFilter.class);

	public void init(FilterConfig filterconfig) throws ServletException {
		// noop
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		Enumeration paramNames = req.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			String[] values = req.getParameterValues(paramName);
			for (int i = 0; i < values.length; i++) {
				log.debug(paramName + "=" + values[i]);
			}
		}
		chain.doFilter(req, res);
	}

	public void destroy() {
		// noop
	}
}
