package zui.web;

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

@WebServlet("/0registerServlet")// 不用了
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        IUserService userServive = new UserService();
        boolean flag = userServive.register(user);
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
}
