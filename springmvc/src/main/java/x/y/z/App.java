package x.y.z;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import x.y.z.config.SpringConfig;
import x.y.z.controller.UserController;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        //System.out.println(ctx.getBean(UserController.class));
    }
}
