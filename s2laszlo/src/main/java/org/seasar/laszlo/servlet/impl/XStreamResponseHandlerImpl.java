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
import java.io.OutputStreamWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.seasar.laszlo.invoker.ComponentInvokerResult;
import org.seasar.laszlo.servlet.ResponseHandler;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.Annotations;

public class XStreamResponseHandlerImpl implements ResponseHandler {

	private String contentType;

	private String encoding;

	private XStream xstream;

	public void response(HttpServletResponse res,
			ComponentInvokerResult invokerResult) throws IOException {

		Object obj = null;
		if (invokerResult.isReturnTypeVoid()) {
			obj = new XStreamVoidObject();
		} else if (invokerResult.getException() != null) {
			String exceptionClass = invokerResult.getException().getClass()
					.getName();
			String exceptionMessage = invokerResult.getException().getMessage();
			XStreamExceptionObject exceptionObject = new XStreamExceptionObject(
					exceptionClass, exceptionMessage);
			obj = exceptionObject;
		} else {
			obj = invokerResult.getResult();
		}

		res.setContentType(contentType);
		OutputStreamWriter writer = new OutputStreamWriter(res
				.getOutputStream(), encoding);
		writer.write("<?xml version=\"1.0\" encoding=\"" + encoding + "\"?>\n");
		
		setXStreamAlias(xstream, obj);
		xstream.toXML(obj, writer);
	}

	private void setXStreamAlias(XStream xstream, Object obj) {

		if (obj instanceof List && List.class.cast(obj).size() != 0) {
			Annotations.configureAliases(xstream, List.class.cast(obj).get(0)
					.getClass());
		} else {
			// TODO Map
			Annotations.configureAliases(xstream, obj.getClass());
		}
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public void setXstream(XStream xstream) {
		this.xstream = xstream;
	}

}
