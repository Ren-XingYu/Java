package x.y.z.service.impl;

import x.y.z.dao.BookDao;
import x.y.z.service.BookService;

public class BookServiceImplXml implements BookService {

    private BookDao bookDao;


    public BookServiceImplXml(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void save() {
        System.out.println("book service save.");
        bookDao.save();
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
