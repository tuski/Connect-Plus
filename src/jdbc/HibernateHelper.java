package jdbc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import jdbc.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author tuski-Revolve
 */
public class HibernateHelper {

    Session session=null;
    public HibernateHelper() {
        this.session=HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    
    
}
