package x.y.z.service;


import x.y.z.pojo.User;

public interface UserService {
    String sayHello();

    User findUserById(int id);
}
