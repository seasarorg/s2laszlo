package org.seasar.laszlo.examples.example2;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface JavaToLaszlo {
    
    public boolean sendBooleanP();

    public int sendIntP();

    public long sendLongP();

    public float sendFloatP();

    public double sendDoubleP();
    
    public Boolean sendBoolean();

    public Integer sendInteger();

    public Long sendLong();

    public Float sendFloat();

    public Double sendDouble();

    public BigInteger sendBigInteger();
    
    public BigDecimal sendBigDecimal();
    
    public Date sendDate();
    
    public String sendString();

    public List sendList();

    public Map sendMap();

    public Example2Bean sendBean();

}
