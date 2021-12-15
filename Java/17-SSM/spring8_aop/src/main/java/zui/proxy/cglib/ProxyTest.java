package zui.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import zui.proxy.jdk.TargetInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

    public static void main(String[] args) {

        //创建目标对象
        Target target = new Target();

        //获得增强的对象
        Advice advice = new Advice();

        // 1.创建增强器
        Enhancer enhancer = new Enhancer();
        // 2.设置父类（目标）
        enhancer.setSuperclass(Target.class);
        // 3.设置回调
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                //前置
                advice.before();
                Object invo = method.invoke(target, objects);
                advice.after();
                return invo;
            }
        });
        // 4.创建代理对象

        Target proxyInstance = (Target) enhancer.create();

        proxyInstance.save();
    }
}
