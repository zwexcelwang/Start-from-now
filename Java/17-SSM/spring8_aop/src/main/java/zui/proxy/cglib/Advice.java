package zui.proxy.cglib;

public class Advice {

    public void before(){
        System.out.println("before...");
    }

    public void after(){
        System.out.println("after...");
    }
}
