package x.y.z.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import x.y.z.domain.User;
import x.y.z.exception.BusinessException;
import x.y.z.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Result findAll(){
        try {
            //int i = 1/0;
            List<User> data = userService.findAll();
            Result result = new Result();
            result.setCode(Code.SUCCESS);
            result.setMessage("success");
            result.setData(data);
            return result;
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(),Code.FAIL);
        }
    }
}
