package org.seasar.laszlo.servlet.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.container.ComponentNotFoundRuntimeException;
import org.seasar.framework.mock.servlet.MockHttpServletRequestImpl;
import org.seasar.framework.mock.servlet.MockHttpServletResponseImpl;
import org.seasar.framework.mock.servlet.MockServletContextImpl;
import org.seasar.laszlo.invoker.ComponentInvoker;
import org.seasar.laszlo.invoker.ComponentInvokerResult;
import org.seasar.laszlo.servlet.ResponseHandler;

public class S2LaszloServletHandlerImplTest extends S2TestCase {

	private S2LaszloServletHandlerImpl s2laszloServletHandler;

	private String componentName;

	private String methodName;

	private Map<String, String[]> props;

	private ComponentInvokerResult cir;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		s2laszloServletHandler = new S2LaszloServletHandlerImpl();

		ComponentInvoker invoker = new ComponentInvoker() {

			public ComponentInvokerResult invoke(String cname, String mname,
					Map<String, String[]> properties, List methodArgs) {
				componentName = cname;
				methodName = mname;
				;

				props = properties;
				ComponentInvokerResult cir = new ComponentInvokerResult();

				if ("throwException".equals(methodName)) {
					cir.setException(new RuntimeException("exception"));
				}

				cir.setResult("hello");
				return cir;
			}

		};

		s2laszloServletHandler.setInvoker(invoker);

		ResponseHandler rh = new ResponseHandler() {
			public void response(HttpServletResponse res,
					ComponentInvokerResult obj) throws IOException {
				cir = obj;
			}
		};

		s2laszloServletHandler.setResponseHandler(rh);

	}

	public void testNormal() throws IOException {
		MockServletContextImpl sc = new MockServletContextImpl("/test");
		MockHttpServletRequestImpl req = new MockHttpServletRequestImpl(sc,
				"/s2laszlo/hoge/hello") {
			public String getServletPath() {
				return "/s2laszlo";
			}
		};
		String[] strs = { "hoge", "hogehoge" };
		req.addParameter("strs", strs);
		req.addParameter("str", "foo");

		MockHttpServletResponseImpl res = new MockHttpServletResponseImpl(req);

		s2laszloServletHandler.execute(req, res);

		assertEquals("hoge", componentName);
		assertEquals("hello", methodName);

		assertEquals(strs[0], props.get("strs")[0]);
		assertEquals(strs[1], props.get("strs")[1]);

		assertEquals("foo", props.get("str")[0]);

		assertEquals("hello", cir.getResult());
		assertNull(cir.getException());

	}

	public void testErrorRequest1() {

		MockServletContextImpl sc = new MockServletContextImpl("/test");
		MockHttpServletRequestImpl req = new MockHttpServletRequestImpl(sc,
				"/s2laszlo") {
			public String getServletPath() {
				return "/s2laszlo";
			}
		};
		String[] strs = { "hoge", "hogehoge" };
		req.addParameter("strs", strs);
		req.addParameter("str", "foo");

		MockHttpServletResponseImpl res = new MockHttpServletResponseImpl(req);

		try {
			s2laszloServletHandler.execute(req, res);
		} catch (ComponentNotFoundRuntimeException e) {
			assertTrue(true);
		} catch (Throwable t) {
			assertTrue(false);
		}
	}

	public void testErrorRequest2() {

		MockServletContextImpl sc = new MockServletContextImpl("/test");
		MockHttpServletRequestImpl req = new MockHttpServletRequestImpl(sc,
				"/s2laszlo/hoge") {
			public String getServletPath() {
				return "/s2laszlo";
			}
		};
		String[] strs = { "hoge", "hogehoge" };
		req.addParameter("strs", strs);
		req.addParameter("str", "foo");

		MockHttpServletResponseImpl res = new MockHttpServletResponseImpl(req);

		try {
			s2laszloServletHandler.execute(req, res);
		} catch (ComponentNotFoundRuntimeException e) {
			assertTrue(true);
		} catch (Throwable t) {
			assertTrue(false);
		}
	}
}
