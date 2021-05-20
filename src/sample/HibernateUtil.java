package sample;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static{
        //create configuration
        try {
            Configuration config = new Configuration();
            config.configure();
            sessionFactory = config.buildSessionFactory();
        }catch (Exception e)
        {}
    }

    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}
