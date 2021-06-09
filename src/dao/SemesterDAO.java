package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import sample.HibernateUtil;
import sample.Semester;


import javax.persistence.Query;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class SemesterDAO {
    private static Session session;
    public static List<Semester> getAllSemester()
    {
        session= HibernateUtil.getSessionFactory().openSession();

        List<Semester> sem=null;
        try{
            final String hql= " from Semester ";
            Query query=session.createQuery(hql);

            sem= query.getResultList();

        }catch(Exception e)
        {
            System.out.print(e);
        }finally {
            session.close();
        }
        return  sem;
    }
    public static void add_Semester(Semester t)
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
    public  static void delete_sem(Semester t){
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
    public static Semester getSemesterById(int id)
    {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Semester> semester = null;
        try
        {
            final String hql = "from Semester where id = '" + id+ "'";
            Query query = session.createQuery(hql);
            semester=query.getResultList();
        }
        catch (HibernateException e)
        {
            System.out.println(e);
        }
        finally {
            session.close();
        }
        return semester.get(0);
    }
    public static void updateSemester(Semester sub)
    {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(sub);
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
    public static void writeFile(int semId) throws IOException {
        FileOutputStream file = new FileOutputStream("semester.bin");

        file.write(semId);
        file.close();
    }

    public static int readFileSemester() throws IOException{
        int res =-1;
        try {
            FileInputStream file = new FileInputStream("semester.bin");
            if(file.available() == 0)
                return res;
            res=file.read();
            return res;
        } catch (IOException e) {
            return -1;
        }

    }
}
