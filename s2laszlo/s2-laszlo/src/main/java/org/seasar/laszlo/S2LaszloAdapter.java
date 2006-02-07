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
package org.seasar.laszlo;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 
 * @author u1hoshino
 * 
 */
public class S2LaszloAdapter {

    private static final Log logger = LogFactory.getLog(S2LaszloAdapter.class);

    public static final String S2LASZLO_INVOKER_COMP_NAME = "s2laszloInvoker";

    private S2LaszloAdapter() {
    }

    public static ReturnObject service(String compname, String methodName) {
        return service(compname, methodName, null);
    }

    public static ReturnObject service(String compname, String methodName,
            List argList) {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        S2LaszloInvoker invoker = (S2LaszloInvoker) container
                .getComponent(S2LASZLO_INVOKER_COMP_NAME);
        ReturnObject ro = null;
        if (invoker != null) {
            ro = invoker.invoke(compname, methodName, argList);
        } else {
            logger.error(S2LASZLO_INVOKER_COMP_NAME + " not defined.");
        }
        return ro;
    }

}
