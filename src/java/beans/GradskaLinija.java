/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import static controlers.RegisterControler.factory;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Malesevic
 */
public class GradskaLinija {
    private int id;
    private int rbr;
    private int id_pocetna;
    private int id_odredisna;
    private String pocetna;
    private String odredisna;
    
    public int getRbr() {
        return rbr;
    }

    public GradskaLinija() {
    }
    public void getStaniceStrings(){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("FROM Stanice s WHERE s.id="+this.id_pocetna);
            Query q1 = session.createQuery("FROM Stanice s WHERE s.id="+this.id_odredisna); 
            
            List list = q.list();
            List list1 = q1.list();
            
            if(list.size()>0){
                Stanice s = (Stanice)list.get(0);
                this.pocetna = s.getNaziv();
            }
            if(list1.size()>0){
                Stanice s = (Stanice)list1.get(0);
                this.odredisna = s.getNaziv();
            }
            
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } finally {
            tx.commit();
            session.close();
        }
    }
    public GradskaLinija(int rbr, int id_pocetna, int id_odredisna) {
        this.rbr = rbr;
        this.id_pocetna = id_pocetna;
        this.id_odredisna = id_odredisna;
        
    }

    public void setRbr(int rbr) {
        this.rbr = rbr;
    }

    public int getId_pocetna() {
        return id_pocetna;
    }

    public void setId_pocetna(int id_pocetna) {
        this.id_pocetna = id_pocetna;
    }

    public int getId_odredisna() {
        return id_odredisna;
    }

    public void setId_odredisna(int id_odredisna) {
        this.id_odredisna = id_odredisna;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPocetna() {
        return pocetna;
    }

    public void setPocetna(String pocetna) {
        this.pocetna = pocetna;
    }

    public String getOdredisna() {
        return odredisna;
    }

    public void setOdredisna(String odredisna) {
        this.odredisna = odredisna;
    }
}
