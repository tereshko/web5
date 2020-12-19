package crud;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class CrudApp {

    private static SessionFactory factory;

    public static void init() {
        factory = new Configuration().configure("/crud/hibernate.cfg.xml").buildSessionFactory();
    }

    public static void shutDown() {
        factory.close();
    }

    public static void create() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            Product product = new Product("milk", 50);

            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
    }

    public static void findById(int id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            Product product = session.get(Product.class, id);
            System.out.println(product);

            session.getTransaction().commit();
        }

    }

    public static void saveOrUpdate(int id, String name, int price) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product productForSave = new Product(name, price);

            session.saveOrUpdate(productForSave);
            session.getTransaction().commit();
        }
    }

    public static void deleteById(int id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product == null) {
                System.out.println("Product was deleted before");
            } else {
                session.delete(product);
            }
            session.getTransaction().commit();
        }
    }

    public static void findAll() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            List<Product> productList = session.createCriteria(Product.class).list();

            System.out.println(productList);
            session.getTransaction().commit();
        }
    }


    public static void main(String[] args) {
        try {
            init();
//            create();
//            findById(1);
//            deleteById(1);
//            saveOrUpdate(11,"milk", 2);
            findAll();
            shutDown();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shutDown();
        }

    }
}
