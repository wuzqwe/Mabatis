package com.szq.javassist;

import com.szq.bank.dao.AccountDao;
import javassist.*;
import org.junit.Test;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;

public class JavassistTest {

    @Test
    public void testGenerate() throws Exception {
        //获取类池，这个类池就是用来生成class的
        ClassPool pool = ClassPool.getDefault();
        //制造类（需要告诉javassistm,类名是啥）
        CtClass ctClass = pool.makeClass("com.szq.bank.dao.impl.AccountDaoImpl");
        //制造方法
        String methodCode="public void insert(){System.out.println(123);}";
        CtMethod ctMethod = CtMethod.make(methodCode, ctClass);
        //将方法添加到类中
        ctClass.addMethod(ctMethod);
        //在内存中生成class
        ctClass.toClass();
        //在加载到JVM当中，返回AccountDaoImpl类的字节码
        Class<?> clazz = Class.forName("com.szq.bank.dao.impl.AccountDaoImpl");
        //创建对象
        Object obj = clazz.newInstance();
        //获取AccountDaoImpl中insert的方法
        Method insertMehod = clazz.getDeclaredMethod("insert");
        //调用方法insert
        insertMehod.invoke(obj);
    }

    @Test
    public void testGenerate2() throws Exception{
        //获取类池
        ClassPool pool = ClassPool.getDefault();
        //制造类
        CtClass ctClass = pool.makeClass("com.szq.bank.dao.impl.AccountDaoImpl");
        //制造接口
        CtClass ctInterface = pool.makeInterface("com.szq.bank.dao.AccountDao");
        //添加接口到类中
        ctClass.addInterface(ctInterface);//AccountDaoImpl implements AccountDao
        //实现接口中的方法
        //制造方法
        CtMethod ctMethod = CtMethod.make("public void delete(){System.out.println(\"hello\");}", ctClass);
        //将方法添加到类中
        ctClass.addMethod(ctMethod);
        //在内存中生成类，同时将生成的类加载到JVM当中
        Class<?> clazz = ctClass.toClass();
        AccountDao accountDao =(AccountDao) clazz.newInstance();
        accountDao.delete();
    }

    @Test
    public void testGengerate3() throws Exception{
        //获取类池
        ClassPool pool = ClassPool.getDefault();
        //制造类
        CtClass ctClass = pool.makeClass("com.szq.bank.dao.impl.AccountDaoImpl");
        //制造接口
        CtClass ctInterface = pool.makeInterface("com.szq.bank.dao.AccountDao");
        //添加接口到类中
        ctClass.addInterface(ctInterface);//AccountDaoImpl implements AccountDao
        //实现接口中所有方法
        //获取接口中的所有方法
        Method[] methods = AccountDao.class.getDeclaredMethods();
        Arrays.stream(methods).forEach(method -> {
            //method是接口中的抽象方法
            //把method抽象方法给实现了
            try {
                //public void delete(){}
                //public int update(String actno,Double balance){}
                StringBuilder methodCode=new StringBuilder();
                methodCode.append("public ");//追加修饰符列表
                methodCode.append(method.getReturnType().getName());//追加返回值类型
                methodCode.append(" ");
                methodCode.append(method.getName());//追加方法名
                methodCode.append("(");
                //拼接参数 String actno,Double balance
                Class<?>[] parameterTypes = method.getParameterTypes();
                for (int i = 0; i < parameterTypes.length; i++) {
                    Class<?> parameterType = parameterTypes[i];
                    methodCode.append(parameterType.getName());
                    methodCode.append(" ");
                    methodCode.append("arg"+i);
                    if (i!=parameterTypes.length-1){
                        methodCode.append(",");
                    }
                }
                methodCode.append("){System.out.println(11111);}");
                methodCode.append("{");
                //动态添加return语句
                String returnTypeSimpleName = method.getReturnType().getSimpleName();
                if ("void".equals(returnTypeSimpleName)) {
                    methodCode.append("return 1;");
                }else if ("int".equals(returnTypeSimpleName)){
                    methodCode.append("return \"hello\";");
                }
                methodCode.append("}");
                System.out.println(methodCode);
                CtMethod ctMethod = CtMethod.make(methodCode.toString(), ctClass);
                ctClass.addMethod(ctMethod);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //在内存中生成class，并且加载到JVM当中
        Class<?> clazz = ctClass.toClass();
        //创建对象
        AccountDao accountDao = (AccountDao) clazz.newInstance();
        //调用方法
        accountDao.insert("aaaa");
        accountDao.delete();
        accountDao.update("aaaa",1000.0);
        accountDao.selectByActno("aaaa");
    }
}
