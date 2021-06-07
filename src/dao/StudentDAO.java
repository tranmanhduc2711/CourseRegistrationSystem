package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import sample.HibernateUtil;
import sample.User;

import javax.persistence.Query;
import java.util.List;

public class StudentDAO {
    static Session session;

    public static List<User> getAllStudentInCLass(String class_Id)
    {
        session = HibernateUtil.getSessionFactory().openSession();
        List<User> student = null;
        try
        {
            final String hql = "select s from User s, Classinfo c where s.id = c.userId " +
                    "and c.classId = '" +class_Id+ "' " +
                    "and s.role = 1";
            Query query = session.createQuery(hql);
            student=query.getResultList();
        }
        catch (HibernateException e)
        {
            System.out.println(e);
        }
        finally {
            session.close();
        }
        return student;
    }

    public static List<User> getAllStudentInCLassById(String class_Id,String id)
    {
        session = HibernateUtil.getSessionFactory().openSession();
        List<User> student = null;
        try
        {
            final String hql = "select s from User s, Classinfo c where s.id = c.userId " +
                    "and c.classId = '" +class_Id+ "' " +
                    "and s.role = 1";
            Query query = session.createQuery(hql);
            student=query.getResultList();
        }
        catch (HibernateException e)
        {
            System.out.println(e);
        }
        finally {
            session.close();
        }
        return student;
    }

    public static List<User> getAllStudentInCourse(Integer courseId)
    {
        session = HibernateUtil.getSessionFactory().openSession();
        List<User> student = null;
        try
        {
            final String hql = "select s from User s, Attend a where s.id = a.studentId " +
                    "and a.courseId = '" + courseId+"' " +
                    "and s.roleId = 'STU' ";
            Query query = session.createQuery(hql);
            student=query.list();
        }
        catch (HibernateException e)
        {
            System.out.println(e);
        }
        finally {
            session.close();
        }
        return student;
    }
}
