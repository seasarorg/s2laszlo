package org.seasar.laszlo.examples.example3;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Example3Impl implements Example3 {

    private static final Log logger = LogFactory.getLog(Example3Impl.class);

    public String sleep(int s) {
        logger.info(s + "ïb sleepÇµÇ‹Ç∑ÅB");
        try {
            Thread.sleep(s * 1000);
        } catch (InterruptedException e) {
            // noop
        }
        return "hello";
    }

    public String throwRuntimeException() {
        boolean flag = true;
        if (flag) {
            throw new RuntimeException("ó·äOÇ™î≠ê∂ÇµÇ‹ÇµÇΩ");
        }
        return "hello";
    }

}
