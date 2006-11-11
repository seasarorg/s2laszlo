/*
 * Copyright 2005-2006 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.laszlo.servlet.impl;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.seasar.laszlo.invoker.ComponentInvoker;
import org.seasar.laszlo.invoker.ComponentInvokerResult;
import org.seasar.laszlo.servlet.ResponseHandler;
import org.seasar.laszlo.servlet.S2LaszloServletHandler;

public class S2LaszloServletHandlerImpl implements S2LaszloServletHandler {

	private ResponseHandler responseHandler;

	private ComponentInvoker invoker;

	public void setInvoker(ComponentInvoker invoker) {
		this.invoker = invoker;
	}

	public void setResponseHandler(ResponseHandler responseHandler) {
		this.responseHandler = responseHandler;
	}

	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException {

		CompMethodNameVO compMethodNameVO = getCreateCompMethodName(req);
		Map<String, String[]> parameters = getRequestParameters(req);

		ComponentInvokerResult invokerResult = invoker.invoke(compMethodNameVO
				.getComponentName(), compMethodNameVO.getMethodName(),
				parameters, null);

		responseHandler.response(res, invokerResult);
		res.flushBuffer();
	}

	private CompMethodNameVO getCreateCompMethodName(HttpServletRequest req) {

		String contextPath = req.getContextPath();
		String servletPath = req.getServletPath();
		String requestUri = req.getRequestURI();
		String ctxAndServletPath = contextPath + servletPath + "/";

		if (requestUri.startsWith(ctxAndServletPath)) {
			String compAndMethod = requestUri.substring(ctxAndServletPath
					.length());
			int sepIndex = compAndMethod.lastIndexOf("/");
			if (0 < sepIndex && sepIndex < compAndMethod.length()) {
				String compName = compAndMethod.substring(0, sepIndex);
				String methodName = compAndMethod.substring(sepIndex + 1,
						compAndMethod.length());
				return new CompMethodNameVO(compName, methodName);
			}
		}

		return new CompMethodNameVO("", "");
	}

	private Map<String, String[]> getRequestParameters(HttpServletRequest req) {

		Map<String, String[]> parameters = new HashMap<String, String[]>();
		Enumeration parameterNames = req.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parameterName = (String) parameterNames.nextElement();
			parameters
					.put(parameterName, req.getParameterValues(parameterName));
		}

		return parameters;
	}

}
