package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.function.Predicate;

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
