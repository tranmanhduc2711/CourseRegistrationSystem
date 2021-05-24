package dao;

import org.hibernate.Session;
import sample.HibernateUtil;
import sample.Subject;

import javax.persistence.Query;
import java.util.List;

public class SubjectDAO {
    public static List<Subject> getAllSubject()
    {
        Session session= HibernateUtil.getSessionFactory().openSession();

        List<Subject> sub=null;
        try{
            final String hql= " from Subject ";
            Query query=session.createQuery(hql);

            sub= query.getResultList();

        }catch(Exception e)
        {
            System.out.print(e);
        }finally {
            session.close();
        }
        return  sub;
    }
}
