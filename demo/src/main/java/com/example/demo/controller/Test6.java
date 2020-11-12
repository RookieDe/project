package com.example.demo.controller;

import com.example.demo.config.annotation.MyTarget;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;

/**
 * Shanghai yejia Diaital Technology Co.,Ltd.
 *
 * @author chenhongde
 * @ClassName Test6
 * @date 2020/10/19 13:55
 */
public class Test6 {

    @MyTarget
    @Deprecated
    public void info(){

    }
    public static void main(String[] args) throws Exception {
        Annotation[] annos = Test6.class.getMethod("info").getAnnotations();
        for(Annotation anno : annos){
            if (anno instanceof MyTarget){
                MyTarget mt = (MyTarget)anno;
                System.out.println(mt.name());
                System.out.println(mt.ages());
                System.out.println(annos.getClass().getName());
                anno.getClass().getDeclaredFields();
            }
            System.out.println(anno);
        }
    }




}
