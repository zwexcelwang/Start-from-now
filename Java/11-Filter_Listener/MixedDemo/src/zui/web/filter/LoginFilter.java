package zui.web.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //0.强制转换
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        //1. 获取资源请求路径
        String uri = httpServletRequest.getRequestURI();
        //2. 判断是否包含登录相关的资源路径,要注意排除 css、js、图片验证码等资源
        if(uri.contains("/login.jsp") || uri.contains("loginServlet") || uri.contains("/css/") || uri.contains("/js/") || uri.contains("/fonts/") || uri.contains("checkCodeServlet")) {
            //就是想登录，放行
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            //3.从session中获取user
            Object user = httpServletRequest.getSession().getAttribute("user");
            if(user != null) {
                filterChain.doFilter(servletRequest, servletResponse);
            }else {
                httpServletRequest.setAttribute("login_msg","您尚未登录，请登录");
                httpServletRequest.getRequestDispatcher("/login.jsp").forward(httpServletRequest, servletResponse);
            }
        }

//        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
