package zui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import zui.domain.Role;
import zui.domain.User;
import zui.service.RoleService;
import zui.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user-list");
        List<User> userList = userService.list();
        modelAndView.addObject("userList", userList);
        System.out.println(userList);
        return modelAndView;
    }

    @RequestMapping("/saveUI")
    public ModelAndView saveUI(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user-add");
        List<Role> roleList = roleService.list();
        modelAndView.addObject("roleList", roleList);
//        System.out.println(roleList);
        return modelAndView;
    }

    @RequestMapping("/save")
    public String save(User user, Long[] roleIds){
        userService.save(user, roleIds);
        return "redirect:/user/list";
    }

    @RequestMapping("/del/{userId}")
    public String del(@PathVariable("userId") Long userId){
        userService.delete(userId);
        return "redirect:/user/list";
    }

    @RequestMapping("/login")
    public String login(String username, String password, HttpSession httpSession){
        User user = userService.login(username, password);
        if(user != null){
//            登录成功，将user存储到session
            httpSession.setAttribute("user", user);
            return "redirect:/index.jsp";
        }
        return "redirect:/login.jsp";
    }
}
