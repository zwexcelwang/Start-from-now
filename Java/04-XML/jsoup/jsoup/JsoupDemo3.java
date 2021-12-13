package jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class JsoupDemo3 {
    public static void main(String[] args) throws IOException {
        // 获取Document对象，根据xml文档获取
        //获取student.xml的path
        String path = JsoupDemo1.class.getClassLoader().getResource("student.xml").getPath();
        System.out.println(path);
        //解析xml文档，加载文档进内存，获取dom树 --->  Document
        Document document = Jsoup.parse(new File(path), "utf-8");
        Elements elements = document.select("name");
        System.out.println(elements);

        //查询id值为itcast的元素
        Elements elements1 = document.select("#itcast");
        System.out.println(elements1);

        //5.获取student标签并且number属性值为heima_0001的age子标签
        //获取student标签并且number属性值为heima_0001
        Elements elements2 = document.select("student[number=\"heima_0001\"]");
        System.out.println(elements2);
        System.out.println("----------------");

        //获取student标签并且number属性值为heima_0001的age子标签
        Elements elements3 = document.select("student[number=\"heima_0001\"] > age");
        System.out.println(elements3);

    }
}
