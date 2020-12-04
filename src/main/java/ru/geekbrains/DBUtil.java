package ru.geekbrains;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManagerFactory;

public class DBUtil {
    private static final EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    private DBUtil() {}

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
