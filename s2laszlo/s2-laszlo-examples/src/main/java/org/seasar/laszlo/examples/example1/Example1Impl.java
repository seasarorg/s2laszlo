package org.seasar.laszlo.examples.example1;

public class Example1Impl implements Example1 {

    private String string;

    public void setString(String string) {
        this.string = string;
    }

    public String hello() {
        return string;
    }
}
