/*
 * Copyright 2005 the Seasar Foundation and the Others.
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
package jp.seasar.laszlo;

import java.util.List;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.MetaDef;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 
 * @author u1hoshino
 * 
 */
public class S2LaszloAdapter {

	public static final String S2LASZLO_META_STRING = "s2laszlo";

	private S2LaszloAdapter() {
	}

	public static ReturnObject service(String compname, String methodName) {
		return service(compname, methodName, null);
	}

	public static ReturnObject service(String compname, String methodName,
			List argList) {
		ReturnObject returnObject = new ReturnObject();

		try {
			S2Container container = SingletonS2ContainerFactory.getContainer();
			Object component = container.getComponent(compname);

			ComponentDef compDef = container.getComponentDef(compname);
			MetaDef s2laszloDef = compDef.getMetaDef(S2LASZLO_META_STRING);
			if (s2laszloDef == null) {
				throw new S2LaszloNotAllowedException("component not allowed");
			}

			BeanDesc beanDesc = BeanDescFactory.getBeanDesc(component
					.getClass());
			Object obj = beanDesc.invoke(component, methodName,
					convertArgList(argList));

			returnObject.setSuccess(true);
			returnObject.setObject(obj);
		} catch (Throwable t) {
			returnObject.setSuccess(false);
			returnObject.setException(t.getClass().getName());
			returnObject.setExceptionMessage(t.getMessage());
		}
		return returnObject;
	}

	private static Object[] convertArgList(List argList) {
		if (argList == null) {
			return null;
		}

		Object[] args = new Object[argList.size()];
		for (int i = 0; i < argList.size(); i++) {
			args[i] = argList.get(i);
		}
		
		return args;
	}
}
