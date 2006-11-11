package org.seasar.laszlo.javarpc.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.laszlo.invoker.ComponentInvoker;
import org.seasar.laszlo.invoker.ComponentInvokerResult;
import org.seasar.laszlo.javarpc.ReturnObject;

public class JavaRPCAdapterImplTest extends S2TestCase {

	private JavaRPCAdapterImpl javaRPCAdapter;

	private Map<String, String[]> props;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		javaRPCAdapter = new JavaRPCAdapterImpl();
		ComponentInvoker invoker = new ComponentInvoker() {

			public ComponentInvokerResult invoke(String compname,
					String methodName, Map<String, String[]> properties,
					List methodArgs) {
				props = properties;
				ComponentInvokerResult cir = new ComponentInvokerResult();
				
				if ("throwException".equals(methodName)) {
					cir.setException(new RuntimeException("exception"));
				}
				
				cir.setResult("hello");
				return cir;
			}

		};

		javaRPCAdapter.setInvoker(invoker);

	}

	public void testNormal() {
		ReturnObject ro = javaRPCAdapter.service("", "", null, null);
		assertEquals("hello", ro.getObject());
		assertNull(ro.getException());
		assertNull(ro.getExceptionMessage());
	}

	public void testProp() {
		Map m = new HashMap();
		Integer[] is = { new Integer(1), new Integer(2) };
		m.put("nums", is);
		String[] strs = { "hoge", "hogehoge" };
		m.put("strs", strs);
		m.put("str", "hello");

		ReturnObject ro = javaRPCAdapter.service("", "", m, null);

		assertEquals("1", props.get("nums")[0]);
		assertEquals("2", props.get("nums")[1]);
		
		assertEquals("hoge", props.get("strs")[0]);
		assertEquals("hogehoge", props.get("strs")[1]);

		assertEquals("hello", props.get("str")[0]);
	}
	
	public void testThrowException(){
		ReturnObject ro = javaRPCAdapter.service("", "throwException", null, null);
		
		assertNull(ro.getObject());
		assertEquals(RuntimeException.class.getName(), ro.getException());
		assertEquals("exception", ro.getExceptionMessage());
	}
}
