package org.seasar.laszlo.examples.example2;

import java.util.List;
import java.util.Map;

public interface LaszloToJava {
    
    public void receiveBooleanP(boolean b);

    public void receiveIntP(int i);

    public void receiveLongP(long l);

    public void receiveFloatP(float f);

    public void receiveDoubleP(double d);
    
    public void receiveBoolean(Boolean b);

    public void receiveInteger(Integer i);

    public void receiveLong(Long l);

    public void receiveFloat(Float f);

    public void receiveDouble(Double d);

    public void receiveString(String s);

    public void receiveList(List l);

    public void receiveMap(Map m);

}
