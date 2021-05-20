package dao;

import org.hibernate.Session;
import sample.HibernateUtil;
import java.util.List;
import sample.User;

import javax.persistence.Query;
import java.util.List;

public class UserDAO {
    public static List<User> getAllUser()
    {
        Session session= HibernateUtil.getSessionFactory().openSession();

        List<User> users=null;

        try{
            final String hql= "select id from User id";
            Query query=session.createQuery(hql);

            users= query.getResultList();

        }catch(Exception e)
        {
            System.out.print(e);
        }finally {
            session.close();
        }
        return users;
    }
}
