/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import beans.Autobus;
import beans.Linija;
import beans.LinijaDetails;
import beans.Prevoznik;
import beans.Prod;
import beans.ProdDet;
import beans.StanicaPolDol;
import beans.Stanice;
import beans.Vozac;
import static controlers.RegisterControler.factory;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.mail.MessagingException;
import javax.mail.Part;
import org.apache.commons.io.IOUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.model.NativeUploadedFile;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Malesevic
 */
@ManagedBean(name = "admin")
@ViewScoped
public class AdminControler {

    //
    //Atributi za liniju
    //
    private int currStep = 0;
    private int rbrLinije;

    private String polaziste;
    private int polaziste_id;

    private String odrediste;
    private int odrediste_id;

    private int linijaID;
    private int prodID;

    private int autobus_id;
    private int vozac_id;

    private String brMedjustanica;
    private int br_medjustanica;

    private ArrayList<Vozac> vozaciLista = new ArrayList<>();
    private ArrayList<Autobus> autobusiLista = new ArrayList<>();
    private ArrayList<String> medjustanice = new ArrayList<>();
    private ArrayList<Integer> medjustaniceIDs = new ArrayList<>();
    private ArrayList<String> brojMedjust = new ArrayList<>();
    private ArrayList<StanicaPolDol> linijedetIDs = new ArrayList<>();
    private ArrayList<Prevoznik> prevoznici = new ArrayList<>();
    //
    //
    //
    //
    //Atributi za vozaca
    //
    private String ime;
    private String prezime;
    private Date dtmRodjenja;
    private Date dtmPocetka;

    //
    //Prevoznik
    //
    private String prevoznik;
    private int prevoznik_id;
    private String nazivPr;
    private String kontaktPr;
    private String adresaPr;

    //
    //Autobus
    //
    private String marka;
    private String model;
    private int br_sedista;
    private ArrayList<Blob> photos = new ArrayList<>();
    private NativeUploadedFile currImg = null;

    private void ResetAllFields() {

        this.nazivPr = this.adresaPr = this.kontaktPr = "";
        this.prevoznik = "";
        this.prevoznik_id = 0;

        this.rbrLinije = 0;

        this.polaziste = "";
        this.polaziste_id = 0;
        this.odrediste = "";
        this.odrediste_id = 0;

        this.linijaID = 0;
        this.prodID = 0;
        this.autobus_id = 0;
        this.vozac_id = 0;

        this.brMedjustanica = "";
        this.br_medjustanica = 0;
        this.brojMedjust.clear();
        this.vozaciLista.clear();
        this.autobusiLista.clear();
        this.medjustanice.clear();
        this.medjustaniceIDs.clear();
        this.linijedetIDs.clear();

        this.ime = "";
        this.prezime = "";
        this.dtmPocetka = null;
        this.dtmRodjenja = null;

        this.marka = "";
        this.model = "";
        this.br_sedista = 0;
    }

    private void convertMedjust() {
        if (this.brMedjustanica.length() > 0) {
            this.br_medjustanica = Integer.parseInt(this.brMedjustanica);
        } else {
            this.br_medjustanica = 0;
        }
    }

    public void storeImage() {
        if (currImg != null) {
            Session session = factory.openSession();
            Transaction tx = null;

            try {
                tx = session.beginTransaction();
                // byte[] b = currImg.getContents();
                Blob photo = null;
                InputStream is = currImg.getInputstream();
                byte[] b = IOUtils.toByteArray(is);
                photo = new javax.sql.rowset.serial.SerialBlob(b);
                photos.add(photo);
                currImg = null;
                tx.commit();
            } catch (HibernateException he) {
                if (tx != null) {
                    tx.rollback();
                }
                he.printStackTrace();
            } catch (SQLException ex) {
                if (tx != null) {
                    tx.rollback();
                }
                ex.printStackTrace();
            } catch (IOException ex) {
                Logger.getLogger(AdminControler.class.getName()).log(Level.SEVERE, null, ex);
            } //catch (IOException ex) {
            // Logger.getLogger(AdminControler.class.getName()).log(Level.SEVERE, null, ex);
            // } catch (MessagingException ex) {
            //      Logger.getLogger(AdminControler.class.getName()).log(Level.SEVERE, null, ex); }
            finally {
                session.close();
            }
        }
    }

