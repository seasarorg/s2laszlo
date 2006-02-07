package org.seasar.laszlo;

public class HogeImpl implements Hoge {

    public String sayHoge() {
        return "hoge";
    }

    public String echo(String str) {
        return str;
    }

    public void throwRuntimeException() {
        throw new RuntimeException("exception");
    }

}
