package zui.web.filter;


import javax.print.DocFlavor;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/*")
public class SensitiveWordFilter implements Filter {
    private List<String> list = new ArrayList<>();  //敏感词汇集合
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //获取文件真实路径
        ServletContext servletContext = filterConfig.getServletContext();
        String realPath = servletContext.getRealPath("/WEB-INF/classes/SensitiveWords.txt");
        //读取文件
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(realPath));
            //将文件的每一行数据添加到list中
            String line = null;
            while((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
            bufferedReader.close();
            System.out.println(list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //创建代理对象，增强getParameter方法
        ServletRequest proxyRequest = (ServletRequest) Proxy.newProxyInstance(servletRequest.getClass().getClassLoader(), servletRequest.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //增强getParameter方法
                //判断是否是getParameter方法
                if(method.getName().equals("getParameter")) {
                    System.out.println("在filter里");
                    String value = (String) method.invoke(servletRequest, args);
                    if(value != null) {
                        for (String str : list) {
                            if(value.contains(str)) {
                                value = value.replaceAll(str, "***");
                            }
                        }
                    }
                    return value;
                }else{
                    return method.invoke(servletRequest, args);
                }
                //判断方法名是否是 getParameterMap

                //判断方法名是否是 getParameterValue
            }
        });
        //放行呀！！！
        filterChain.doFilter(proxyRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
