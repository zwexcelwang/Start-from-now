package zui.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
//        创建真实对象
        Lenovo lenovo = new Lenovo();
//        动态增强lenovo对象
//        三个参数
//        类加载器：真实对象.getClass().getClassLoader()
//        接口数组：真实对象.getClass().getInterfaces()
//        处理器：new InvocationHandler()
        ISaleComputer proxy_lenovo = (ISaleComputer) Proxy.newProxyInstance(lenovo.getClass().getClassLoader(), lenovo.getClass().getInterfaces(), new InvocationHandler() {
            /**
             * 代理逻辑编写的方法：代理对象的所有方法都会触发该方法的执行
             * @param proxy 代理对象
             * @param method 代理对象调用的方法，被封装为的对象
             * @param args 代理对象调用的方法，传递的实际参数
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("invoke方法被执行啦");
//                System.out.println(method.getName());
//                System.out.println(args[0]);

                //判断是否是sale方法
                if(method.getName().equals("sale")) {
                    //增强参数
                    double money = (double) args[0];
                    money = money * 0.85;
                    System.out.println("专车接送。。");
                    //使用真实对象调用该方法
                    String obj = (String) method.invoke(lenovo, money);
                    System.out.println("免费送货。。");
                    // 增强返回值
                    return obj + " + 鼠标垫";
                }else {
                    Object obj = method.invoke(lenovo, args);
                    return obj;
                }

            }
        });
//        调用方法
        String computer = proxy_lenovo.sale(8000);
        System.out.println(computer);
//        proxy_lenovo.show();
    }

}
