package dao.impl;

import dao.OrderDAO;
import entity.Order;
import utils.DAOUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {


    @Override
    public Order create(Order order) {
        EntityManager entityManager = DAOUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            entityManager.persist(order);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return order;
    }

    @Override
    public Order getById(Long id) {
        return DAOUtils.getEntityManager().find(Order.class , id);
    }

    @Override
    public List<Order> getByIds(List<Long> ids) {
        EntityManager entityManager = DAOUtils.getEntityManager();
        List orders = entityManager.createNativeQuery("select * from our_order where id in (:ids)")
                .setParameter("ids", ids)
                .getResultList();
        return orders;
    }

    @Override
    public List<Order> getAll() {
        return DAOUtils.getEntityManager().createNativeQuery("select * FROM our_order" ).getResultList();
    }
}
