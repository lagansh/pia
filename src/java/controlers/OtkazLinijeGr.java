/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import beans.OtkazaneLinije;
import static controlers.RegisterControler.factory;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Malesevic
 */
@ManagedBean(name="otkaz")
@RequestScoped
public class OtkazLinijeGr {
    private OtkazaneLinije ol;

    
    public String OtkaziLiniju(){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(ol);
            ol = new OtkazaneLinije();
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
    
    public OtkazLinijeGr() {
        ol = new OtkazaneLinije();
    }
    
    public OtkazaneLinije getOl() {
        return ol;
    }

    public void setOl(OtkazaneLinije ol) {
        this.ol = ol;
    }
    
}
