import config.FactoryConfiguration;
import entity.Address;
import entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        Customer student = new Customer(1, "Chamath");
        Address address = new Address(1, "120/B","Panadura", "Sri Lanka",student);

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(student);
        session.save(address);
        transaction.commit();
        session.close();


    }
}
