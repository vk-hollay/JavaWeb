package com.atguigu.web.pojo;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.List;

public class Dom4jTest {
    @Test
    public void test1() {
        // 创建一个SaxReader输入流，去读取 xml配置文件，生成Document对象
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read("src/books.xml");
            System.out.println(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() throws DocumentException {
        //1.读取 book.xml文件
        //在junit测试中，相对路径是从模块名开始算
        SAXReader reader = new SAXReader();
        Document document = reader.read("src/books.xml");
        //2.通过Document对象获取根元素
        Element rootElement = document.getRootElement();
        System.out.println(rootElement);
        //3.通过根元素获取book标签对象
        //element()和elements()都是通过标签名查找子元素
        List<Element> books = rootElement.elements("book");
        //4.遍历，处理每个book标签转化为 Book类
        for (Element book : books) {
            //asXML() 把标签对象转换为标签字符串
            System.out.println(book.asXML());

            Element nameElement = book.element("name");
            System.out.println(nameElement.asXML());
            //getText() 可以获取标签中的文本内容
            String nametext = nameElement.getText();

            //elementText() 直接获取指定标签的文本内容
            String priceText = book.elementText("price");
            String authorText = book.elementText("author");
            //attributeValue() 直接获取标签指定属性的值
            String snValue = book.attributeValue("sn");
            System.out.println(new Book(snValue, nametext, Double.parseDouble(priceText), authorText));

            System.out.println("--------------------------------------------------------------------");
        }
    }
}
