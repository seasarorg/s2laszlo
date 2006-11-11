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
package org.seasar.laszlo.javarpc.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.seasar.laszlo.invoker.ComponentInvoker;
import org.seasar.laszlo.invoker.ComponentInvokerResult;
import org.seasar.laszlo.javarpc.JavaRPCAdapter;
import org.seasar.laszlo.javarpc.ReturnObject;

public class JavaRPCAdapterImpl implements JavaRPCAdapter {

	private ComponentInvoker invoker;

	public void setInvoker(ComponentInvoker invoker) {
		this.invoker = invoker;
	}

	public ReturnObject service(String compname, String methodName,
			Map propertyValues, List argList) {

		ComponentInvokerResult invResult = invoker.invoke(compname, methodName,
				convertPropertyValues(propertyValues), argList);

		ReturnObject ro = null;
		if (invResult.getException() == null) {
			ro = new ReturnObject(invResult.getResult());
		} else {
			ro = new ReturnObject(
					invResult.getException().getClass().getName(), invResult
							.getException().getMessage());
		}

		return ro;
	}

	private Map<String, String[]> convertPropertyValues(Map propertyValues) {

		if (propertyValues == null || propertyValues.size() == 0) {
			return new HashMap<String, String[]>(0);
		}

		Map<String, String[]> result = new HashMap<String, String[]>(
				propertyValues.size());
		for (Iterator it = propertyValues.entrySet().iterator(); it.hasNext();) {
			Map.Entry ent = (Map.Entry) it.next();

			if (ent.getValue() instanceof Object[]) {
				Object[] objs = (Object[]) ent.getValue();
				String[] strs = new String[objs.length];
				for (int i = 0; i < objs.length; i++) {
					if (objs[i] != null) {
						strs[i] = objs[i].toString();
					}
				}
				result.put(ent.getKey().toString(), strs);
			} else {
				String[] value = { ent.getValue().toString() };
				result.put(ent.getKey().toString(), value);
			}
		}

		return result;
	}
}
