package org.seasar.laszlo.servlet.impl;

import java.io.IOException;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.mock.servlet.MockHttpServletRequestImpl;
import org.seasar.framework.mock.servlet.MockHttpServletResponseImpl;
import org.seasar.framework.mock.servlet.MockServletContextImpl;
import org.seasar.laszlo.invoker.ComponentInvokerResult;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XStreamResponseHandlerImplTest extends S2TestCase {

	private XStreamResponseHandlerImpl xstreamResponseHandler;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		xstreamResponseHandler = new XStreamResponseHandlerImpl();
		xstreamResponseHandler.setEncoding("UTF-8");
		xstreamResponseHandler.setContentType("aaa");
		xstreamResponseHandler.setXstream(new XStream(new DomDriver()));
	}

	public void testNormal() throws IOException {

		MockServletContextImpl sc = new MockServletContextImpl("/test");
		MockHttpServletRequestImpl req = new MockHttpServletRequestImpl(sc,
				"/s2laszlo/hoge/hello");
		MockHttpServletResponseImpl res = new MockHttpServletResponseImpl(req);

		ComponentInvokerResult invokerResult = new ComponentInvokerResult();
		invokerResult.setResult("hoge");
		xstreamResponseHandler.response(res, invokerResult);

		assertEquals("aaa", res.getContentType());
		assertEquals(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<string>hoge</string>",
				res.getOutputStream().toString());
	}
}
