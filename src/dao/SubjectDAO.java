package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import sample.HibernateUtil;
import sample.Subject;
import sample.User;

import javax.persistence.Query;
import java.util.List;

public class SubjectDAO {
    private static Session session;
    public static List<Subject> getAllSubject()
    {
         session= HibernateUtil.getSessionFactory().openSession();

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
    public static void add_Subject(Subject t)
    {
        session= HibernateUtil.getSessionFactory().openSession();

        try{
            session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
        }catch (HibernateException e)
        {
            System.out.println(e);
        }
        finally {
            session.close();
        }
    }
    public  static void delete_sub(Subject t){
        session=HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(t);
            session.getTransaction().commit();
        }
        catch (HibernateException e)
        {
            System.out.print(e);
        }finally {
            session.close();
        }
    }
    public static void updateSubject(Subject sub)
    {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(sub);
            session.getTransaction().commit();
        }
        catch(HibernateException e)
        {
            System.out.println(e);
        }
        finally {
            session.close();
        }
    }
}
