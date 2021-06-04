package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import sample.Courseregistrationsession;
import sample.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

public class CourseRegisDAO {
    private static Session session;

    public static List<Courseregistrationsession> getAllSession()
    {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Courseregistrationsession> info = null;
        try
        {
            final String hql = "from Courseregistrationsession ";
            Query query = session.createQuery(hql);
            info = query.getResultList();
        }
        catch (HibernateException e)
        {
            System.out.println(e);
        }
        finally {
            session.close();
        }
        return info;
    }
}
