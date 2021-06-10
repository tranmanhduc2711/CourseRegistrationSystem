package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import sample.*;

import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    private static Session session;

    public static  List<CourseinfoPOJO> getAllCourseInSem(Integer sem)
    {
        session =HibernateUtil.getSessionFactory().openSession();
        List<CourseinfoPOJO> data = new ArrayList<CourseinfoPOJO>();
        try
        {
            final String hql = "from CoursePOJO c, Semester s, Subject sj, Session ss " +
                    "where c.semester = s.id " +
                    "and c.subjectId = sj.id " +
                    "and c.sessionId = ss.id " +
                    "and c.semester = "+ sem.toString();
            Query query = session.createQuery(hql);
            List<Object[]> res =query.getResultList();
            for(int i = 0;i<res.size();i++)
            {
                data.add(new CourseinfoPOJO(((CoursePOJO) res.get(i)[0]).getId(),
                        ((Subject) res.get(i)[2]).getId(),
                        ((Subject) res.get(i)[2]).getName(),
                        ((Subject) res.get(i)[2]).getCredits(),
                        ((CoursePOJO) res.get(i)[0]).getTeacher(),
                        ((CoursePOJO) res.get(i)[0]).getRoom(),
                        ((CoursePOJO) res.get(i)[0]).getDayOfWeek(),
                        ((sample.Session) res.get(i)[3]).toString(),
                        ((CoursePOJO) res.get(i)[0]).getMax()));
            }
        }
        catch (org.hibernate.HibernateException e)
        {
            System.out.println(e);
        }
        finally {
            session.close();
        }
        return data;
    }

    public static void addCourse(CoursePOJO course)
    {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(course);
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
    public static void addAttend(StudentInCourse sic)
    {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(sic);
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

    public static void deleteAttend(StudentInCourse sic)
    {
        session = HibernateUtil.getSessionFactory().openSession();
        try
        {
            session.beginTransaction();
            session.delete(sic);
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
    public static List<CourseinfoPOJO> getAllCourseOfStudent(Integer sem, String stuId)
    {
        session = HibernateUtil.getSessionFactory().openSession();
        List<CourseinfoPOJO> info = new ArrayList<CourseinfoPOJO>();
        try
        {
            final String hql = "from CoursePOJO c, Semester s, Subject sj, Session ss, StudentInCourse a " +
                    "where c.semester = s.id " +
                    "and c.subjectId = sj.id " +
                    "and c.sessionId = ss.id " +
                    "and a.course_id = c.id " +
                    "and c.semester = "+ sem.toString() +
                    " and a.student_id ='" +stuId+"'";
            Query query = session.createQuery(hql);
            List<Object[]> res =query.getResultList();
            for(int i = 0;i<res.size();i++)
            {
                info.add(new CourseinfoPOJO(((CoursePOJO)res.get(i)[0]).getId(),
                        ((Subject)res.get(i)[2]).getId(),
                        ((Subject)res.get(i)[2]).getName(),
                        ((Subject)res.get(i)[2]).getCredits(),
                        ((CoursePOJO)res.get(i)[0]).getTeacher(),
                        ((CoursePOJO)res.get(i)[0]).getRoom(),
                        ((CoursePOJO)res.get(i)[0]).getDayOfWeek(),
                        ((sample.Session)res.get(i)[3]).toString(),
                        ((CoursePOJO)res.get(i)[0]).getMax()));
            }
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
    public static List<CoursePOJO> getAllInSem(int semId)
    {
        session = HibernateUtil.getSessionFactory().openSession();
        List<CoursePOJO> course = null;
        try
        {
            final String hql = "from CoursePOJO where semester =" +semId;
            Query query = session.createQuery(hql);
            course = query.getResultList();
        }
        catch (HibernateException e)
        {
            System.out.println(e);
        }
        finally {
            session.close();
        }
        return course;
    }
}
