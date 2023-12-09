package com.store.dao.impl;

import com.store.dao.ItemDAO;
import com.store.entity.Category;
import com.store.entity.Item;
import com.store.utils.DAOUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class CategoryDAOImpl {

    Logger logger = Logger.getLogger(CategoryDAOImpl.class.getName());


    public Category findById(Long id) {
        EntityManager entityManager = DAOUtils.getEntityManager();
        Category category = entityManager.find(Category.class, id);
        return category;
    }

}
