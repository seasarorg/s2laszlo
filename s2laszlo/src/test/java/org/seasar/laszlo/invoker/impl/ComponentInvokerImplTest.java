package org.seasar.laszlo.invoker.impl;

import java.util.Map;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.beans.MethodNotFoundRuntimeException;
import org.seasar.framework.container.ComponentNotFoundRuntimeException;
import org.seasar.laszlo.exception.S2LaszloNotAllowedException;
import org.seasar.laszlo.invoker.ComponentAssembler;
import org.seasar.laszlo.invoker.ComponentInvokerResult;

public class ComponentInvokerImplTest extends S2TestCase {

	private static String DICON_FILE = "ComponentInvokerImplTest.dicon";

	private ComponentInvokerImpl componentInvoker;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		include(DICON_FILE);

		componentInvoker = new ComponentInvokerImpl();
		componentInvoker.setMetaString("s2laszlo");
		componentInvoker.setContainer(getContainer());
		ComponentAssembler ca = new ComponentAssembler() {
			public Object getInitializedComponent(String componentName,
					Map<String, String[]> valuesMap) {
				return getContainer().getComponent(componentName);
			}

		};
		componentInvoker.setComponentAssembler(ca);
	}

	public void testNormal() {

		ComponentInvokerResult cir = componentInvoker.invoke("hogehoge",
				"hello", null, null);
		assertEquals("hello", cir.getResult());
		assertNull(cir.getException());

	}

	public void testThrowException() {
		ComponentInvokerResult cir = componentInvoker.invoke("hogehoge",
				"throwException", null, null);
		assertNull(cir.getResult());
		assertTrue(cir.getException() instanceof RuntimeException);
	}

	public void testComponentNotFound() {
		try {
			componentInvoker.invoke("foo", "exec", null, null);
			assertTrue(false);
		} catch (ComponentNotFoundRuntimeException e) {
			assertTrue(true);
		} catch (Throwable t) {
			assertTrue(false);
		}
	}

	public void testMethodNotFound() {
		try {
			componentInvoker.invoke("hogehoge", "hogehoge", null, null);
			assertTrue(false);
		} catch (MethodNotFoundRuntimeException e) {
			assertTrue(true);
		} catch (Throwable t) {
			assertTrue(false);
		}
	}

	public void testNoMetadata() {
		try {
			componentInvoker.invoke("hogehogeNoMeta", "hello", null, null);
			assertTrue(false);
		} catch (S2LaszloNotAllowedException e) {
			assertTrue(true);
		}
	}

}
