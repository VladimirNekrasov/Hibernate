package ru.geekbrains;


import javax.persistence.EntityManager;
import java.math.BigDecimal;


public class PrepareDataApp {
    public static void forcePrepareData() {

        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();

        em.getTransaction().begin();
        Product product = new Product(null, "iPhone 12 pro Max", new BigDecimal(1200));
        Product product1 = new Product(null, "Samsung galaxy s20+", new BigDecimal(1100));
        Product product2 = new Product(null, "Xiaomi mi 11 pro", new BigDecimal(800));
        Product product3 = new Product(null, "Huawei P40 pro", new BigDecimal(900));
        Product product4 = new Product(null, "Honor 30 pro", new BigDecimal(700));
        em.persist(product);
        em.persist(product1);
        em.persist(product2);
        em.persist(product3);
        em.persist(product4);
        em.getTransaction().commit();

        em.getTransaction().begin();
        Customer customer = new Customer(null, "John");
        customer.addProduct(product1);
        customer.addProduct(product4);
        Customer customer1 = new Customer(null, "Bob");
        customer1.addProduct(product3);
        Customer customer2 = new Customer(null, "Mark");
        customer2.addProduct(product3);
        customer2.addProduct(product1);
        Customer customer3 = new Customer(null, "Tom");
        em.persist(customer);
        em.persist(customer1);
        em.persist(customer2);
        em.persist(customer3);
        em.getTransaction().commit();
        em.close();
    }

    public static void main(String[] args) {
        forcePrepareData();
    }
}
