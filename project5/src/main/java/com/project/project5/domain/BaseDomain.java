package com.project.project5.domain;

import com.project.project5.entity.mongodb.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.lang.reflect.ParameterizedType;

/**
 * Shanghai *** Technology Co.,Ltd.
 *
 * @author RookieDe
 * @ClassName BaseDomain
 * @date 2020/4/14 14:42
 */
public class BaseDomain<T extends BaseModel> {

    protected Class<T> entityClass;

    @Autowired
    protected MongoTemplate mongoTemplate;

    public BaseDomain() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void insert(T model) {
        mongoTemplate.insert(model);
    }

    public void deleteByKeyVal(String key, Object saId) {
        mongoTemplate.remove(Query.query(Criteria.where(key).is(saId)), entityClass);
    }


}
