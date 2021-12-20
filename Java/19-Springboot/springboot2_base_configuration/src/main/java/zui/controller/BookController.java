package zui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zui.MyDataSource;

import java.security.SecureRandom;

//Rest模式
@RestController
@RequestMapping("/book")
public class BookController {

    //读取yaml数据中的单一数据
    @Value("${country}")
    private String country1;

    @Value("${user1.username}")
    private String name1;

    @Value("${likes[1]}")
    private String hobby1;
    @Value("${tempDir}")
    private String tempDir;

//    使用自动装配将所有的数据对封装到一个environment对象中
    @Autowired
    private Environment env;

    @Autowired
    private MyDataSource myDataSource;

    @GetMapping
    public String getById(){
        System.out.println("springboot is running...");
        System.out.println(country1);
        System.out.println(name1);
        System.out.println(hobby1);
        System.out.println(tempDir);
        System.out.println("========================");
        System.out.println(env.getProperty("user1.username"));
        System.out.println(env.getProperty("country"));
        System.out.println(env.getProperty("likes[1]"));
        System.out.println(env.getProperty("tempDir"));

        System.out.println(myDataSource);

        return "springboot is running";
    }
}
