package x.y.z.service.impl;

// import org.springframework.stereotype.Service;
import org.apache.dubbo.config.annotation.Service;
import x.y.z.pojo.User;
import x.y.z.service.UserService;


// 创建Bean,并放入Spring的IoC容器中
//@Service

// 将这个类提供的方法(服务)对外发布。将访问的地址：IP，端口，路径，注册到注册中心中。
// 当前服务3秒超时,默认1秒
@Service(timeout = 3000, retries = 0, version = "v2.0")
public class UserServiceImplV2 implements UserService {
    @Override
    public String sayHello() {
        System.out.println("v2.0");
        return "hello dubbo";
    }

    @Override
    public User findUserById(int id) {
        System.out.println("v2.0");
        User user=new User(1,"zhansan","123");
        return user;
    }
}
