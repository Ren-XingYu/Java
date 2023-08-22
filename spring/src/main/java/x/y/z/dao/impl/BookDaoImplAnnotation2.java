package x.y.z.dao.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import x.y.z.dao.BookDao;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// @Component提供了三个衍生注解：@Controller @Service @Repository
@Repository("bookDao2")
//@Scope("prototype")
public class BookDaoImplAnnotation2 implements BookDao {

    @Value("${jdbc.username}")
    private String name;

    @Override
    public void save() {
        System.out.println("book dao2 save.");
    }

    @Override
    public void update() {
        System.out.println("book dao2 update.");
    }

    @Override
    public int select() {
        System.out.println("book dao2 select.");
        return 200;
    }

    @PostConstruct
    public void init(){
        System.out.println("book dao2 init.");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("book dao2 destroy.");
    }
}
