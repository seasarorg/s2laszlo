package org.seasar.laszlo.examples.example4;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Example4Impl implements Example4 {

    private static final Log logger = LogFactory.getLog(Example4Impl.class);
    
	public String sleepAndEcho(int sleep, String str) {
		logger.info("待機秒数:" + sleep + ",メッセージ:" + str);
		try {
			Thread.sleep(sleep * 1000);
		} catch (InterruptedException e) {
			// noop
		}
		return str;
	}

}
