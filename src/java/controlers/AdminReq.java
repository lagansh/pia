/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import beans.Korisnik;
import beans.RezervacijeGr;
import beans.RezervacijeMgr;
import static controlers.RegisterControler.factory;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import net.bootsfaces.render.A;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Malesevic
 */
@ManagedBean(name="adminreq")
@ViewScoped
public class AdminReq {

    private ArrayList<Korisnik> korisnici = new ArrayList<>();
    private ArrayList<RezervacijeGr> gradskeRez = new ArrayList<>();
    private ArrayList<RezervacijeMgr> mgrRez = new ArrayList<>();
    
    public String Odobri(Korisnik k){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            k.setOdobren(1);
            session.update(k);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } finally {
            session.close();
        }
        return "AdminRequests";
    }
    public String Odbij(Korisnik k){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            k.setOdobren(-1);
            session.update(k);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } finally {
            session.close();
        }
        return "AdminRequests";
    }
    public String odbijKartuGr(RezervacijeGr rgr){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            rgr.setStatus(-1);
            session.update(rgr);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } finally {
            session.close();
        }
        return "AdminRequests";
    }
    public String odobriKartuGr(RezervacijeGr rgr){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            rgr.setStatus(1);
            session.update(rgr);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } finally {
            session.close();
        }
        return "AdminRequests";
    }
    
    public String odobriKartuMgr(RezervacijeMgr mgr){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            mgr.setStatus(1);
            session.update(mgr);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } finally {
            session.close();
        }
        return "AdminRequests";
    }
    public String odbijKartuMgr(RezervacijeMgr mgr){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            mgr.setStatus(-1);
            session.update(mgr);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } finally {
            session.close();
        }
        return "AdminRequests";
    }
    public AdminReq() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("FROM Korisnik WHERE odobren=0");
            korisnici = (ArrayList<Korisnik>) q.list();
            q = session.createQuery("FROM RezervacijeGr WHERE status=0");
            this.gradskeRez = (ArrayList<RezervacijeGr>) q.list();
            q = session.createQuery("FROM RezervacijeMgr WHERE status=0");
            this.mgrRez = (ArrayList<RezervacijeMgr>) q.list();
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } finally {
            session.close();
        }
    }

    public ArrayList<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(ArrayList<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    public ArrayList<RezervacijeGr> getGradskeRez() {
        return gradskeRez;
    }

    public void setGradskeRez(ArrayList<RezervacijeGr> gradskeRez) {
        this.gradskeRez = gradskeRez;
    }

    public ArrayList<RezervacijeMgr> getMgrRez() {
        return mgrRez;
    }

    public void setMgrRez(ArrayList<RezervacijeMgr> mgrRez) {
        this.mgrRez = mgrRez;
    }
    
}
