package dao;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import sample.Classinfo;
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
    public static void updateClass(Clazz clazz)
    {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(clazz);
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

    public static List<Clazz> getClassById(String id)
    {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Clazz> clazz = null;
        try
        {
            final String hql = "from Clazz where Clazz.id like '%" + id+ "%'";
            Query query = session.createQuery(hql);
            clazz=query.getResultList();
        }
        catch (HibernateException e)
        {
            System.out.println(e);
        }
        finally {
            session.close();
        }
        return clazz;
    }

    public static void addStudentInClass(Classinfo classInfo)
    {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(classInfo);
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

    public static void deleteStudentInClass(Classinfo classInfo)
    {
        session = HibernateUtil.getSessionFactory().openSession();
        try
        {
            session.beginTransaction();
            session.delete(classInfo);
            session.getTransaction().commit();
        }
        catch (HibernateException e)
        {
            System.out.println(e);
        }
        finally {
            session.close();
        }
    }

    public static List<Classinfo> getAllInfoClass()
    {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Classinfo> info = null;
        try
        {
            final String hql = "from Classinfo ";
            Query query = session.createQuery(hql);
            info=query.getResultList();
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

    public static Integer getMale(String id)
    {
        session = HibernateUtil.getSessionFactory().openSession();
        Integer male=0;
        try
        {
            String hql = "select count(*) " +
                    "from Classinfo a, User  b " +
                    "where a.userId = b.id " +
                    "and a.classId = '" + id+"' " +
                    "and b.gender = 1 " +
                    "and b.role = 1";
            Query query = session.createQuery(hql);
            Object res = query.getSingleResult();
            male = ((Long)res).intValue();
        }
        catch (HibernateException e)
        {
            System.out.println(e);
        }
        finally {
            session.close();
        }
        return male;
    }

}
