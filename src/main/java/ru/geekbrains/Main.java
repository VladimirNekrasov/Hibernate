package ru.geekbrains;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class Main {


    public static void main(String[] args) {
        PrepareDataApp.forcePrepareData();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите номер операции, который вы хотите сделать \n 1) узнать список покупок клиента" +
                "\n 2) узнать кто покупал конкретный товар" +
                "\n 3) удалить товар" +
                "\n 4) удалить клиента");
        try {
            String userAnswer = reader.readLine();
            if (userAnswer.equals("1")){
                System.out.println("введите id клиента");
                String clientId = reader.readLine();
                Long clientIdLong = Long.parseLong(clientId);
                shopingList(clientIdLong);
            } else if (userAnswer.equals("2")){
                System.out.println("введите id продукта");
                String productId = reader.readLine();
                Long productIdLong = Long.parseLong(productId);
                shopingList(productIdLong);
            } else if (userAnswer.equals("3")){
                System.out.println("введите id продукта");
                String productId = reader.readLine();
                Long productIdLong = Long.parseLong(productId);
                deleteProduct(productIdLong);
            } else if (userAnswer.equals("4")) {
                System.out.println("введите id клиента");
                String clientId = reader.readLine();
                Long clientIdLong = Long.parseLong(clientId);
                deleteCustomer(clientIdLong);
            } else {
                System.out.println("Вы ввели некорретное значение");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



//
//        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
//        Customer customer = em.find(Customer.class, 1L);
//        Product product = em.find(Product.class, 1L);
//        shopingList(1L);
//        shopingList(2L);
//        whoBoughtProduct(2l);
//        deleteProduct(1L);
//        em.close();
    }

    public static void shopingList(Long customerId){
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p INNER JOIN p.customers c WHERE c.id = :customer_id", Product.class);
        query.setParameter("customer_id", customerId);
        List<Product> resultList = query.getResultList();
        System.out.println("Customer#" + customerId + " bought: \n" + resultList);
    }

    public static void whoBoughtProduct(Long productId){
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c INNER JOIN c.products d WHERE d.id = :product_id", Customer.class);
        query.setParameter("product_id", productId);
        List<Customer> resultList = query.getResultList();
        System.out.println("product_id#" + productId + " bought: \n" + resultList);
    }

    public static void deleteProduct(Long productId){
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Product product = em.find(Product.class, productId);
        em.remove(product);
        em.getTransaction().commit();
    }

    public static void deleteCustomer(Long customerId){
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Customer customer = em.find(Customer.class, customerId);
        em.remove(customer);
        em.getTransaction().commit();
    }

}


/*1. В базе данных (MySQL) необходимо реализовать возможность хранить информацию о покупателях
(id, имя) и товарах (id, название, стоимость). У каждого покупателя свой набор купленных товаров.
Задача: написать тестовое консольное приложение, которое позволит посмотреть, какие товары покупал клиент,
какие клиенты купили определенный товар, и предоставит возможность удалять из базы товары/покупателей.

2. * Добавить детализацию по паре «покупатель — товар»: сколько стоил товар в момент покупки клиентом.*/