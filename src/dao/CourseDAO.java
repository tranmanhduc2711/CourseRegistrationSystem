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
                        ((Session) res.get(i)[3]).toString(),
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
}
