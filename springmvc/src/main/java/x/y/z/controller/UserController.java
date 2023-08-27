package x.y.z.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import x.y.z.pojo.User;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


// Controller和ResponseBody可以合二为一，变为RestController
//@Controller + @ResponseBody -> @RestController;
@Controller
//@ResponseBody
@RequestMapping("/users")
public class UserController {

    // 普通参数
    @RequestMapping( value = "/save",method = RequestMethod.POST)
    @ResponseBody  // 设置当前控制器返回值为响应体
    public String save(@RequestParam("name") String name, @RequestParam("age") int age){
        System.out.println("user name="+name+" age="+age);
        return "{'module':'springmvc'}";
    }

    // 普通参数
    @RequestMapping( value = "/delete/{id}",method = RequestMethod.DELETE)
    //@DeleteMapping(value = "/delete/{id}")
    @ResponseBody  // 设置当前控制器返回值为响应体
    public String delete(@PathVariable Integer id){
        System.out.println(id);
        return "{'module':'springmvc'}";
    }

    // name=xxx
    // age=xxx
    // address.province=xxx
    // address.city=xxx
    @RequestMapping( "/pojoParam")
    @ResponseBody
    public String pojoParam(User user){
        System.out.println(user);
        return "{'module':'springmvc'}";
    }

    @RequestMapping( "/jsonPojoParam")
    @ResponseBody
    public String jsonPojoParam(@RequestBody User user){
        System.out.println(user);
        return "{'module':'springmvc'}";
    }

    // likes=1
    // likes=2
    // likes=3
    @RequestMapping( "/arrayParam")
    @ResponseBody
    public String arrayParam(String[] likes){
        System.out.println(Arrays.toString(likes));
        return "{'module':'springmvc'}";
    }

    // likes=1
    // likes=2
    // likes=3
    @RequestMapping( "/listParam")
    @ResponseBody
    public String listParam(@RequestParam List<String> likes){ // list需要加@RequestParam注解
        System.out.println(likes);
        return "{'module':'springmvc'}";
    }

    // 需要在配置类中开启EnableWebMvc
    @RequestMapping( "/jsonParam")
    @ResponseBody
    public String jsonParam(@RequestBody List<String> likes){ // list需要加@RequestParam注解
        System.out.println(likes);
        return "{'module':'springmvc'}";
    }

    @RequestMapping( "/dateParam")
    @ResponseBody
    public String dateParam(@DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        System.out.println(date);
        return "{'module':'springmvc'}";
    }

    // 跳转页面
    @RequestMapping( "/toJumpPage")
    public String toJumpPage(){
        return "page.jsp";
    }

    // 响应文本
    @RequestMapping( "/toText")
    @ResponseBody
    public String toText(){
        return "response text";
    }

    // 响应POJO
    @RequestMapping( "/toJsonPOJO")
    @ResponseBody
    public User toJsonPOJO(){
        User user = new User();
        user.setName("zhangsan");
        user.setAge(20);
        return user;
    }



}
