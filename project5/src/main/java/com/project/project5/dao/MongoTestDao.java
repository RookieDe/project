package com.project.project5.dao;

import com.project.project5.domain.BaseDomain;
import com.project.project5.entity.mongodb.Book;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName MongoTestDao
 * @date 2020/4/14 14:34
 */
@Repository
public class MongoTestDao extends BaseDomain<Book> {


    public List<Book> findAll() {
        return mongoTemplate.findAll(entityClass);
    }


    public Book getBookById(String bookId) {
        Query query = new Query(Criteria.where("bookId").is(bookId));
        return mongoTemplate.findOne(query, Book.class);
    }


    public Book getBookByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query,entityClass);

    }

    public String updateBook(Book book) {
        Query query = new Query(Criteria.where("bookId").is(book.getBookId()));
        Update update = new Update()
                .set("publish", book.getPublish())
                .set("info", book.getInfo())
                .set("updateTime",new Date());
        // updateFirst 更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query, update, entityClass);
        // updateMulti 更新查询返回结果集的全部
        // mongoTemplate.updateMulti(query,update,entityClass);
        // upsert 更新对象不存在则去添加
        // mongoTemplate.upsert(query,update,entityClass);
        return "success";

    }

    public String deleteBook(Book book) {
        mongoTemplate.remove(book);
        return  "success";
    }


    public String deleteBookById(String bookId) {
        // findOne
        Book book = getBookById(bookId);
        // delete
        deleteBook(book);
        return "success";
    }


    public List<Book> findByLikes(String search) {
        Criteria criteria = Criteria.where("bookId").is(search);
        Criteria criteria2 = Criteria.where("name").regex(search);
        criteria.orOperator(criteria2);

        Query q1 = Query.query(criteria);
        Query query = new BasicQuery(q1.getQueryObject().toJson());
//        Long count = mongoTemplate.count(query, entityClass);
//        PageRequest request = PageRequest.of(model.getPage()-1, model.getPageSize());
//        query.with(request);

        List<Book> list = mongoTemplate.find(query, entityClass);
        return list;

    }
}
