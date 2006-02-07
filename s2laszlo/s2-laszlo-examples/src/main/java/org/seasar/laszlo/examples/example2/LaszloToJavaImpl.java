package org.seasar.laszlo.examples.example2;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LaszloToJavaImpl implements LaszloToJava {

    private static final Log logger = LogFactory.getLog(LaszloToJavaImpl.class);

    public void receiveBooleanP(boolean b) {
        logger.info("receiveBooleanP:" + b);
    }

    public void receiveIntP(int i) {
        logger.info("receiveIntP:" + i);
    }

    public void receiveLongP(long l) {
        logger.info("receiveLongP:" + l);
    }

    public void receiveFloatP(float f) {
        logger.info("receiveFloatP:" + f);
    }

    public void receiveDoubleP(double d) {
        logger.info("receiveDoubleP:" + d);
    }

    public void receiveBoolean(Boolean b) {
        logger.info("receiveBoolean:" + b);
    }

    public void receiveInteger(Integer i) {
        logger.info("receiveInteger:" + i);
    }

    public void receiveLong(Long l) {
        logger.info("receiveLong:" + l);
    }

    public void receiveFloat(Float f) {
        logger.info("receiveFloat:" + f);
    }

    public void receiveDouble(Double d) {
        logger.info("receiveDouble:" + d);
    }

    public void receiveString(String s) {
        logger.info("receiveString:" + s);
    }

    public void receiveList(List l) {
        for (int i = 0; i < l.size(); i++) {
            logger.info("receiveList:" + l.get(i) + ":" + l.get(i).getClass());
        }
    }

    public void receiveMap(Map m) {
        for (Iterator it = m.entrySet().iterator(); it.hasNext();) {
            Map.Entry e = (Map.Entry) it.next();
            logger.info("receiveMap:(" + e.getKey() + "," + e.getValue() + ":" + e.getValue().getClass() + ")");
        }
    }
}
