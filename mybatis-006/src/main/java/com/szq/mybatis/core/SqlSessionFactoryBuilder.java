package com.szq.mybatis.core;

import com.szq.mybatis.util.Resources;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.*;

/**
 * SqlSessionFactory构建器对象
 * 通过SqlSessionFactoryBuilder的build方法来解析godbatis-config.xml文件
 * 然后创建SqlSessionFactory对象
 */
public class SqlSessionFactoryBuilder {
    public SqlSessionFactoryBuilder(){

    }
    /**
     * 解析godbatis-config.xml文件，来构建SqlSessionFactory对象
     * @param in 指向godbatis-config.xml文件的一个输入流
     * @return SqlSessionFactory
     */
    public SqlSessionFactory build(InputStream in){
        SqlSessionFactory factory=null;
        try {
            //解析godbatis-config.xml文件
            SAXReader reader=new SAXReader();
            Document document = reader.read(in);
            Element environmenet= ((Element) document.selectSingleNode("/configuration/environments"));
            String defaulted= environmenet.attributeValue("default");
            Element environment  = ((Element) document.selectSingleNode("/configuration/environments/environment[@id='" + defaulted + "']"));
            Element transactionManager = environment.element("transactionManager");
            Element dataSource = environment.element("dataSource");
            List<String> sqlMapperXMLPathList=new ArrayList<>();
            List<Node> nodes = document.selectNodes("//mapper");//获取整个配置文件中所有的mapper配置
            nodes.forEach(node->{
                Element mapper = (Element) node;
                String resource=mapper.attributeValue("resource");
                sqlMapperXMLPathList.add(resource);
            });
            //获取数据源对象
            DataSource dataSource1=getDataSource(dataSource);
            //获取事务管理器
            Transaction transaction=getTransaction(transactionManager,dataSource1);
            Map<String,MappedStatement> mappedStatementMap=getMapperedStatments(sqlMapperXMLPathList);
            factory=new SqlSessionFactory(transaction,mappedStatementMap);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return factory;
    }

    /**
     * 解析所有的sqlMapper.xml文件，然后构建Map集合
     * @param sqlMapperXMLPathList
     * @return
     */
    private Map<String, MappedStatement> getMapperedStatments(List<String> sqlMapperXMLPathList) {
        Map<String,MappedStatement> mappedStatements=new HashMap<>();
        sqlMapperXMLPathList.forEach(sqlMapperXMLPath->{
            try {
                SAXReader reader=new SAXReader();
                Document document = reader.read(Resources.getResourceAsStream(sqlMapperXMLPath));
                Element mapper = (Element) document.selectSingleNode("mapper");
                String namespace = mapper.attributeValue("namespace");
                List<Element> elements = mapper.elements();
                elements.forEach(element->{
                    String id = element.attributeValue("id");
                    String sqlid=namespace+"."+id;
                    String resultType = element.attributeValue("resultType");
                    String sql = element.getTextTrim();
                    MappedStatement mappedStatement = new MappedStatement(sql, resultType);
                    mappedStatements.put(sqlid,mappedStatement);
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return mappedStatements;
    }

    /**
     * 获取事务管理器的
     * @param transactionManager 事务管理器标签对象
     * @param dataSource1 数据源对象
     * @return
     */
    private Transaction getTransaction(Element transactionManager, DataSource dataSource1) {
        Transaction transaction=null;
        String type = transactionManager.attributeValue("type").trim().toUpperCase();
        if (Const.JDBC_TRANSACTION.equals(type)) {
            transaction=new JdbcTransaction(dataSource1,false);
        }
        if (Const.MANGED_TRANSACTION.equals(type)) {
            transaction=new ManagedTransaction();
        }
        return transaction;
    }

    /**
     * 获取数据源对象
     * @param dataSource
     * @return
     */
    private DataSource getDataSource(Element dataSource) {
        Map<String,String > map=new HashMap<>();
        //获取所有property
        List<Element> propertyElts= dataSource.elements("property");
        propertyElts.forEach(propertyElt->{
            String name = propertyElt.attributeValue("name");
            String value= propertyElt.attributeValue("value");
            map.put(name,value);
        });
        DataSource source=null;
        String type = dataSource.attributeValue("type").trim().toUpperCase();
        if (Const.UN_POOLED_DATASOURCE.equals(type)) {
            source=new UnPooledDataSource(map.get("driver"),map.get("url"),map.get("username"),map.get("password"));
        }
        if (Const.POOLED_DATASOURCE.equals(type)) {
            source=new PooledDataSource();
        }
        if (Const.JNDI_DATASOURCE.equals(type)) {
            source=new JNDIDataSource();
        }
        return source;
    }
}
