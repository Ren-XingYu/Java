package x.y.z.dao.impl;

import x.y.z.dao.BookDao;

public class BookDaoImplXml implements BookDao {

    private int connectionNumber;

    private String databaseName;

    public BookDaoImplXml(int connectionNumber, String databaseName) {
        this.connectionNumber = connectionNumber;
        this.databaseName = databaseName;
    }

    public BookDaoImplXml(){
        System.out.println("book dao constructor is running.");
    }

    @Override
    public void save() {
        System.out.println("book dao save.");
    }

    @Override
    public void update() {
        System.out.println("book dao update.");
    }

    @Override
    public int select() {
        System.out.println("book dao select.");
        return 0;
    }

    public void init(){
        System.out.println("book dao init.");
    }

    public void destroy(){
        System.out.println("book dao destroy.");
    }
}
