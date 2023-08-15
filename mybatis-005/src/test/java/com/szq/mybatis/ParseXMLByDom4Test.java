package com.szq.mybatis;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class ParseXMLByDom4Test {

    @Test
    public void testParseMyBatisConfigXML() throws Exception{
        //创建SAXReader对象
        SAXReader reader=new SAXReader();
        //获取输入流
        //读XML文件，返回document对象。document对象是文档对象，代表了整个XMl文件
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("mybatis.config.xml");
        Document document = reader.read(is);
//        System.out.println(document);
        Element rootElement = document.getRootElement();
        String rootElementName= rootElement.getName();
//        System.out.println(name);
        //获取default默认的环境
        String xpath="/configuration/environments";//xpath是做标签路径匹配的。能够让我们快速定位XML文件的元素
        Element element = (Element)document.selectSingleNode(xpath);
//        System.out.println(element);
        //获取属性的值
        String aDefault = element.attributeValue("default");
//        System.out.println("默认环境的id"+aDefault);

        //获取具体环境的id
        xpath="/configuration/environments/environment[@id='"+aDefault+"']";
//        System.out.println(xpath);
        Element element1 = (Element)document.selectSingleNode(xpath);
//        System.out.println(element1);
//      获取environment结点下transactionManager结点
        Element transactionManager = element1.element("transactionManager");
        String type = transactionManager.attributeValue("type");
//        System.out.println("s事务管理器类型"+type);
        //获取dataSource结点
        Element dataSource = element1.element("dataSource");
        String type1 = dataSource.attributeValue("type");
//        System.out.println("数据源的类型"+type1);
        //获取dataSource结点下的所有结点
        List<Element> elements = dataSource.elements();
        elements.forEach(element2 -> {
            String name = element2.attributeValue("name");
            String value = element2.attributeValue("value");
            System.out.println(name + ":" + value);
        });

        //获取所有mapper标签
        xpath="//mapper";       //不想从根下开始获取，你想从任意位置
        List<Node> mappers = document.selectNodes(xpath);
        mappers.forEach(mapper ->{
            Element mapperEl=(Element) mapper;
            String resource = mapperEl.attributeValue("resource");
            System.out.println(resource);

        });

    }

    @Test
    public void name() {
    }

    @Test
    public void testParseSqlMapperXML() throws Exception{
        SAXReader reader=new SAXReader();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("CarMapper.xml");
        Document document = reader.read(is);
        //获取namespace
        String xpath="/mapper";
        Element mapper = (Element)document.selectSingleNode(xpath);
        String namespace = mapper.attributeValue("namespace");
//        System.out.println(namespace);
        //获取mapper结点的所有标签
        List<Element> elements = mapper.elements();
        elements.forEach(element -> {
            //获取sqlId
            String id = element.attributeValue("id");
            System.out.println(id);
            //获取resultType属性
            String resultType = element.attributeValue("resultType");
            System.out.println(resultType);
            //获取标签中的sql语句（表示获取标签中的文本内容，而且去除前后空白
            String textTrim = element.getTextTrim();
            System.out.println(textTrim);
            //转换
            String s = textTrim.replaceAll("#\\{[0-9A-Za-z_$]*}", "?");
            System.out.println(s);
        });
    }


}
