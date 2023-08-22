package x.y.z.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import x.y.z.dao.BookDao;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// @Component提供了三个衍生注解：@Controller @Service @Repository
@Repository("bookDao")
//@Scope("prototype")
public class BookDaoImplAnnotation implements BookDao {
    @Override
    public void save() {
        System.out.println("book dao save.");
    }

    @PostConstruct
    public void init(){
        System.out.println("book dao init.");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("book dao destroy.");
    }
}
