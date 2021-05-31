package dao;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import sample.Clazz;
import sample.HibernateUtil;
import sample.User;

import java.util.List;


import javax.persistence.Query;
import java.util.List;
public class CLassDAO {
    private static Session session;
    public static List<Clazz> getAllClass()
    {
        session= HibernateUtil.getSessionFactory().openSession();

        List<Clazz> clazz=null;

        try{
            final String hql= " from Clazz ";
            Query query=session.createQuery(hql);

            clazz= query.getResultList();

        }catch(Exception e)
        {
            System.out.print(e);
        }finally {
            session.close();
        }
        return clazz;
    }
    public static void add_Class(Clazz t)
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
    public  static void delete_Class(Clazz t){
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


}
