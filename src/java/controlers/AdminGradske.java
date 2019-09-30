/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import beans.Autobus;
import beans.GradskaLinija;
import beans.MedjustaniceGradske;
import beans.RedVoznje;
import beans.VozacGr;
import beans.VozacLinijaGr;
import static controlers.RegisterControler.factory;
import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Malesevic
 */
@ManagedBean(name = "admingradske")
@ViewScoped

public class AdminGradske {

    private int step = 0;
    
    private String naziv_stanice;
    
    private int rbr;
    private String polaziste;
    private String odrediste;
    private int brMedjustanica;
    private VozacGr voz;
    private ArrayList<MedjustaniceGradske> medjustaniceGradske = new ArrayList<>();
    private ArrayList<VozacGr> vozaci = new ArrayList<>();
    private int izabrani_vozac;

    
    private void resetAll() {
        rbr = 0;
        polaziste = "";
        odrediste = "";
        brMedjustanica = 0;
        medjustaniceGradske.clear();
        izabrani_vozac = 0;
    }

    public String dalje() {

        switch (step) {
            case 0:
                if (this.rbr != 0) {
                    step++;
                    getPolOdrIDs();
                    if (this.brMedjustanica <= 0) {
                        step++;
                    }
                }
                break;
            case 1:
                getMedjustaniceIDs();
                step++;
                break;
            case 2:
                step++;
                break;
            case 3:
                saveAll();
                resetAll();
                step = 0;
                break;
        }

        return null;
    }
    public String saveVozac() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(voz);
            vozaci.add(voz);
            voz = new VozacGr();
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

    private void saveAll() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (int i = 0; i < medjustaniceGradske.size(); i++) {
                RedVoznje rd = new RedVoznje(this.rbr, medjustaniceGradske.get(i).getId(), new Time(medjustaniceGradske.get(i).getPolazak().getTime()));
                session.save(rd);
            }
            VozacLinijaGr vlg = new VozacLinijaGr(this.rbr, this.izabrani_vozac);
            session.save(vlg);
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

    public AdminGradske() {
        voz = new VozacGr();
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM VozacGr");
            vozaci = (ArrayList<VozacGr>) query.list();
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

    private void getMedjustaniceIDs() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            for (int i = 0; i < medjustaniceGradske.size(); i++) {
                if (i != 0 && i != this.medjustaniceGradske.size() - 1) {
                    Query query = session.createQuery("SELECT s.id FROM Stanice s WHERE s.naziv='" + this.medjustaniceGradske.get(i).getNaziv() + "'");
                    int id = (int) query.list().get(0);
                    if (id != 0) {
                        medjustaniceGradske.get(i).setId(id);
                    }
                }
            }
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

    private void getPolOdrIDs() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT s.id FROM Stanice s WHERE s.naziv='" + this.polaziste + "'");
            int polaziste_id = (int) query.list().get(0);
            query = session.createQuery("SELECT s.id FROM Stanice s WHERE s.naziv='" + this.odrediste + "'");
            int odrediste_id = (int) query.list().get(0);
            GradskaLinija gl = new GradskaLinija(this.rbr, polaziste_id, odrediste_id);
            session.save(gl);
            medjustaniceGradske.add(new MedjustaniceGradske(polaziste_id, polaziste));
            for (int i = 0; i < this.brMedjustanica; i++) {
                medjustaniceGradske.add(new MedjustaniceGradske());
            }
            medjustaniceGradske.add(new MedjustaniceGradske(odrediste_id, odrediste));
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } catch (Exception e) {
            int a = 3;
        } finally {
            session.close();
        }
    }

    public int getRbr() {
        return rbr;
    }

    public void setRbr(int rbr) {
        this.rbr = rbr;
    }

    public String getPolaziste() {
        return polaziste;
    }

    public void setPolaziste(String polaziste) {
        this.polaziste = polaziste;
    }

    public String getOdrediste() {
        return odrediste;
    }

    public void setOdrediste(String odrediste) {
        this.odrediste = odrediste;
    }

    public int getBrMedjustanica() {
        return brMedjustanica;
    }

    public void setBrMedjustanica(int brMedjustanica) {
        this.brMedjustanica = brMedjustanica;
    }

    public ArrayList<MedjustaniceGradske> getMedjustaniceGradske() {
        return medjustaniceGradske;
    }

    public void setMedjustaniceGradske(ArrayList<MedjustaniceGradske> medjustGradske) {
        this.medjustaniceGradske = medjustGradske;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public ArrayList<VozacGr> getVozaci() {
        return vozaci;
    }

    public void setVozaci(ArrayList<VozacGr> vozaci) {
        this.vozaci = vozaci;
    }

    public int getIzabrani_vozac() {
        return izabrani_vozac;
    }

    public void setIzabrani_vozac(int izabarani_vozac) {
        this.izabrani_vozac = izabarani_vozac;
    }

    public VozacGr getVoz() {
        return voz;
    }

    public void setVoz(VozacGr voz) {
        this.voz = voz;
    }

    public String getNaziv_stanice() {
        return naziv_stanice;
    }

    public void setNaziv_stanice(String naziv_stanice) {
        this.naziv_stanice = naziv_stanice;
    }
}
