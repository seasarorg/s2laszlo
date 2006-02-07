package org.seasar.laszlo.examples.example2;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class JavaToLaszloImpl implements JavaToLaszlo {

    private boolean booleanPValue;

    private int intPValue;

    private long longPValue;

    private float floatPValue;

    private double doublePValue;

    private Boolean booleanValue;

    private Integer integerValue;

    private Long longValue;

    private Float floatValue;

    private Double doubleValue;

    private BigInteger bigIntegerValue;

    private BigDecimal bigDecimalValue;

    private Date dateValue;

    private String stringValue;

    private List listValue;

    private Map mapValue;

    private Example2Bean beanValue;

    public boolean sendBooleanP() {
        return booleanPValue;
    }

    public int sendIntP() {
        return intPValue;
    }

    public long sendLongP() {
        return longPValue;
    }

    public float sendFloatP() {
        return floatPValue;
    }

    public double sendDoubleP() {
        return doublePValue;
    }

    public Boolean sendBoolean() {
        return booleanValue;
    }

    public Integer sendInteger() {
        return integerValue;
    }

    public Long sendLong() {
        return longValue;
    }

    public Float sendFloat() {
        return floatValue;
    }

    public Double sendDouble() {
        return doubleValue;
    }

    public BigInteger sendBigInteger() {
        return bigIntegerValue;
    }

    public BigDecimal sendBigDecimal() {
        return bigDecimalValue;
    }

    public Date sendDate() {
        return dateValue;
    }

    public String sendString() {
        return stringValue;
    }

    public List sendList() {
        return listValue;
    }

    public Map sendMap() {
        return mapValue;
    }

    public Example2Bean sendBean() {
        return beanValue;
    }

    // ///////// setter

    public void setBeanValue(Example2Bean beanValue) {
        this.beanValue = beanValue;
    }

    public void setBigDecimalValue(BigDecimal bigDecimalValue) {
        this.bigDecimalValue = bigDecimalValue;
    }

    public void setBigIntegerValue(BigInteger bigIntegerValue) {
        this.bigIntegerValue = bigIntegerValue;
    }

    public void setBooleanPValue(boolean booleanPValue) {
        this.booleanPValue = booleanPValue;
    }

    public void setBooleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    public void setDoublePValue(double doublePValue) {
        this.doublePValue = doublePValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public void setFloatPValue(float floatPValue) {
        this.floatPValue = floatPValue;
    }

    public void setFloatValue(Float floatValue) {
        this.floatValue = floatValue;
    }

    public void setIntegerValue(Integer integerValue) {
        this.integerValue = integerValue;
    }

    public void setIntPValue(int intPValue) {
        this.intPValue = intPValue;
    }

    public void setListValue(List listValue) {
        this.listValue = listValue;
    }

    public void setLongPValue(long longPValue) {
        this.longPValue = longPValue;
    }

    public void setLongValue(Long longValue) {
        this.longValue = longValue;
    }

    public void setMapValue(Map mapValue) {
        this.mapValue = mapValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

}
