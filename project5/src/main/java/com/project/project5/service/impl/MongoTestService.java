package com.project.project5.service.impl;

import com.project.project5.dao.MongoTestDao;
import com.project.project5.entity.mongodb.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName MongoTestService
 * @date 2020/4/14 14:34
 */
@Service
public class MongoTestService {

    @Autowired
    private MongoTestDao mongoTestDao;

    /**
     * 新增
     * @param book
     * @return
     */
    public void insert(Book book) {
        mongoTestDao.insert(book);
    }


    /**
     * 查询所有记录
     * @return
     */
    public List<Book> findAll() {
        return mongoTestDao.findAll();
    }

    /**
     * 查询单条
     * @param bookId
     * @return
     */
    public Book getBookById(String bookId) {
        return mongoTestDao.getBookById(bookId);
    }

    /**
     * 通过姓名查询信息
     * @param name
     * @return
     */
    public Book getBookByName(String name) {
        return mongoTestDao.getBookByName(name);
    }

    /**
     * 更新
     * @param book
     * @return
     */
    public String updateBook(Book book) {
        return mongoTestDao.updateBook(book);
    }

    /**
     * 删除信息
     * @param book
     * @return
     */
    public String deleteBook(Book book) {
        return mongoTestDao.deleteBook(book);
    }

    /**
     * 根据id删除信息
     * @param bookId
     * @return
     */
    public String deleteBookById(String bookId) {
        return mongoTestDao.deleteBookById(bookId);
    }

    /**
     * 根据查询条件查询信息
     * @param search
     * @return
     */
    public List<Book> findByLikes(String search) {
        return mongoTestDao.findByLikes(search);
    }
}
