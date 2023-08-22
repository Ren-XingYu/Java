package x.y.z.dao.impl;

import x.y.z.dao.UserDao;

public class UserDaoImplXml implements UserDao {

    @Override
    public void save() {
        System.out.println("user dao save.");
    }
}
