package x.y.z.factory;

import x.y.z.dao.OrderDao;
import x.y.z.dao.impl.OrderDaoImplXml;

/**
 * 静态工厂初始化Bean
 */
public class OrderDaoFactory {
    public static OrderDao getOrderDao(){
        System.out.println("order factory setup.");
        return new OrderDaoImplXml();
    }
}
