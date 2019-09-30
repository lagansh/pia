/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import beans.Autobus;
import beans.GradskaLinija;
import beans.GuestFilter;
import beans.Korisnik;
import beans.LazyGradskaLinija;
import beans.RezervacijeGr;
import beans.RezervacijeMgr;
import beans.Vozac;
import static controlers.RegisterControler.factory;
import helpBeans.GrRedVoznje;
import helpBeans.KorisnikGradske;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.io.IOUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Malesevic
 */
@ManagedBean(name = "regkor")
@ViewScoped
public class RegKorisnik {

    private int navTab; //0 - Gradske linije, 1 - Medjugradske, 2 - Rezervacija karata
    private ArrayList<GradskaLinija> grLinije = new ArrayList<>();
    private GradskaLinija izabranaLinija;
    private ArrayList<KorisnikGradske> gradske_ukupno = new ArrayList<>();
    private ArrayList<KorisnikGradske> filtered = new ArrayList<>();
    private KorisnikGradske izabranaLinijaRV;

    private ArrayList<GuestFilter> results = new ArrayList<>();
    private ArrayList<GuestFilter> resultsF = new ArrayList<>();
    private GuestFilter izabranaMedjugradska;

    private int trajanje_rezervacije = 0;
    private int cena = 0;
    private ArrayList<RezervacijeGr> zahtevi = new ArrayList<>();
    private ArrayList<RezervacijeMgr> zahteviMgr = new ArrayList<>();

    @ManagedProperty(value = "#{loginctrl}")
    private LoginControler loginControl;

    public void navKlik(int i) {
        if (i == 2) {
            this.allReservations();
            this.allReservationsMgr();
        }
        navTab = i;
    }

