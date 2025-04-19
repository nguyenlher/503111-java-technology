import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        // Create a session
        Session session = null;
        Transaction transaction = null;

        try {
            System.out.println("Starting Hibernate test...");

            // Get Session
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // Create a manufacture
            Manufacture samsung = new Manufacture();
            samsung.setId("MF-001");
            samsung.setName("Samsung Electronics");
            samsung.setLocation("Seoul, South Korea");
            samsung.setEmployee(267937);

            // Create phones
            Phone galaxyS21 = new Phone();
            galaxyS21.setId("PH-001");
            galaxyS21.setName("Galaxy S21");
            galaxyS21.setPrice(799);
            galaxyS21.setColor("Phantom Gray");
            galaxyS21.setCountry("South Korea");
            galaxyS21.setQuantity(100);
            galaxyS21.setManufacture(samsung);

            Phone galaxyNote20 = new Phone();
            galaxyNote20.setId("PH-002");
            galaxyNote20.setName("Galaxy Note 20");
            galaxyNote20.setPrice(999);
            galaxyNote20.setColor("Mystic Bronze");
            galaxyNote20.setCountry("South Korea");
            galaxyNote20.setQuantity(75);
            galaxyNote20.setManufacture(samsung);

            // Set up the relationship
            List<Phone> samsungPhones = new ArrayList<>();
            samsungPhones.add(galaxyS21);
            samsungPhones.add(galaxyNote20);
            samsung.setPhones(samsungPhones);

            // Save objects
            session.save(samsung);
            session.save(galaxyS21);
            session.save(galaxyNote20);

            // Create another manufacture
            Manufacture apple = new Manufacture();
            apple.setId("MF-002");
            apple.setName("Apple Inc.");
            apple.setLocation("Cupertino, California");
            apple.setEmployee(147000);

            // Create an iPhone
            Phone iPhone13 = new Phone();
            iPhone13.setId("PH-003");
            iPhone13.setName("iPhone 13 Pro");
            iPhone13.setPrice(999);
            iPhone13.setColor("Sierra Blue");
            iPhone13.setCountry("USA");
            iPhone13.setQuantity(120);
            iPhone13.setManufacture(apple);

            Phone iPhone15 = new Phone();
            iPhone15.setId("PH-004");
            iPhone15.setName("iPhone 15 Pro Max");
            iPhone15.setPrice(1500);
            iPhone15.setColor("Titan White");
            iPhone15.setCountry("USA");
            iPhone15.setQuantity(120);
            iPhone15.setManufacture(apple);

            List<Phone> applePhones = new ArrayList<>();
            applePhones.add(iPhone13);
            applePhones.add(iPhone15);
            apple.setPhones(applePhones);

            // Save objects
            session.save(apple);
            session.save(iPhone13);
            session.save(iPhone15);

            // Commit the transaction
            transaction.commit();
            System.out.println("Objects saved successfully!");

            // Now retrieve and display the data
            System.out.println("\nRetrieving data from database...");
            List<Manufacture> manufactures = session.createQuery("from Manufacture", Manufacture.class).list();
            for (Manufacture m : manufactures) {
                System.out.println("Manufacture: " + m.getName() + ", Location: " + m.getLocation() +
                        ", Employees: " + m.getEmployee());
                System.out.println("Phones produced by " + m.getName() + ":");
                for (Phone p : m.getPhones()) {
                    System.out.println("  - " + p.getName() + " (" + p.getColor() + "), Price: $" +
                            p.getPrice() + ", Quantity: " + p.getQuantity());
                }
                System.out.println();
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            HibernateUtils.close();
            System.out.println("Hibernate session closed.");
        }
    }
}