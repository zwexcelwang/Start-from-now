package zui.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import zui.dimain.User;
import zui.dimain.VO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    //localhost:8080/spring_mvc/user/quick
    @RequestMapping(value = "/quick", method = RequestMethod.GET)
    public String save() {
        System.out.println("Controller save running...");
        return "/success";
    }

    @RequestMapping(value = "/quick2")
    public ModelAndView save2() {
        System.out.println("Controller save2 running...");
        /*
        Model:模型，作用封装数据
        View：视图，作用展示数据
         */
        ModelAndView modelAndView = new ModelAndView();
        //设置模型数据
        modelAndView.addObject("username", "zhangsan");
        //设置视图名称
        modelAndView.setViewName("success");
        return modelAndView;
    }

    //SpringMVC解析的时候发现没有这个参数，就创建了给传过来
    @RequestMapping(value = "/quick3")
    public ModelAndView save3(ModelAndView modelAndView) {
        System.out.println("Controller save3 running...");
        /*
        Model:模型，作用封装数据
        View：视图，作用展示数据
         */
        //设置模型数据
        modelAndView.addObject("username", "zhangsan3");
        //设置视图名称
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @RequestMapping(value = "/quick4")
    public String save4(Model model) {
        System.out.println("Controller save4 running...");
        model.addAttribute("username"," zhangsan4");
        return "success";
    }

    @RequestMapping(value = "/quick5")
    public String save5(HttpServletRequest httpServletRequest) {
        System.out.println("Controller save5 running...");
        httpServletRequest.setAttribute("username","zhangsan5");
        return "success";
    }

    @RequestMapping(value = "/quick6")
    public void save6(HttpServletResponse httpServletResponse) throws IOException {
        System.out.println("Controller save6 running...");
        httpServletResponse.getWriter().print("hello");
    }

    @RequestMapping(value = "/quick7")
    @ResponseBody //告诉SpringMVC框架，该方法不进行视图跳转，直接进行数据响应
    public String save7(){
        System.out.println("Controller save7 running...");
        return "hello";
    }

    @RequestMapping(value = "/quick8")
    @ResponseBody //告诉SpringMVC框架，该方法不进行视图跳转，直接进行数据响应
    public String save8(){
        System.out.println("Controller save8 running...");
        return "{\"username\":\"zhangsan8\", \"age\": 18}";
    }

    @RequestMapping(value = "/quick9")
    @ResponseBody //告诉SpringMVC框架，该方法不进行视图跳转，直接进行数据响应
    public String save9() throws JsonProcessingException {
        System.out.println("Controller save9 running...");
        User user = new User();
        user.setUsername("zhangsan9");
        user.setAge(18);
        //使用json的转化工具将对象转换成json格式字符串
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        return json;
    }

    @RequestMapping(value = "/quick10")
    @ResponseBody //告诉SpringMVC框架，该方法不进行视图跳转，直接进行数据响应
//    期望SpringMVC自动将user转换成json字符串
    public User save10() {
        System.out.println("Controller save10 running...");
        User user = new User();
        user.setUsername("zhangsan10");
        user.setAge(18);
        return user;
    }

    @RequestMapping(value = "/quick11")
    @ResponseBody //告诉SpringMVC框架，该方法不进行视图跳转，直接进行数据响应
//    期望SpringMVC自动将user转换成json字符串
    public void save11(String username, int age) {
        System.out.println("Controller save11 running...");
        //http://localhost:8080/spring_mvc/user/quick11?username=zhangsan&age=11
        System.out.println(username);
        System.out.println(age);
    }

    @RequestMapping(value = "/quick12")
    @ResponseBody //告诉SpringMVC框架，该方法不进行视图跳转，直接进行数据响应
//    期望SpringMVC自动将user转换成json字符串
    public User save12(User user) {
        System.out.println("Controller save12 running...");
        //http://localhost:8080/spring_mvc/user/quick12?username=zhangsan&age=11
        System.out.println(user.toString());
        return user;
    }

    @RequestMapping(value = "/quick13")
    @ResponseBody //告诉SpringMVC框架，该方法不进行视图跳转，直接进行数据响应
//    期望SpringMVC自动将user转换成json字符串
    public void save13(String[] strs) {
        System.out.println("Controller save13 running...");
        System.out.println(Arrays.asList(strs));
//        http://localhost:8080/spring_mvc/user/quick13?strs=111&strs=222
    }

    @RequestMapping(value = "/quick14")
    @ResponseBody //告诉SpringMVC框架，该方法不进行视图跳转，直接进行数据响应
    public void save14(VO vo) {
        System.out.println("Controller save14 running...");
        System.out.println(vo.toString());
    }

    @RequestMapping(value = "/quick15")
    @ResponseBody //告诉SpringMVC框架，该方法不进行视图跳转，直接进行数据响应
    public void save15(@RequestBody List<User> userList) {
        System.out.println("Controller save15 running...");
        System.out.println(userList);
    }

    @RequestMapping(value = "/quick16")
    @ResponseBody //告诉SpringMVC框架，该方法不进行视图跳转，直接进行数据响应
    public void save16(@RequestParam(value="name", required = false, defaultValue = "zhangsan") String username) {
        System.out.println("Controller save16 running...");
        System.out.println(username);
    }

    @RequestMapping(value = "/quick17/{name}")
    @ResponseBody //告诉SpringMVC框架，该方法不进行视图跳转，直接进行数据响应
    public void save17(@PathVariable(value="name") String username) {
        System.out.println("Controller save17 running...");
//        http://localhost:8080/spring_mvc/user/quick17/zhangsan
        System.out.println(username);
    }

    @RequestMapping(value = "/quick18")
    @ResponseBody //告诉SpringMVC框架，该方法不进行视图跳转，直接进行数据响应
    public void save18(Date date) {
        System.out.println("Controller save18 running...");
        System.out.println(date);
    }


    @RequestMapping(value = "/quick19")
    @ResponseBody //告诉SpringMVC框架，该方法不进行视图跳转，直接进行数据响应
    public void save19(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession httpSession) {
        System.out.println("Controller save19 running...");
        System.out.println(httpServletRequest);
        System.out.println(httpServletResponse);
        System.out.println(httpSession);
    }

    @RequestMapping(value = "/quick20")
    @ResponseBody //告诉SpringMVC框架，该方法不进行视图跳转，直接进行数据响应
    public void save20(@RequestHeader(value = "User-Agent") String user_agent) {
        System.out.println("Controller save21 running...");
        System.out.println(user_agent);
    }

    @RequestMapping(value = "/quick21")
    @ResponseBody //告诉SpringMVC框架，该方法不进行视图跳转，直接进行数据响应
    public void save21(@CookieValue(value = "JSESSIONID") String jsessonId) {
        System.out.println("Controller save21 running...");
        System.out.println(jsessonId);
    }

    @RequestMapping(value = "/quick22")
    @ResponseBody //告诉SpringMVC框架，该方法不进行视图跳转，直接进行数据响应
    public void save22(String username, MultipartFile uploadFile, MultipartFile uploadFile2) throws IOException {
        System.out.println("Controller save22 running...");
        System.out.println(uploadFile);
        System.out.println(username);
        //        获取文件名称
        String uploadFilename = uploadFile.getOriginalFilename();
        uploadFile.transferTo(new File("E:\\WANGZIWEI\\IDEA_Project\\SpringDemos\\spring_mvc\\src\\main\\resources\\"+uploadFilename));
        String uploadFilename2 = uploadFile2.getOriginalFilename();
        uploadFile2.transferTo(new File("E:\\WANGZIWEI\\IDEA_Project\\SpringDemos\\spring_mvc\\src\\main\\resources\\"+uploadFilename2));

    }

    @RequestMapping(value = "/quick23")
    @ResponseBody //告诉SpringMVC框架，该方法不进行视图跳转，直接进行数据响应
    public void save23(String username, MultipartFile[] uploadFile) throws IOException {
        System.out.println("Controller save23 running...");
        System.out.println(username);
        for(MultipartFile file:uploadFile) {
            String uploadFilename = file.getOriginalFilename();
            file.transferTo(new File("E:\\WANGZIWEI\\IDEA_Project\\SpringDemos\\spring_mvc\\src\\main\\resources\\"+uploadFilename));
        }

    }
}
