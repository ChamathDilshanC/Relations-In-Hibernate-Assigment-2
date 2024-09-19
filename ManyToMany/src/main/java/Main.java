import config.FactoryConfiguration;
import entity.Address;
import entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Address> addresses = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer(1, "Chamath",addresses);
        Customer customer2 = new Customer(2, "Amandi",addresses);
        Address address1 = new Address(1, "120/B","Panadura", "Sri Lanka",customers);
        Address address2 = new Address(2, "120/B","Colombo", "Sri Lanka",customers);


        addresses.add(address1);
        addresses.add(address2);
        customers.add(customer1);
        customers.add(customer2);

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(customer1);
        session.save(customer2);
        session.save(address1);
        session.save(address2);

        
        transaction.commit();
        session.close();


    }
}
