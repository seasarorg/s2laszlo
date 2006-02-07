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

import org.seasar.laszlo.ReturnObject;
import org.seasar.laszlo.S2LaszloExceptionHandler;

public class S2LaszloExceptionHandlerImpl implements S2LaszloExceptionHandler {

    public ReturnObject execute(String compname, String methodName, Throwable t) {
        ReturnObject ro = new ReturnObject(t.getClass().getName(), t
                .getMessage());
        return ro;
    }
}
