package dao;

import org.hibernate.Session;
import sample.HibernateUtil;
import java.util.List;
import sample.User;

import javax.persistence.Query;
import java.util.List;

public class UserDAO {
    public static List<User> getAllUserTeacher()
    {
        Session session= HibernateUtil.getSessionFactory().openSession();

        List<User> teacher_users=null;

        try{
            final String hql= " from User where role=1 ";
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
}
