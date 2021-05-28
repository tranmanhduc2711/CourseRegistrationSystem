package dao;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import sample.HibernateUtil;
import java.util.List;
import sample.User;

import javax.persistence.Query;
import java.util.List;

public class UserDAO {
    private static Session session= HibernateUtil.getSessionFactory().openSession();
    public static List<User> getAllUser()
    {
        session = HibernateUtil.getSessionFactory().openSession();
        List<User> user = null;
        try
        {
            final String hql = "from User ";
            Query query = session.createQuery(hql);
            user=query.getResultList();
        }
        catch (HibernateException e)
        {
            System.out.println(e);
        }
        finally {
            session.close();
        }
        return user;
    }
    public static List<User> getAllUserTeacher()
    {


        List<User> teacher_users=null;

        try{
            final String hql= " from User  ";
            Query query=session.createQuery(hql);

            teacher_users= query.getResultList();

        }catch(Exception e)
        {
            System.out.print(e);
        }finally {
            session.close();
        }
        return teacher_users;
    }
    public static void add_Teacher(User t)
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
    public  static void delete_teacher(User t){
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

    public static void addUser(User user)
    {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(user);
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

    public static void updateUser(User user)
    {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(user);
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
