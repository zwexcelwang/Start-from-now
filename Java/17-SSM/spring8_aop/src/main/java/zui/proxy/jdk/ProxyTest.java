package zui.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

    public static void main(String[] args) {

        //创建目标对象
        final Target target = new Target();

        //获得增强的对象
        final Advice advice = new Advice();

        //返回值 就是动态生成的代理对象
        TargetInterface proxyInstance = (TargetInterface) Proxy.newProxyInstance(
                target.getClass().getClassLoader(), //目标对象类加载器
                target.getClass().getInterfaces(), //目标对象相同的接口字节码数组
                new InvocationHandler() {
                    // 调用代理对象的任何方法，实质执行的都是invoke方法
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //前置增强
                        advice.before();
                        Object invo = method.invoke(target, args);
                        //后置增强
                        advice.after();
                        return invo;
                    }
                }
        );

        proxyInstance.save();
    }
}
