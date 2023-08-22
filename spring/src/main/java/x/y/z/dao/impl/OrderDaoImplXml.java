package x.y.z.dao.impl;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import x.y.z.dao.OrderDao;

public class OrderDaoImplXml implements OrderDao, InitializingBean, DisposableBean {
    @Override
    public void save() {
        System.out.println("order dao save.");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("order dao destroy.");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("order dao init.");
    }
}
