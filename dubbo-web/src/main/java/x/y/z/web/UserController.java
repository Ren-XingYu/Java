package x.y.z.web;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import x.y.z.pojo.User;
import x.y.z.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    //@Autowired // 本地注入

    /**
     * 远程注入
     *
     * 1、从zookeeper注册中心获取userService的访问url
     * 2、进程远程调用RPC
     * 3、将结果封装为代理对象。对变量赋值。
     */
    //@Reference(timeout = 1000)   // 如果调用方设置了超时时间,则以调用方超时时间为准,否则以提供方超时时间为准
    //@Reference(loadbalance = "random") // 通过loadbalance字段来配置负载均衡策略
    //@Reference(cluster = "failover") // 通过cluster字段来配置集群容错策略
    //@Reference(mock = "force:return null") // 服务降级：消费方对服务的调用都直接返回null,不发起远程调用
    @Reference(version = "v2.0") // 通过version字段来调用相同服务的不同版本
    private UserService service;

    @RequestMapping(value = "/sayHello",method = RequestMethod.GET)
    public String sayHello(){
        return service.sayHello();
    }

    @RequestMapping(value = "/findUser",method = RequestMethod.GET)
    public User findUser(){
        return service.findUserById(1);
    }
}
