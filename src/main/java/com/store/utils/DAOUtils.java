package com.store.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAOUtils {

    private static EntityManager entityManager;

    public static EntityManager getEntityManager(){
        if (entityManager != null){
            return entityManager;
        }
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("factory");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        DAOUtils.entityManager = entityManager;
        return DAOUtils.entityManager;
    }

}