    public void cenaKarte() {

        Korisnik kor = loginControl.getCurrKor();
        if (this.trajanje_rezervacije == 1) {
            if (kor.getZaposlenje().equals("Zaposlen")) {
                this.cena = 3200;
            }
            if (kor.getZaposlenje().equals("Nezaposlen")) {
                this.cena = 2000;
            }
            if (kor.getZaposlenje().equals("Invalid")) {
                this.cena = 500;
            }
            if (kor.getZaposlenje().equals("Student")) {
                this.cena = 1200;
            }
            if (kor.getZaposlenje().equals("Penzioner")) {
                this.cena = 1200;
            }
        } else if (this.trajanje_rezervacije == 12) {
            if (kor.getZaposlenje().equals("Zaposlen")) {
                this.cena = 12 * 3200;
            }
            if (kor.getZaposlenje().equals("Nezaposlen")) {
                this.cena = 12 * 2000;
            }
            if (kor.getZaposlenje().equals("Invalid")) {
                this.cena = 12 * 500;
            }
            if (kor.getZaposlenje().equals("Student")) {
                this.cena = 12 * 1200;
            }
            if (kor.getZaposlenje().equals("Penzioner")) {
                this.cena = 12 * 1200;
            }
        }
    }
    public void allReservationsMgr(){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("FROM RezervacijeMgr WHERE user='"+loginControl.getUsername()+"'");
            this.zahteviMgr = (ArrayList<RezervacijeMgr>) q.list();
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } finally {
            session.close();
            tx = null;
        }
    }
    public void addReservationMgr(GuestFilter g) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String user = loginControl.getUsername();
            int status = 0;
            String prevoznik = g.getPrevoznik();
            String od_do = g.getPocetna()+"-"+g.getOdredisna();
            int cena = 0;
            RezervacijeMgr mgr = new RezervacijeMgr(user,status,prevoznik,od_do,cena);
            g.VozacAndAutobus();
            g.getBus().setBr_sedista(g.getBus().getBr_sedista()-1);
            session.update(g.getBus());
            session.save(mgr);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } finally {
            session.close();
            tx = null;
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Uspesno", "ste poslali zahtev!"));
        }
    }

    public void allReservations() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("FROM RezervacijeGr WHERE user='" + loginControl.getUsername() + "'");
            if (zahtevi.size() > 0) {
                zahtevi.clear();
            }
            zahtevi = (ArrayList<RezervacijeGr>) q.list();
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } finally {
            session.close();
            tx = null;
        }
    }

    public void addRezervacijuGr() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            RezervacijeGr rgr = new RezervacijeGr(loginControl.getUsername(), 0, this.trajanje_rezervacije, this.cena);
            session.save(rgr);
            this.zahtevi.add(rgr);
            this.trajanje_rezervacije = 0;
            this.cena = 0;
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } finally {
            session.close();
            tx = null;
        }
    }

    public void load() {
        navTab = 0;
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("FROM GradskaLinija");
            grLinije = (ArrayList<GradskaLinija>) q.list();
            for (int i = 0; i < grLinije.size(); i++) {
                GradskaLinija gl = grLinije.get(i);
                gl.getStaniceStrings();
                Query q1 = session.createQuery("SELECT s.naziv, rv.polazak FROM RedVoznje rv,Stanice s WHERE rbr_linije=" + grLinije.get(i).getRbr() + " AND rv.id_medjustanica=s.id");
                List list = q1.list();
                KorisnikGradske kg = new KorisnikGradske();
                kg.setRbr(grLinije.get(i).getRbr());
                for (int j = 0; j < list.size(); j++) {
                    Object[] row = (Object[]) list.get(j);
                    GrRedVoznje grv = new GrRedVoznje((String) row[0], (Time) row[1]);
                    kg.getRedvz().add(grv);
                }
                kg.Pocetnaa();
                kg.Odredisnaa();
                gradske_ukupno.add(kg);
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

    private void getFill(boolean topten) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createSQLQuery("CALL guest_sort(?,?,?,?,?,?)");
            if (topten) {
                query.setParameter(0, null);
                query.setParameter(1, null);
                query.setParameter(2, null);
                query.setParameter(3, null);
                query.setParameter(4, null);
                query.setParameter(5, null);
            }
            query.executeUpdate();
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } finally {
            session.close();
            tx = null;
        }
    }
    private ArrayList<StreamedContent> slike = new ArrayList<>();
    private StreamedContent id;

    public void loadDetails() {
        this.slike.clear();
        this.izabranaMedjugradska.VozacAndAutobus();
        if (this.izabranaMedjugradska.getBus().imaSlike()) {
            for (int i = 0; i < 5; i++) {
                try {
                    InputStream st;
                    switch (i) {
                        case 0:
                            if (this.izabranaMedjugradska.getBus().getFoto1() != null) {
                                st = this.izabranaMedjugradska.getBus().getFoto1().getBinaryStream();
                                slike.add(new DefaultStreamedContent(st, "image/png"));
                            }
                            break;
                        case 1:
                            if (this.izabranaMedjugradska.getBus().getFoto2() != null) {
                                st = this.izabranaMedjugradska.getBus().getFoto2().getBinaryStream();
                                slike.add(new DefaultStreamedContent(st, "image/png"));
                            }
                            break;
                        case 2:
                            if (this.izabranaMedjugradska.getBus().getFoto3() != null) {
                                st = this.izabranaMedjugradska.getBus().getFoto1().getBinaryStream();
                                slike.add(new DefaultStreamedContent(st, "image/png"));
                            }
                            break;
                        case 3:
                            if (this.izabranaMedjugradska.getBus().getFoto4() != null) {
                                st = this.izabranaMedjugradska.getBus().getFoto1().getBinaryStream();
                                slike.add(new DefaultStreamedContent(st, "image/png"));
                            }
                            break;
                        case 4:
                            if (this.izabranaMedjugradska.getBus().getFoto5() != null) {
                                st = this.izabranaMedjugradska.getBus().getFoto1().getBinaryStream();
                                slike.add(new DefaultStreamedContent(st, "image/png"));
                            }
                            break;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(RegKorisnik.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void loadMedjugradske() {
        getFill(true);

        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String q = "FROM GuestFilter";
            Query query = session.createQuery(q);
            results = (ArrayList<GuestFilter>) query.list();
            if (results.size() > 0) {
                for (int i = 0; i < results.size(); i++) {
                    ArrayList<String> medjust = new ArrayList<>();
                    Query query1 = session.createQuery("SELECT mg.naziv FROM MedjustaniceGuest mg WHERE mg.prod_id=?");
                    query1.setParameter(0, results.get(i).getId());
                    medjust = (ArrayList<String>) query1.list();
                    results.get(i).setMedjustanice(medjust.toString());
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

    public RegKorisnik() {
        load();
        loadMedjugradske();
    }

    public int getNavTab() {
        return navTab;
    }

    public void setNavTab(int navTab) {
        this.navTab = navTab;
    }

    public ArrayList<GradskaLinija> getGrLinije() {
        return grLinije;
    }

    public void setGrLinije(ArrayList<GradskaLinija> grLinije) {
        this.grLinije = grLinije;
    }

    public GradskaLinija getIzabranaLinija() {
        return izabranaLinija;
    }

    public void setIzabranaLinija(GradskaLinija izabranaLinija) {
        this.izabranaLinija = izabranaLinija;
    }

    public ArrayList<KorisnikGradske> getGradske_ukupno() {
        return gradske_ukupno;
    }

    public void setGradske_ukupno(ArrayList<KorisnikGradske> gradske_ukupno) {
        this.gradske_ukupno = gradske_ukupno;
    }

    public KorisnikGradske getIzabranaLinijaRV() {
        return izabranaLinijaRV;
    }

    public void setIzabranaLinijaRV(KorisnikGradske izabranaLinijaRV) {
        this.izabranaLinijaRV = izabranaLinijaRV;
    }

    public ArrayList<KorisnikGradske> getFiltered() {
        return filtered;
    }

    public void setFiltered(ArrayList<KorisnikGradske> filtered) {
        this.filtered = filtered;
    }

    public ArrayList<GuestFilter> getResults() {
        return results;
    }

    public void setResults(ArrayList<GuestFilter> results) {
        this.results = results;
    }

    public GuestFilter getIzabranaMedjugradska() {
        return izabranaMedjugradska;
    }

    public void setIzabranaMedjugradska(GuestFilter izabranaMedjugradska) {
        this.izabranaMedjugradska = izabranaMedjugradska;
    }

    public ArrayList<StreamedContent> getSlike() {
        return slike;
    }

    public void setSlike(ArrayList<StreamedContent> slike) {
        this.slike = slike;
    }

    public ArrayList<GuestFilter> getResultsF() {
        return resultsF;
    }

    public void setResultsF(ArrayList<GuestFilter> resultsF) {
        this.resultsF = resultsF;
    }

    public StreamedContent getId() {
        return id;
    }

    public void setId(StreamedContent id) {
        this.id = id;
    }

    public int getTrajanje_rezervacije() {
        return trajanje_rezervacije;
    }

    public void setTrajanje_rezervacije(int trajanje_rezervacije) {
        this.trajanje_rezervacije = trajanje_rezervacije;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public void setLoginControl(LoginControler loginControl) {
        this.loginControl = loginControl;
    }

    public ArrayList<RezervacijeGr> getZahtevi() {
        return zahtevi;
    }

    public void setZahtevi(ArrayList<RezervacijeGr> zahtevi) {
        this.zahtevi = zahtevi;
    }

    public ArrayList<RezervacijeMgr> getZahteviMgr() {
        return zahteviMgr;
    }

    public void setZahteviMgr(ArrayList<RezervacijeMgr> zahteviMgr) {
        this.zahteviMgr = zahteviMgr;
    }
}
