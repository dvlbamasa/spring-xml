import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;

public class HibernateSession {

    private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure().build();
            Metadata metadata = new MetadataSources(standardRegistry)
            					.addAnnotatedClass(Person.class)
            					.addAnnotatedClass(Role.class)
            					.addAnnotatedClass(ContactInformation.class)
            					.addAnnotatedClass(Address.class)
            					.addAnnotatedClass(Name.class)
            					.getMetadataBuilder().build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        }
        return sessionFactory;
    }
    
	public static Session getSession() {
		Session session = getSessionFactory().openSession();
		return session;
	}
	
}