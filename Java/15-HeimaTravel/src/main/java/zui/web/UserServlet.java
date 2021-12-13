package zui.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;
import zui.domain.ResultInfo;
import zui.domain.User;
import zui.service.IUserService;
import zui.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    private IUserService userService = new UserService();

    public void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("register servlet!!!");
        //验证校验
        String checkCode = req.getParameter("check");
        //从sesion中获取验证码
        HttpSession httpSession = req.getSession();
        String checkCodeServer = (String) httpSession.getAttribute("CHECKCODE_SERVER");
        httpSession.removeAttribute("CHECKCODE_SERVER");
        System.out.println("用户填的：" + checkCode + " || 服务器上的：" + checkCodeServer);
        //比较
        if(checkCode == null || !checkCodeServer.equalsIgnoreCase(checkCode)) {
            //验证码错误
            ResultInfo info = new ResultInfo();
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //将info对象序列化为json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(json);
            return;
        }
        System.out.println("验证码对的！！");

        //如果验证码是对的
        //1.获取数据
        Map<String, String[]> map = req.getParameterMap();

        //2.封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.调用service完成注册
//        IUserService userServive = new UserService();
        boolean flag = userService.register(user);
        ResultInfo info = new ResultInfo();
        //4.响应结果
        if(flag){
            //注册成功
            info.setFlag(true);
        }else{
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败!");
        }
        //将info对象序列化为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);

        //将json数据写回客户端
        //设置content-type
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    public void findUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("find user servlet!!!");
        //从session中获取登录用户
        Object user = req.getSession().getAttribute("user");
        //将user写回客户端

        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        mapper.writeValue(resp.getOutputStream(),user);
    }
    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("login servlet!!!");
        //1.获取用户名和密码数据
        Map<String, String[]> map = req.getParameterMap();
        //2.封装User对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //3.调用Service查询
//        IUserService userService = new UserService();
        // 从数据库返回来的user
        User u  = userService.login(user);

        ResultInfo info = new ResultInfo();

        //4.判断用户对象是否为null
        if(u == null){
            //用户名密码或错误
            info.setFlag(false);
            info.setErrorMsg("用户名密码或错误");
        }
        //5.判断用户是否激活
        if(u != null && !"Y".equals(u.getStatus())){
            //用户尚未激活
            info.setFlag(false);
            info.setErrorMsg("您尚未激活，请激活");
        }
        //6.判断登录成功
        if(u != null && "Y".equals(u.getStatus())){
            req.getSession().setAttribute("user",u);//登录成功标记

            //登录成功
            info.setFlag(true);
        }

        //响应数据
        ObjectMapper mapper = new ObjectMapper();

        resp.setContentType("application/json;charset=utf-8");
        mapper.writeValue(resp.getOutputStream(),info);
    }

    public void activeUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("active user servlet!!!");

        //1.获取激活码
        String code = req.getParameter("code");
        if(code != null){
            //2.调用service完成激活
//            IUserService userService = new UserService();
            boolean flag = userService.active(code);

            //3.判断标记
            String msg = null;
            if(flag){
                //激活成功
                msg = "激活成功，请<a href='login.html'>登录</a>";
            }else{
                //激活失败
                msg = "激活失败，请联系管理员!";
            }
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write(msg);
        }
    }
    public void exit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("exit servlet!!!");
        //1.销毁session
        req.getSession().invalidate();
        //2.跳转
        resp.sendRedirect(req.getContextPath()+"/login.html");
        System.out.println(req.getContextPath());
    }


}
