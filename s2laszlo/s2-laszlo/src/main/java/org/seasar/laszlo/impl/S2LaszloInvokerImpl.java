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
package org.seasar.laszlo.impl;

import java.util.List;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.MetaDef;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.laszlo.ReturnObject;
import org.seasar.laszlo.S2LaszloExceptionHandler;
import org.seasar.laszlo.S2LaszloInvoker;
import org.seasar.laszlo.S2LaszloInvokerLogFormatter;
import org.seasar.laszlo.S2LaszloNotAllowedException;
import org.seasar.laszlo.filter.S2LaszloNeedGzipHolder;

/**
 * 
 * @author u1hoshino
 * 
 */
public class S2LaszloInvokerImpl implements S2LaszloInvoker {

    private String metaString = S2LASZLO_META_STRING;

    private S2LaszloInvokerLogFormatter invokerLog = new S2LaszloInvokerLogFormatterDefaultImpl();

    private S2LaszloExceptionHandler exceptionHandler = new S2LaszloExceptionHandlerImpl();

    public void setS2LaszloInvokerLogFormatter(
            S2LaszloInvokerLogFormatter invokerLog) {
        this.invokerLog = invokerLog;
    }

    public void setS2LaszloMetaString(String metaString) {
        this.metaString = metaString;
    }

    public void setS2LaszloExceptionHandler(
            S2LaszloExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    public ReturnObject invoke(String compname, String methodName, List argList) {
        invokerLog.beforeLog(compname, methodName, argList);
        ReturnObject returnObject = null;
        try {
            S2Container container = SingletonS2ContainerFactory.getContainer();
            Object component = container.getComponent(compname);
            ComponentDef compDef = container.getComponentDef(compname);
            MetaDef s2laszloDef = compDef.getMetaDef(metaString);
            if (s2laszloDef == null) {
                throw new S2LaszloNotAllowedException("component not allowed");
            }
            
            // gzip
            S2LaszloNeedGzipHolder.setNeedGzip();
            
            BeanDesc beanDesc = BeanDescFactory.getBeanDesc(component
                    .getClass());
            Object obj = beanDesc.invoke(component, methodName,
                    convertArgList(argList));
            returnObject = new ReturnObject(obj);
        } catch (Throwable t) {
            returnObject = exceptionHandler.execute(compname, methodName, t);
            invokerLog.errorLog(compname, methodName, returnObject, t);
        }
        invokerLog.afterLog(compname, methodName, returnObject);
        return returnObject;
    }

    private Object[] convertArgList(List argList) {
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
