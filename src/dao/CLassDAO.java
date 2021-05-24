package dao;


import org.hibernate.Session;
import sample.Clazz;
import sample.HibernateUtil;
import java.util.List;


import javax.persistence.Query;
import java.util.List;
public class CLassDAO {
    public static List<Clazz> getAllClass()
    {
        Session session= HibernateUtil.getSessionFactory().openSession();

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

}
