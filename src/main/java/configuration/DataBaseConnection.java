package configuration;

import jakarta.persistence.EntityManagerFactory;
import model.Laptop;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class DataBaseConnection {
    public static EntityManagerFactory createEntityManagerFactory(){
        Properties properties =new Properties();
        properties.setProperty("org.hibernate.driver_class","org.postgresql.Driver");
        properties.setProperty(Environment.URL,"jdbc:postgresql://localhost:5432/my_database");
        properties.setProperty(Environment.USER,"postgres");
        properties.setProperty(Environment.PASS,"postgres");

        properties.setProperty(Environment.HBM2DDL_AUTO,"create");
        properties.setProperty(Environment.DIALECT,"org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty(Environment.SHOW_SQL,"true");
        properties.setProperty(Environment.FORMAT_SQL,"true");

        Configuration configuration = new Configuration();
        configuration.addProperties(properties);
        configuration.addAnnotatedClass(Laptop.class);

        EntityManagerFactory unwrap = configuration.buildSessionFactory().unwrap(EntityManagerFactory.class);
        return unwrap;
    }
}
