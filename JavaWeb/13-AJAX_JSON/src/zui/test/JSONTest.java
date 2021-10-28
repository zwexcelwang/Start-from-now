package zui.test;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import zui.domain.Person;

import javax.management.ObjectName;
import java.io.IOException;
import java.util.*;


public class JSONTest {

    public void test1() throws JsonProcessingException {
        Person p = new Person();
        p.setAge(10);
        p.setGender("男");
        p.setName("张三");
        p.setBirthday(new Date());

        //创建JSON的核心对象
        ObjectMapper objectMapper = new ObjectMapper();

        /*
        转换方法
        writeValue(参数1，obj)
            参数1 ：
            File：将obj对象转换成JSON字符串，并保存到指定的文件中
            Writer：将obj对象转换为JSON字符串，并将json数据填充到字符输出流中
            OutputStream：将obj对象转换为JSON字符串，并将json数据填充到字节输出流中
        writeValueAsString(obj):将对象转为json字符串
         */
        String json = objectMapper.writeValueAsString(p);
        System.out.println(json);
    }


    public void test2() throws JsonProcessingException {
        Person p1 = new Person();
        p1.setAge(10);
        p1.setGender("男");
        p1.setName("张三");
        p1.setBirthday(new Date());

        Person p2 = new Person();
        p2.setAge(20);
        p2.setGender("男");
        p2.setName("张三");
        p2.setBirthday(new Date());

        Person p3 = new Person();
        p3.setAge(30);
        p3.setGender("男");
        p3.setName("张三");
        p3.setBirthday(new Date());
        List<Person> list = new ArrayList<Person>();

        list.add(p1);
        list.add(p2);
        list.add(p3);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(list);
        System.out.println(json);
    }


    public void test3() throws JsonProcessingException {

        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", 40);
        map.put("gender", "男");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(map);
        System.out.println(json);
    }

    @Test
    public void test4() throws IOException {
        String json = "{\"gender\":\"男\",\"name\":\"张三\",\"age\":40}";

        ObjectMapper objectMapper = new ObjectMapper();
        Person ps = objectMapper.readValue(json, Person.class);
        System.out.println(ps);
    }
}
