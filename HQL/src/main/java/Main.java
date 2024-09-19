import config.FactoryConfiguration;
import entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


        // Unique one Data from the customer table 👽❤️

        Query<Customer> query = session.createQuery("FROM Customer WHERE cid = :id", Customer.class);
        query.setParameter("id", 1);
        Customer customer = query.uniqueResult();
        System.out.println("Customer ID: " + customer.getCid());


        // Load only one row 👽❤️

        Query<Customer> cquery = session.createQuery("FROM Customer WHERE cid = :id", Customer.class);
        cquery.setParameter("id", 1);
        Customer c1 = cquery.getSingleResult();
        System.out.println("Customer ID: " + c1.getCid());
        System.out.println("Customer Name: " + c1.getName());


        // Load only one column 👽❤️

        Query<String> query1 = session.createQuery("SELECT name FROM Customer WHERE cid = :id", String.class);
        query1.setParameter("id", 1);
        String name = query1.getSingleResult();
        System.out.println("Customer Name: " + name);


        // Load multiple columns 👽❤️

        Query<Object[]> query2 = session.createQuery("SELECT cid, name FROM Customer WHERE cid = :id", Object[].class);
        query2.setParameter("id", 1);
        Object[] objects = query2.getSingleResult();
        System.out.println("Customer ID: " + objects[0]);
        System.out.println("Customer Name: " + objects[1]);


        // HQL Insert Query 👽❤️

        String hql = "INSERT INTO Customer (cid, name) VALUES (:id, :name)";
        Query Insertquery = session.createQuery(hql);
        Insertquery.setParameter("id", 6);
        Insertquery.setParameter("name", "Ben");
        int result = Insertquery.executeUpdate();

        System.out.println("Rows affected: " + result);


        // HQL Insert Query for Address 👽❤️

        String hql1 = "INSERT INTO Address (aid, street, city, country, cid) VALUES (:aid, :street, :city, :country, :cid)";
        Query Insertquery1 = session.createQuery(hql1);
        Insertquery1.setParameter("aid", 6);
        Insertquery1.setParameter("street", "123 Main St");
        Insertquery1.setParameter("city", "Colombo");
        Insertquery1.setParameter("country", "Sri Lanka");
        Insertquery1.setParameter("cid", 6);
        int result1 = Insertquery1.executeUpdate();
        System.out.println("Rows affected: " + result1);


        // Update Query 👽❤️

        String updateHql = "UPDATE Customer SET name = :newName WHERE cid = :id";
        Query updateQuery = session.createQuery(updateHql);
        updateQuery.setParameter("newName", "Updated Name");
        updateQuery.setParameter("id", 1);
        int updateResult = updateQuery.executeUpdate();
        System.out.println("Updated rows: " + updateResult);


        // Delete Query 👽❤️

        String deleteHql = "DELETE FROM Customer WHERE cid = :id";
        Query deleteQuery = session.createQuery(deleteHql);
        deleteQuery.setParameter("id", 2);
        int deleteResult = deleteQuery.executeUpdate();
        System.out.println("Deleted rows: " + deleteResult);


        // Select specific columns for a specific customer 👽❤️

        Query<Object[]> query3 = session.createQuery("SELECT cid, name FROM Customer WHERE cid = :id", Object[].class);
        query3.setParameter("id", 1);
        Object[] rst1 = query3.getSingleResult();
        System.out.println("Customer ID: " + rst1[0] + ", Name: " + rst1[1]);



        // Select specific columns for all customers 👽❤️

        Query<Object[]> query4 = session.createQuery("SELECT cid, name FROM Customer", Object[].class);
        List<Object[]> results2 = query4.getResultList();
        for (Object[] rst2 : results2) {
            System.out.println("Customer ID: " + rst2[0] + ", Name: " + rst2[1]);
        }


        // Join query 👽❤️

        Query<Object[]> query5 = session.createQuery("SELECT a.aid, a.street, c.name FROM Address a INNER JOIN a.cid c", Object[].class);
        List<Object[]> results3 = query5.getResultList();
        for (Object[] rst3 : results3) {
            System.out.println("Address ID: " + rst3[0] + ", Street: " + rst3[1] + ", Customer Name: " + rst3[2]);
        }

        transaction.commit();
        session.close();
    }
}