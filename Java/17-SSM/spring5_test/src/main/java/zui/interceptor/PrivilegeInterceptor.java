package zui.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import zui.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PrivilegeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //逻辑：判断用户是否登录，本质判断session中有没有user
        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");
        if(user == null){
            //没有登陆
            response.sendRedirect(request.getContextPath()+"/login.jsp");
            return false;
        }
//        放行，访问资源
        return true;
    }
}
