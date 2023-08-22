package x.y.z.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import x.y.z.dao.BookDao;
import x.y.z.service.BookService;

@Service
public class BookServiceImplAnnotation implements BookService {
//    @Autowired
//    private BookDao bookDao;

    @Autowired
    @Qualifier("bookDao2")
    private BookDao bookDao;

    @Override
    public void save() {
        System.out.println("book service save.");
        bookDao.save();
    }
}
