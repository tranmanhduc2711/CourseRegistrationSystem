package sample;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static{
        //create configuration
        try {
            Configuration config = new Configuration();
            config.configure();
            sessionFactory = config.buildSessionFactory();
        }catch (Throwable th)
        {
            throw  new ExceptionInInitializerError(th);
        }
    }

    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}
