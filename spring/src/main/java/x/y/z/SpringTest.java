package x.y.z;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import x.y.z.config.SpringConfig;
import x.y.z.dao.BookDao;
import x.y.z.dao.OrderDao;
import x.y.z.dao.UserDao;
import x.y.z.service.BookService;

import javax.sql.DataSource;

public class SpringTest {

    @Test
    public void testIoc(){
        // ApplicationContext加载的Bean都是立即加载的
        //ApplicationContext context = new ClassPathXmlApplicationContext("applicationContextXml.xml");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContextXml.xml");
        // 注册关闭钩子函数(关闭虚拟机前先关闭Ioc容器)
        context.registerShutdownHook();
        BookDao bookDao = (BookDao) context.getBean("bookDao");
        bookDao.save();

        BookService bookService = (BookService) context.getBean("bookService");
        bookService.save();

        // 比较暴力的关闭,推荐使用关闭钩子函数
        //context.close();
    }

    @Test
    public void testStaticFactoryMethod(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContextXml.xml");
        OrderDao orderDao = (OrderDao) context.getBean("orderDao");
        orderDao.save();
    }

    @Test
    public void testInstanceFactoryMethod(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContextXml.xml");
        UserDao userDao = (UserDao) context.getBean("userDao");
        userDao.save();
    }

    @Test
    public void testBean(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContextXml.xml");
        //BookDao bookDao = context.getBean("bookDao",BookDao.class);
        BookDao bookDao = context.getBean(BookDao.class);
        bookDao.save();
    }

    @Test
    public void testAnnotation(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContextAnnotation.xml");
        BookDao bookDao = context.getBean(BookDao.class);
        bookDao.save();
    }

    @Test
    public void testSpringConfig(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookService bookService = context.getBean(BookService.class);
        bookService.save();

        DataSource dataSource = context.getBean(DataSource.class);
        System.out.println(dataSource);
    }

    @Test
    public void testAop(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookDao bookDao = context.getBean("bookDao",BookDao.class);
        System.out.println(bookDao);
        System.out.println(bookDao.getClass());
        bookDao.update();
        System.out.println(bookDao.select());
    }
}
