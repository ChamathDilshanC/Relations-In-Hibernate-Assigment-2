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

            // 1. Select Queries 👽❤️

            // Unique result
            Query<Customer> uniqueQuery = session.createQuery("FROM Customer WHERE cid = :id", Customer.class);
            uniqueQuery.setParameter("id", 1);
            Customer customer = uniqueQuery.uniqueResult();
            System.out.println("Unique result - Customer ID: " + customer.getCid());

            // Single row
            Query<Customer> singleRowQuery = session.createQuery("FROM Customer WHERE cid = :id", Customer.class);
            singleRowQuery.setParameter("id", 1);
            Customer singleCustomer = singleRowQuery.getSingleResult();
            System.out.println("Single row - Customer Name: " + singleCustomer.getName());

            // Single column
            Query<String> singleColumnQuery = session.createQuery("SELECT name FROM Customer WHERE cid = :id", String.class);
            singleColumnQuery.setParameter("id", 1);
            String name = singleColumnQuery.getSingleResult();
            System.out.println("Single column - Customer Name: " + name);

            // Multiple columns
            Query<Object[]> multiColumnQuery = session.createQuery("SELECT cid, name FROM Customer WHERE cid = :id", Object[].class);
            multiColumnQuery.setParameter("id", 1);
            Object[] multiColumnResult = multiColumnQuery.getSingleResult();
            System.out.println("Multiple columns - ID: " + multiColumnResult[0] + ", Name: " + multiColumnResult[1]);

            // Specific columns for all customers
            Query<Object[]> allCustomersQuery = session.createQuery("SELECT cid, name FROM Customer", Object[].class);
            List<Object[]> allCustomersResult = allCustomersQuery.getResultList();
            for (Object[] result : allCustomersResult) {
                System.out.println("All customers - ID: " + result[0] + ", Name: " + result[1]);
            }

            // 2. Insert Queries 👽❤️

            // Insert into Customer table
            String customerInsertHql = "INSERT INTO Customer (cid, name) VALUES (:id, :name)";
            Query customerInsertQuery = session.createQuery(customerInsertHql);
            customerInsertQuery.setParameter("id", 6);
            customerInsertQuery.setParameter("name", "Ben");
            int customerInsertResult = customerInsertQuery.executeUpdate();
            System.out.println("Customer insert - Rows affected: " + customerInsertResult);

            // Insert into Address table
            String addressInsertHql = "INSERT INTO Address (aid, street, city, country, cid) VALUES (:aid, :street, :city, :country, :cid)";
            Query addressInsertQuery = session.createQuery(addressInsertHql);
            addressInsertQuery.setParameter("aid", 6);
            addressInsertQuery.setParameter("street", "123 Main St");
            addressInsertQuery.setParameter("city", "Colombo");
            addressInsertQuery.setParameter("country", "Sri Lanka");
            addressInsertQuery.setParameter("cid", 6);
            int addressInsertResult = addressInsertQuery.executeUpdate();
            System.out.println("Address insert - Rows affected: " + addressInsertResult);

            // 3. Update Query 👽❤️

            String updateHql = "UPDATE Customer SET name = :newName WHERE cid = :id";
            Query updateQuery = session.createQuery(updateHql);
            updateQuery.setParameter("newName", "Updated Name");
            updateQuery.setParameter("id", 1);
            int updateResult = updateQuery.executeUpdate();
            System.out.println("Update - Rows affected: " + updateResult);

            // 4. Delete Query 👽❤️

            String deleteHql = "DELETE FROM Customer WHERE cid = :id";
            Query deleteQuery = session.createQuery(deleteHql);
            deleteQuery.setParameter("id", 2);
            int deleteResult = deleteQuery.executeUpdate();
            System.out.println("Delete - Rows affected: " + deleteResult);

            // 5. Join Query 👽❤️

            Query<Object[]> joinQuery = session.createQuery("SELECT a.aid, a.street, c.name FROM Address a INNER JOIN a.cid c", Object[].class);
            List<Object[]> joinResults = joinQuery.getResultList();
            for (Object[] result : joinResults) {
                System.out.println("Join - Address ID: " + result[0] + ", Street: " + result[1] + ", Customer Name: " + result[2]);
            }

            transaction.commit();
            session.close();
    }
}