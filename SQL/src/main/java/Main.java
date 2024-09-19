import config.FactoryConfiguration;
import entity.Address;
import entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


        // Select Query 👽❤️

        NativeQuery<Customer> selectQuery = session.createNativeQuery("SELECT * FROM customer", Customer.class);
        List<Customer> customers = selectQuery.list();
        for (Customer customer : customers) {
            System.out.println(customer.getCid() + " | " + customer.getName());
        }


        // Insert Query 👽❤️

        NativeQuery<?> insertQuery = session.createNativeQuery("INSERT INTO customer(cid, name) VALUES (?, ?)");
        insertQuery.setParameter(1, 1);
        insertQuery.setParameter(2, "Chamath");
/*        int insertedRows = insertQuery.executeUpdate();
        System.out.println("Inserted rows: " + insertedRows);*/


        //Insert Query For Address 👽❤️

        NativeQuery<?> insertQuery1 = session.createNativeQuery("INSERT INTO address(aid, street, city, country, customer_id) VALUES (?, ?, ?, ?, ?)");
        insertQuery1.setParameter(1, 1);
        insertQuery1.setParameter(2, "123 Main St");
        insertQuery1.setParameter(3, "Colombo");
        insertQuery1.setParameter(4, "Sri Lanka");
        insertQuery1.setParameter(5, 1);
/*        int insertedRows1 = insertQuery1.executeUpdate();
        System.out.println("Inserted rows: " + insertedRows1);*/


        // Update Query 👽❤️

        NativeQuery<?> updateQuery = session.createNativeQuery("UPDATE customer SET name = ? WHERE cid = ?");
        updateQuery.setParameter(1, "Dilshan");
        updateQuery.setParameter(2, 1);
/*        int updatedRows = updateQuery.executeUpdate();
        System.out.println("Updated rows: " + updatedRows);*/


        // Delete Query 👽❤️

        NativeQuery<?> deleteQuery = session.createNativeQuery("DELETE FROM customer WHERE cid = ?");
        deleteQuery.setParameter(1, 3);
/*        int deletedRows = deleteQuery.executeUpdate();
        System.out.println("Deleted rows: " + deletedRows);*/


        // Search by name 👽❤️

        NativeQuery<Customer> searchByNameQuery = session.createNativeQuery("SELECT * FROM customer WHERE name = ?", Customer.class);
        searchByNameQuery.setParameter(1, "Dilshan");
        List<Customer> customersByName = searchByNameQuery.list();
        for (Customer customer : customersByName) {
            System.out.println("Found by name: " + customer.getCid() + " | " + customer.getName());
        }


        // Search by id 👽❤️

        NativeQuery<Customer> searchByIdQuery = session.createNativeQuery("SELECT * FROM customer WHERE cid = ?", Customer.class);
        searchByIdQuery.setParameter(1, 1);
        Customer customerById = (Customer) searchByIdQuery.uniqueResult();
        if (customerById != null) {
            System.out.println("Found by ID: " + customerById.getCid() + " | " + customerById.getName());
        } else {
            System.out.println("No customer found with the given ID.");
        }


        // Join Query 👽❤️
        String joinQuery = "SELECT c.cid, c.name, a.aid, a.street, a.city, a.country " +
                "FROM customer c " +
                "LEFT JOIN address a ON c.cid = a.customer_id";

        NativeQuery<?> joinNativeQuery = session.createNativeQuery(joinQuery);
        List<Object[]> joinResults = (List<Object[]>) joinNativeQuery.list();

        System.out.println("Join Query Results:");
        for (Object[] result : joinResults) {
            Integer customerId = (Integer) result[0];
            String customerName = (String) result[1];
            Integer addressId = (Integer) result[2];
            String street = (String) result[3];
            String city = (String) result[4];
            String country = (String) result[5];

            System.out.println("Customer ID: " + customerId +
                    ", Name: " + customerName +
                    ", Address ID: " + (addressId != null ? addressId : "N/A") +
                    ", Street: " + (street != null ? street : "N/A") +
                    ", City: " + (city != null ? city : "N/A") +
                    ", Country: " + (country != null ? country : "N/A"));
        }
        transaction.commit();
        session.close();
    }
}