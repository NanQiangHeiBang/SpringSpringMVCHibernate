package com.ax;

import com.xf.entity.Dept;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/12/27.
 */
public class APPTest {
    @Test
    public void fun1(){
        ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");
       SessionFactory sessinFactory = (SessionFactory) act.getBean("sqlSessionFactory");
        Session session1 = sessinFactory.openSession();
        Session session2 = sessinFactory.openSession();
        System.out.println(session1 == session2);
    }

    @Test
    public void fun2() {
        ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");
        SessionFactory sessionFactory = (SessionFactory) act.getBean("sqlSessionFactory");

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();

            Dept dept = new Dept(2010, "ax", "xf");
            session.persist(dept);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

}
