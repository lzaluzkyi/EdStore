package com.store.dao.impl;

import com.store.dao.ItemDAO;
import com.store.entity.Category;
import com.store.entity.Item;
import com.store.service.ItemService;
import com.store.service.impl.ItemServiceImpl;
import com.store.utils.DAOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class ItemDAOImpl implements ItemDAO {

    Logger logger = Logger.getLogger(ItemDAOImpl.class.getName());


    @Override
    public Item update(Item item) {
        logger.info("Start saving -> " + item.toString());
        EntityManager entityManager = DAOUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(item);
        transaction.commit();
        logger.info("End saving new item id is " + item.getId());
        return item;
    }

    @Override
    public Item save(Item item) {
        logger.info("Start saving -> " + item.toString());
        EntityManager entityManager = DAOUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(item);
        transaction.commit();
        logger.info("End saving new item id is " + item.getId());
        return item;
    }

    @Override
    public Item findById(Long id) {
        EntityManager entityManager = DAOUtils.getEntityManager();
        Item item = entityManager.find(Item.class, id);
        return item;
    }

    @Override
    public List<Item> findByCategory(Category category) {
        EntityManager entityManager = DAOUtils.getEntityManager();
        List resultList = entityManager.createQuery("select i from Item as i where i.category.id = :categoryId", Item.class)
                .setParameter("categoryId", category.getId()).getResultList();
        return resultList;
    }

    @Override
    public List<Item> findByName(String startWith) {
        EntityManager entityManager = DAOUtils.getEntityManager();
        startWith = "%" + startWith;
        List resultList = entityManager.createNativeQuery("select * from  item where name like (:name)", Item.class)
                .setParameter("name", startWith).getResultList();
        return resultList;
    }

    @Override
    public List<Item> getAll() {
        EntityManager entityManager = DAOUtils.getEntityManager();
        List resultList = entityManager.createQuery("select i from Item as i ", Item.class)
                .getResultList();
        return resultList;
    }

    @Override
    public void delete(Long itemId) {
        EntityManager entityManager = DAOUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Item byId = findById(itemId);
        entityManager.remove(byId);
        transaction.commit();
    }
}
