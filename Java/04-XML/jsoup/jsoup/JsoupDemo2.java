package jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JsoupDemo2 {
    public static void main(String[] args) throws IOException {
        // 获取Document对象，根据xml文档获取
        //获取student.xml的path
        String path = JsoupDemo1.class.getClassLoader().getResource("student.xml").getPath();
        System.out.println(path);

        //parse （String html）：解析xml或html字符串
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "\n" +
                "<students>\n" +
                "\t<student number=\"heima_0001\">\n" +
                "\t\t<name id=\"itcast\">\n" +
                "\t\t\t<xing>张</xing>\n" +
                "\t\t\t<ming>三</ming>\n" +
                "\t\t</name>\n" +
                "\t\t<age>18</age>\n" +
                "\t\t<sex>male</sex>\n" +
                "\t</student>\n" +
                "\t<student number=\"heima_0002\">\n" +
                "\t\t<name>jack</name>\n" +
                "\t\t<age>18</age>\n" +
                "\t\t<sex>female</sex>\n" +
                "\t</student>\n" +
                "\n" +
                "</students>";
        Document document = Jsoup.parse(str);
        System.out.println(document);

        // parse解析URL：通过网络路径获取指定的html或xml的文档对象
        URL url = new URL("https://baike.baidu.com/item/jsoup");
        Document document1 = Jsoup.parse(url, 10000);
        System.out.println(document1);

//        //解析xml文档，加载文档进内存，获取dom树 --->  Document
//        Document document = Jsoup.parse(new File(path), "utf-8");
//        //获取元素对象 Element
//        Elements elements = document.getElementsByTag("name");
//
//        System.out.println(elements.size());

        //获取第一个element对象
//        Element element = elements.get(0);
//        String name = element.text();
//        System.out.println(name);
    }
}
