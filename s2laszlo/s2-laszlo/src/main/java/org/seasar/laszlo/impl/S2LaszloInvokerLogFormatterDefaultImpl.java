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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seasar.laszlo.ReturnObject;
import org.seasar.laszlo.S2LaszloInvokerLogFormatter;

/**
 * 
 * @author u1hoshino
 * 
 */
public class S2LaszloInvokerLogFormatterDefaultImpl implements
        S2LaszloInvokerLogFormatter {

    private static final Log logger = LogFactory
            .getLog(S2LaszloInvokerLogFormatterDefaultImpl.class);

    private static final ThreadLocal threadLocal = new ThreadLocal();

    public void beforeLog(final String compname, final String methodName,
            final List argList) {
        if (logger.isInfoEnabled()) {
            threadLocal.set(new Long(System.currentTimeMillis()));
            StringBuffer sb = new StringBuffer(100);
            sb.append(format(compname, methodName));
            sb.append("START");
            logger.info(sb.toString());
        }
    }

    public void afterLog(final String compname, final String methodName,
            final ReturnObject ro) {
        if (logger.isInfoEnabled()) {

            long time = -1;
            Long startTime = (Long) threadLocal.get();
            // if log setting chenged...
            if (startTime != null) {
                time = System.currentTimeMillis() - startTime.longValue();
            }
            StringBuffer sb = new StringBuffer(100);
            sb.append(format(compname, methodName));
            sb.append("END");
            if (time != -1) {
                sb.append(" : ( ");
                sb.append(time);
                sb.append(" ms )");
            }
            logger.info(sb.toString());
        }
    }

    public void errorLog(final String compname, final String methodName,
            final ReturnObject ro, final Throwable t) {
        StringBuffer sb = new StringBuffer(100);
        sb.append(format(compname, methodName));
        sb.append("CATCH EXCEPTION...");
        logger.error(sb.toString(), t);
    }

    public String format(final String compname, final String methodName) {
        StringBuffer sb = new StringBuffer(100);
        sb.append("[S2Laszlo] : ");
        sb.append(compname);
        sb.append(" : ");
        sb.append(methodName);
        sb.append(" : ");
        return sb.toString();
    }
}
