package config;


import entity.Address;
import entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    public static FactoryConfiguration factoryConfiguration;
    private  final SessionFactory sessionFactory;

    private FactoryConfiguration(){
        Configuration configuration = new Configuration()
                .configure()
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Address.class);
        sessionFactory = configuration.configure().buildSessionFactory();
    }

    public static FactoryConfiguration getInstance(){
        if(factoryConfiguration == null) factoryConfiguration = new FactoryConfiguration();
        return factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
