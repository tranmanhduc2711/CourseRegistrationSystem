package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import sample.HibernateUtil;
import sample.Semester;


import javax.persistence.Query;
import java.util.List;

public class SemesterDAO {
    private static Session session;
    public static List<Semester> getAllSemester()
    {
        session= HibernateUtil.getSessionFactory().openSession();

        List<Semester> sem=null;
        try{
            final String hql= " from Semester ";
            Query query=session.createQuery(hql);

            sem= query.getResultList();

        }catch(Exception e)
        {
            System.out.print(e);
        }finally {
            session.close();
        }
        return  sem;
    }
    public static void add_Semester(Semester t)
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
    public  static void delete_sem(Semester t){
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
    public static void updateSemester(Semester sub)
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