    public String addAutobus() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Blob b0 = null;
            Blob b1 = null;
            Blob b2 = null;
            Blob b3 = null;
            Blob b4 = null;
            for (int i = 0; i < photos.size(); i++) {
                switch (i) {
                    case 0:
                        b0 = photos.get(i);
                        break;
                    case 1:
                        b1 = photos.get(i);
                        break;
                    case 2:
                        b2 = photos.get(i);
                        break;
                    case 3:
                        b3 = photos.get(i);
                        break;
                    case 4:
                        b4 = photos.get(i);
                        break;
                }
            }
            Autobus a = new Autobus(this.prevoznik_id, this.marka, this.model, this.br_sedista, b0, b1, b2, b3, b4);
            session.save(a);
            this.photos.clear();
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } finally {
            session.close();
        }
        ResetAllFields();
        return null;
    }

    public String addPrevoznik() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Prevoznik prevoznik = new Prevoznik(this.nazivPr, this.adresaPr, this.kontaktPr);
            session.save(prevoznik);
            this.prevoznici.clear();
            tx.commit();
            listPrevoznici();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } finally {
            session.close();
        }
        ResetAllFields();
        return null;
    }

    private void listVozaci() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            String q = "FROM Vozac WHERE prevoznik_id=" + this.prevoznik_id;
            Query query = session.createQuery(q);
            this.vozaciLista = (ArrayList<Vozac>) query.list();
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

    private void listAutobusi() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            String q = "FROM Autobus WHERE prevoznik_id=" + this.prevoznik_id;
            Query query = session.createQuery(q);
            this.autobusiLista = (ArrayList<Autobus>) query.list();
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

    private void addLinija() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            String query = "FROM Stanice Where naziv='" + this.polaziste + "'";
            Query q = session.createQuery(query);
            ArrayList<Stanice> list = (ArrayList<Stanice>) q.list();
            if (list.size() > 0) {
                this.polaziste_id = list.get(0).getId();
            } else {
                polaziste_id = -1;
            }
            String query1 = "FROM Stanice Where naziv='" + this.odrediste + "'";
            Query q1 = session.createQuery(query1);
            ArrayList<Stanice> list1 = (ArrayList<Stanice>) q1.list();
            if (list1.size() > 0) {
                this.odrediste_id = list1.get(0).getId();
            } else {
                odrediste_id = -1;
            }

            if (polaziste_id != -1 && odrediste_id != -1) {
                Linija l = new Linija(polaziste_id, odrediste_id);
                linijaID = (Integer) session.save(l);
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

    private void addLinijaDet() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (int i = 0; i < medjustaniceIDs.size(); i++) {
                LinijaDetails ld = new LinijaDetails(this.linijaID, medjustaniceIDs.get(i));
                linijedetIDs.add(new StanicaPolDol((Integer) session.save(ld), this.medjustanice.get(i)));
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

    private void getMedjustaniceIDs() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            for (int i = 0; i < medjustanice.size(); i++) {
                Query q = session.createQuery("SELECT s.id FROM Stanice s WHERE s.naziv='" + medjustanice.get(i) + "'");
                medjustaniceIDs.add((Integer) q.list().get(0));
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

    private void saveProd() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Prod pr = new Prod(this.linijaID, this.vozac_id, this.autobus_id);
            this.prodID = (int) session.save(pr);
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

    private void saveProdDetails() {
        saveProd();
        Session session = factory.openSession();
        Transaction tx = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            tx = session.beginTransaction();
            for (int i = 0; i < linijedetIDs.size(); i++) {
                Date dolazak = linijedetIDs.get(i).getDolazak();
                Date polazak = linijedetIDs.get(i).getDolazak();

                Timestamp d = new Timestamp(dolazak.getTime());
                Timestamp p = new Timestamp(polazak.getTime());
                ProdDet pd = new ProdDet(this.prodID, linijedetIDs.get(i).getId(), d, p);
                session.save(pd);

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

    public String Dalje() {

        switch (currStep) {
            case 0:
                addLinija();
                convertMedjust();
                if (medjustanice != null) {
                    medjustanice.clear();
                }

                if (this.br_medjustanica == 0) {
                    currStep++;
                } else {
                    brojMedjust.clear();
                    for (int i = 0, j = 1; i < this.br_medjustanica; i++, j++) {
                        brojMedjust.add(Integer.toString(j));
                        medjustanice.add("");
                    }
                }
                break;
            case 1:
                getMedjustaniceIDs();
                medjustaniceIDs.add(0, this.polaziste_id);
                medjustaniceIDs.add(this.odrediste_id);
                medjustanice.add(0, this.polaziste);
                medjustanice.add(this.odrediste);
                addLinijaDet();
                break;
            case 2:
                listAutobusi();
                listVozaci();
                break;
            case 3:
                saveProdDetails();
                ResetAllFields();
                break;
        }
        currStep = (currStep + 1) % 4;
        return null;
    }

    public void listPrevoznici() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("FROM Prevoznik");
            this.prevoznici = (ArrayList<Prevoznik>) q.list();
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

    public AdminControler() {
        listPrevoznici();
    }

    private void getPrevoznikID() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            boolean ima = false;

            String q = "FROM Prevoznik WHERE naziv='" + this.prevoznik + "'";
            Query query = session.createQuery(q);
            List list = query.list();
            if (list.size() > 0) {
                ima = true;
            } else {
                ima = false;
            }

            if (!ima) {
                prevoznik_id = 0;
            } else {
                Prevoznik p = (Prevoznik) list.get(0);
                this.prevoznik_id = p.getId();
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

    public void addVozac() {

        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Vozac v = new Vozac(prevoznik_id, ime, prezime, dtmRodjenja, dtmPocetka);
            int id = (Integer) session.save(v);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } finally {
            session.close();
            ResetAllFields();
        }
    }

    public int getRbrLinije() {
        return rbrLinije;
    }

    public void setRbrLinije(int rbrLinije) {
        this.rbrLinije = rbrLinije;
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

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Date getDtmRodjenja() {
        return dtmRodjenja;
    }

    public void setDtmRodjenja(Date dtmRodjenja) {
        this.dtmRodjenja = dtmRodjenja;
    }

    public Date getDtmPocetka() {
        return dtmPocetka;
    }

    public void setDtmPocetka(Date dtmPocetka) {
        this.dtmPocetka = dtmPocetka;
    }

    public String getPrevoznik() {
        return prevoznik;
    }

    public void setPrevoznik(String prevoznik) {
        this.prevoznik = prevoznik;
    }

    public int getCurrStep() {
        return currStep;
    }

    public void setCurrStep(int currStep) {
        this.currStep = currStep;
    }

    public String getBrMedjustanica() {
        return brMedjustanica;
    }

    public void setBrMedjustanica(String brMedjustanica) {
        this.brMedjustanica = brMedjustanica;
    }

    public ArrayList<String> getBrojMedjust() {
        return brojMedjust;
    }

    public void setBrojMedjust(ArrayList<String> brojMedjust) {
        this.brojMedjust = brojMedjust;
    }

    public ArrayList<String> getMedjustanice() {
        return medjustanice;
    }

    public void setMedjustanice(ArrayList<String> Medjustanice) {
        this.medjustanice = Medjustanice;
    }

    public ArrayList<StanicaPolDol> getLinijedetIDs() {
        return linijedetIDs;
    }

    public void setLinijedetIDs(ArrayList<StanicaPolDol> linijedetIDs) {
        this.linijedetIDs = linijedetIDs;
    }

    public ArrayList<Vozac> getVozaciLista() {
        return vozaciLista;
    }

    public void setVozaciLista(ArrayList<Vozac> vozaciLista) {
        this.vozaciLista = vozaciLista;
    }

    public ArrayList<Autobus> getAutobusiLista() {
        return autobusiLista;
    }

    public void setAutobusiLista(ArrayList<Autobus> autobusiLista) {
        this.autobusiLista = autobusiLista;
    }

    public int getAutobus_id() {
        return autobus_id;
    }

    public void setAutobus_id(int autobus_id) {
        this.autobus_id = autobus_id;
    }

    public int getVozac_id() {
        return vozac_id;
    }

    public void setVozac_id(int vozac_id) {
        this.vozac_id = vozac_id;
    }

    public String getNazivPr() {
        return nazivPr;
    }

    public void setNazivPr(String nazivPr) {
        this.nazivPr = nazivPr;
    }

    public String getKontaktPr() {
        return kontaktPr;
    }

    public void setKontaktPr(String kontaktPr) {
        this.kontaktPr = kontaktPr;
    }

    public String getAdresaPr() {
        return adresaPr;
    }

    public void setAdresaPr(String adresaPr) {
        this.adresaPr = adresaPr;
    }

    public ArrayList<Prevoznik> getPrevoznici() {
        return prevoznici;
    }

    public void setPrevoznici(ArrayList<Prevoznik> prevoznici) {
        this.prevoznici = prevoznici;
    }

    public int getPrevoznik_id() {
        return prevoznik_id;
    }

    public void setPrevoznik_id(int prevoznik_id) {
        this.prevoznik_id = prevoznik_id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getBr_sedista() {
        return br_sedista;
    }

    public void setBr_sedista(int br_sedista) {
        this.br_sedista = br_sedista;
    }

    public ArrayList<Blob> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Blob> photos) {
        this.photos = photos;
    }

    public NativeUploadedFile getCurrImg() {
        return currImg;
    }

    public void setCurrImg(NativeUploadedFile currImg) {
        this.currImg = currImg;
    }
}
