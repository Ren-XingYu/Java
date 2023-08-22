package x.y.z.factory;

import x.y.z.dao.UserDao;
import x.y.z.dao.impl.UserDaoImplXml;

/**
 * 实例工厂初始化Bean
 */
public class UserDaoFactory {
    public UserDao getUserDao(){
        System.out.println("user factory setup.");
        return new UserDaoImplXml();
    }
}
