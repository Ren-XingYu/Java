package x.y.z.factory;

import org.springframework.beans.factory.FactoryBean;
import x.y.z.dao.UserDao;
import x.y.z.dao.impl.UserDaoImplXml;

/**
 * 使用FactoryBean实例化Bean:实例工厂实例化Bean的简化方式
 */
public class UserDaoFactoryBean implements FactoryBean<UserDao> {

    @Override
    public UserDao getObject() throws Exception {
        return new UserDaoImplXml();
    }

    @Override
    public Class<?> getObjectType() {
        return UserDao.class;
    }

    // 默认为单例
    @Override
    public boolean isSingleton() {
        return true;
    }
}
