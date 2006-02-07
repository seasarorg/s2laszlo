package org.seasar.laszlo.examples.example2;

public class Example2Bean {

    private int key_;

    private String value_;

    public Example2Bean(int key, String value){
        this.key_ = key;
        this.value_ = value;
    }
    
    public int getKey() {
        return key_;
    }

    public void setKey(int key) {
        this.key_ = key;
    }

    public String getValue() {
        return value_;
    }

    public void setValue(String value) {
        this.value_ = value;
    }
}
