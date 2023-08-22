package x.y.z;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import x.y.z.config.SpringConfig;
import x.y.z.domain.User;
import x.y.z.service.UserService;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        List<User> userList = context.getBean(UserService.class).findAll();
        System.out.println(userList);
    }
}
