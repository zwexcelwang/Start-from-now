package zui.proxy;

public class Lenovo implements ISaleComputer{

    @Override
    public String sale(double money) {
        System.out.println("花了" + money +"钱");
        return "联想电脑";
    }

    @Override
    public void show() {
        System.out.println("show!!!");
    }
}
