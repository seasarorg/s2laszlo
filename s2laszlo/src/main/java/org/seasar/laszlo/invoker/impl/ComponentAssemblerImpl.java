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

import java.util.Iterator;
import java.util.Map;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.InstanceDef;
import org.seasar.framework.container.S2Container;
import org.seasar.laszlo.invoker.ComponentAssembler;

/**
 * Class <code>ComponentAssemblerImpl</code> is ComponentAssembler
 * Implementation.
 * 
 * 
 * @author u1hoshino
 * 
 */
public class ComponentAssemblerImpl implements ComponentAssembler {

    /** S2Container */
    private S2Container container;

    /** S2Container setter */
    public void setContainer(S2Container container) {
        if (container != null) {
            this.container = container.getRoot();
        }
    }

    /**
     * Initialized component for s2laszlo getter
     * 
     * @param componentName
     *            String
     * @param valuesMap
     *            Map<String,String[]>
     * @return initialized component object
     */
    public Object getInitializedComponent(String componentName,
            Map<String, String[]> valuesMap) {
        Object component = container.getComponent(componentName);
        ComponentDef compDef = container.getComponentDef(componentName);
        
        InstanceDef instDef = compDef.getInstanceDef();
        if (isLongLifecycleComponent(instDef) || valuesMap == null
                || valuesMap.size() == 0) {
            return component;
        }

        for (Iterator<Map.Entry<String, String[]>> it = valuesMap.entrySet()
                .iterator(); it.hasNext();) {
            Map.Entry<String, String[]> ent = it.next();
            setComponentProperty(component, ent.getKey(), ent.getValue());
        }

        return component;
    }

    /**
     * Check long lifecycle component.
     * 
     * @param instDef
     *            InstnaceDef
     * @return <code>true</code> if InstanceDef is long lifecycle<br/>
     *         <code>false</code> otherwise
     */
    protected boolean isLongLifecycleComponent(InstanceDef instDef) {

        if (InstanceDef.SINGLETON_NAME.equals(instDef.getName())) {
            return true;
        } else if (InstanceDef.PROTOTYPE_NAME.equals(instDef.getName())) {
            return false;
        } else if (InstanceDef.REQUEST_NAME.equals(instDef.getName())) {
            return false;
        } else if (InstanceDef.SESSION_NAME.equals(instDef.getName())) {
            return true;
        } else if (InstanceDef.OUTER_NAME.equals(instDef.getName())) {
            return true;
        }

        return true;
    }

    /**
     * Set a component property.
     * 
     * @param component
     *            Object
     * @param propertyName
     *            String
     * @param values
     *            String[]
     */
    protected void setComponentProperty(Object component, String propertyName,
            String[] values) {

        if (values == null || values.length == 0) {
            return;
        }

        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(component.getClass());
        
        if (beanDesc.hasPropertyDesc(propertyName)) {
            PropertyDesc pd = beanDesc.getPropertyDesc(propertyName);
            if (pd.hasWriteMethod()) {
                for (int i = 0; i < values.length; i++) {
                    pd.setValue(component, values[i]);
                }
            }
        }
    }

}
