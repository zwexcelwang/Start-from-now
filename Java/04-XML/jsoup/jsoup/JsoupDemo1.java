package jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class JsoupDemo1 {

    public static void main(String[] args) throws IOException {
        // 获取Document对象，根据xml文档获取
        //获取student.xml的path
        String path = JsoupDemo1.class.getClassLoader().getResource("student.xml").getPath();
        System.out.println(path);

        //解析xml文档，加载文档进内存，获取dom树 --->  Document
        Document document = Jsoup.parse(new File(path), "utf-8");
        //获取元素对象 Element
        Elements elements = document.getElementsByTag("name");

        System.out.println(elements.size());

        //获取第一个element对象
        Element element = elements.get(0);
        String name = element.text();
        System.out.println(name);
    }
}