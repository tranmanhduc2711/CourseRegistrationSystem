package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import sample.CourseRegisterInfo;
import sample.Courseregistrationsession;
import sample.HibernateUtil;
import sample.Semester;

import javax.persistence.Query;
import java.util.ArrayList;
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

    public static List<CourseRegisterInfo> getAllRegister() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<CourseRegisterInfo> info = new ArrayList<CourseRegisterInfo>();
        try {
            final String hql = "from Courseregistrationsession c, Semester  s where c.semester_id = s.id";
            Query query = session.createQuery(hql);
            List<Object[]> res = query.getResultList();
            for (int i = 0; i < res.size(); i++) {
                info.add(new CourseRegisterInfo(((Courseregistrationsession) res.get(i)[0]).getId(),
                        ((Semester) res.get(i)[1]).getName(),
                        ((Semester) res.get(i)[1]).getYear(),
                        ((Courseregistrationsession) res.get(i)[0]).getStart(),
                        ((Courseregistrationsession) res.get(i)[0]).getEnd()));
            }
        } catch (HibernateException e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return info;
    }

    public static void deleteCourseRegister(Courseregistrationsession courseRegister)
    {
        session = HibernateUtil.getSessionFactory().openSession();
        try
        {
            session.beginTransaction();
            session.delete(courseRegister);
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

    public static void addCourseRegister(Courseregistrationsession courseRegister)
    {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(courseRegister);
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
