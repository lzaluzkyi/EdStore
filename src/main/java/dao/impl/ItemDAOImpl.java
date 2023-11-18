package dao.impl;

import dao.ItemDAO;
import entity.Category;
import entity.Item;
import utils.DAOUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.logging.Logger;

public class ItemDAOImpl implements ItemDAO {

    Logger logger = Logger.getLogger(ItemDAOImpl.class.getName());

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
        List resultList = entityManager.createNativeQuery("select * from  item where category_id = :categoryId", Item.class)
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
}
