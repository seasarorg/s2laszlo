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
package org.seasar.laszlo.invoker.impl;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.MetaDef;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.util.MethodUtil;
import org.seasar.laszlo.exception.S2LaszloNotAllowedException;
import org.seasar.laszlo.invoker.ComponentAssembler;
import org.seasar.laszlo.invoker.ComponentInvoker;
import org.seasar.laszlo.invoker.ComponentInvokerResult;

/**
 * Class <code>ComponentInvokerrImpl</code> is ComponentInvoker
 * Implemantation.
 * 
 * @author u1hoshino
 * 
 */
public class ComponentInvokerImpl implements ComponentInvoker {

    private String metaString;

    private S2Container container;

    private ComponentAssembler componentAssembler;

    public void setMetaString(String metaString) {
        this.metaString = metaString;
    }

    public void setContainer(S2Container container) {
        if (container != null) {
            this.container = container.getRoot();
        }
    }

    public void setComponentAssembler(ComponentAssembler componentAssembler) {
        this.componentAssembler = componentAssembler;
    }

    /**
     * 
     * 
     */
    public ComponentInvokerResult invoke(String componentName,
            String methodName, Map<String, String[]> propertyValues,
            List methodArgs) {

        // Object component = container.getComponent(componentName);
        ComponentDef compDef = container.getComponentDef(componentName);

        MetaDef[] s2laszloDefs = compDef.getMetaDefs(metaString);
        if (s2laszloDefs == null || s2laszloDefs.length == 0) {
            throw new S2LaszloNotAllowedException("component not allowed.");
        }

        Object component = componentAssembler.getInitializedComponent(
                componentName, propertyValues);

        // component is not null
        assert component != null;

        Method method = getTargetMethod(component, methodName);

        assert method != null;

        ComponentInvokerResult result = new ComponentInvokerResult();

        if (isReturnTypeVoid(method)) {
            result.setReturnTypeVoid(true);
        }
        result.setMethodAnnotations(method.getAnnotations());
        result.setClassAnnotations(component.getClass().getAnnotations());

        // invoke
        try {
            Object obj = MethodUtil.invoke(method, component,
                    convertArgList(methodArgs));
            result.setResult(obj);
        } catch (Exception e) {
            result.setException(e);
        }

        return result;
    }

    /**
     * Get target <code>Method</code>.
     * 
     * @param component
     *            Object
     * @param methodName
     *            String
     * @return <code>Method</code>
     */
    protected Method getTargetMethod(Object component, String methodName) {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(component.getClass());
        Method[] methods = beanDesc.getMethods(methodName);

        if (methods == null || methods.length != 1) {
            return null;
        }

        return methods[0];
    }

    /**
     * Convert List to Object[] for method arguments.
     * 
     * @param argList
     *            List
     * @return <code>Object[]</code>: if argList is not null or empty.<br/>
     *         <code>null</code>: otherwise.
     */
    private Object[] convertArgList(List argList) {
        if (argList == null || argList.size() == 0) {
            return null;
        }
        Object[] args = new Object[argList.size()];
        for (int i = 0; i < argList.size(); i++) {
            args[i] = argList.get(i);
        }
        return args;
    }

    private boolean isReturnTypeVoid(Method method) {
        Class returnType = method.getReturnType();
        return Void.TYPE == returnType;
    }

}
