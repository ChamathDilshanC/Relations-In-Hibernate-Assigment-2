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
        Customer customer = new Customer(1, "Chamath",addresses);
        Address address1 = new Address(1, "120/B","Panadura", "Sri Lanka",customer);
        Address address2 = new Address(2, "120/B","Colombo", "Sri Lanka",customer);
        Address address3 = new Address(3, "120/B","Colombo", "Sri Lanka",customer);

        addresses.add(address1);
        addresses.add(address2);
        addresses.add(address3);

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(customer);
        session.save(address1);
        session.save(address2);
        session.save(address3);
        transaction.commit();
        session.close();


    }
}
