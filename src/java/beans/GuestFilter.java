/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import static controlers.RegisterControler.factory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Malesevic
 */
public class GuestFilter {

    private int id;
    private String prevoznik;
    private String pocetna;
    private String odredisna;
    private String polazak;
    private String dolazak;
    private int vozac_id;
    private int autobus_id;
    private String vozac;
    private String autobus;
    private String medjustanice;

    private Vozac voz;
    private Autobus bus;

    
    public void VozacAndAutobus() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
                Query q = session.createQuery("FROM Vozac v WHERE v.id=" + this.autobus_id);
                voz = (Vozac) q.list().get(0);
                Query q1 = session.createQuery("FROM Autobus a WHERE a.id=" + this.vozac_id);
                bus = (Autobus) q1.list().get(0);
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
    
    public String getMedjustanice() {
        return medjustanice;
    }

    public void setMedjustanice(String medjustanice) {
        this.medjustanice = medjustanice;
    }

    public int getId() {
        return id;
    }

    public GuestFilter() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrevoznik() {
        return prevoznik;
    }

    public void setPrevoznik(String prevoznik) {
        this.prevoznik = prevoznik;
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

    public String getPolazak() {
        return polazak;
    }

    public void setPolazak(String polazak) {
        this.polazak = polazak;
    }

    public String getDolazak() {
        return dolazak;
    }

    public void setDolazak(String dolazak) {
        this.dolazak = dolazak;
    }

    public int getVozac_id() {
        return vozac_id;
    }

    public void setVozac_id(int vozac_id) {
        this.vozac_id = vozac_id;
    }

    public int getAutobus_id() {
        return autobus_id;
    }

    public void setAutobus_id(int autobus_id) {
        this.autobus_id = autobus_id;
    }

    public String getVozac() {
        return vozac;
    }

    public void setVozac(String vozac) {
        this.vozac = vozac;
    }

    public String getAutobus() {
        return autobus;
    }

    public void setAutobus(String autobus) {
        this.autobus = autobus;
    }

    public Vozac getVoz() {
        return voz;
    }

    public void setVoz(Vozac voz) {
        this.voz = voz;
    }

    public Autobus getBus() {
        return bus;
    }

    public void setBus(Autobus bus) {
        this.bus = bus;
    }
}
