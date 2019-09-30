/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import beans.GuestFilter;
import beans.Prevoznik;
import beans.Stanice;
import beans.Vozac;
import static controlers.RegisterControler.factory;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.StoredProcedureQuery;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Malesevic
 */
@ManagedBean(name="guestctrl")
@RequestScoped
public class GuestControler {
    
    private int page = 1;
    
    //Parametri sa stranice
    private Date datum = null;
    private String prevoznik;
    private int prevoznik_id;
    private String polaziste;
    private int polaziste_id = -1;
    private String odrediste;
    private int odrediste_id = -1;
    private String kod;
    private String kdo;
    
    //Konvertovani parametri (polazak od i polazak do) i datum
    private String datumOD;
    private String datumDO;
    private String datumm;
    
    Calendar c = Calendar.getInstance();
    private boolean imaRes = false;
    private ArrayList<GuestFilter> top10 = new ArrayList<>();
    private ArrayList<GuestFilter> results = new ArrayList<>();
    private static boolean pretrazivanje_klik = true;
    private static boolean topten_klik = false;
    
    
    public String pretClick(){
        topten_klik = false;
        pretrazivanje_klik = true;
        return null;
    }
    
    public String topClick(){
        pretrazivanje_klik = false;
        topten_klik = true;
        return null;
    }
    
    private void GetPocKrId(){
        Session session = factory.openSession();
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            String query = "FROM Stanice Where naziv='"+this.polaziste+"'";
            Query q = session.createQuery(query);
            ArrayList<Stanice> list = (ArrayList<Stanice>)q.list();
            if(list.size()>0){
                this.polaziste_id = list.get(0).getId();
            }
            else
                polaziste_id = -1;
            String query1 = "FROM Stanice Where naziv='"+this.odrediste+"'";
            Query q1 = session.createQuery(query1);
            ArrayList<Stanice> list1 = (ArrayList<Stanice>)q1.list();
            if(list1.size()>0){
                this.odrediste_id = list1.get(0).getId();
            }
            else
                odrediste_id = -1;
            tx.commit();
        } catch(HibernateException he){
            if(tx != null) tx.rollback();
            he.printStackTrace();
        }
        finally{
            session.close();
        }
    }
    
    private void GetPrevoznikId(){
        Session session = factory.openSession();
        Transaction tx = null;
        Prevoznik prevoz = null;
        
        try{
            tx = session.beginTransaction();
            
            String query = "FROM Prevoznik WHERE naziv='"+this.prevoznik+"'";
            Query q = session.createQuery(query);
            List list = q.list();
            if(list.size()>0){
                prevoz = (Prevoznik)list.get(0);
                prevoznik_id = prevoz.getId();
            }
            else
                prevoznik_id = -1;
            tx.commit();
        } catch(HibernateException he){
            if(tx != null) tx.rollback();
            he.printStackTrace();
        }
        finally{
            session.close();
        }
    }

    public GuestControler() {
       int a = 1;
    }
    
    private void setODandDo(){
        
        Calendar krOD = Calendar.getInstance();
        Calendar krDO = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        int kodH = -1, kdoH = -1;
        int kodM = -1, kdoM = -1;
        if(this.kod.length() == 5){
            String[] kodHM = kod.split(":");
            kodH = Integer.parseInt(kodHM[0]);
            kodM = Integer.parseInt(kodHM[1]);
            krOD.set(Calendar.YEAR, c.get(Calendar.YEAR));
            krOD.set(Calendar.MONTH, c.get(Calendar.MONTH));
            krOD.set(Calendar.DATE, c.get(Calendar.DATE));
            krOD.set(Calendar.HOUR_OF_DAY, kodH);
            krOD.set(Calendar.MINUTE, kodM);
            krOD.set(Calendar.SECOND, 0);
            datumOD = dateFormat.format(krOD.getTimeInMillis());
        }
        else
            datumOD = null;
        
        if(this.kdo.length() == 5){
            String[] kdoHM = kdo.split(":");
            kdoH = Integer.parseInt(kdoHM[0]);
            kdoM = Integer.parseInt(kdoHM[1]);
            krDO.set(Calendar.YEAR, c.get(Calendar.YEAR));
            krDO.set(Calendar.MONTH, c.get(Calendar.MONTH));
            krDO.set(Calendar.DATE, c.get(Calendar.DATE));
            krDO.set(Calendar.HOUR_OF_DAY, kdoH);
            krDO.set(Calendar.MINUTE, kdoM);
            krDO.set(Calendar.SECOND, 0);
            datumDO = dateFormat.format(krDO.getTimeInMillis());
        }
        else
           datumDO = null;
       
    }
    
    private void getFill(boolean topten){
        Date nowDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = dateFormat.format(nowDate);
        String nowTime = dateFormat1.format(nowDate);
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Query query = session.createSQLQuery("CALL guest_sort(?,?,?,?,?,?)");
            if(topten){
                query.setParameter(0, null);
                query.setParameter(1, null);
                query.setParameter(2, null);
                query.setParameter(3, null);
                query.setParameter(4, null);
                query.setParameter(5, null);
            }
            else{
                query.setParameter(0, datumm);
                query.setParameter(1, (prevoznik.length()<=0?null:prevoznik_id));
                query.setParameter(2, (datumOD != null?datumOD:null));
                query.setParameter(3, (datumDO != null?datumDO:null));
                query.setParameter(4, (polaziste_id>0?polaziste_id:null));
                query.setParameter(5,(odrediste_id>0?odrediste_id:null));
            }
            query.executeUpdate();
            
            
            tx.commit();
        } catch(HibernateException he){
            if(tx != null) tx.rollback();
            he.printStackTrace();
        }
        finally{
            session.close();
            tx = null;
        }
    }
    
    public String GuestFilter(){
       
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        if(this.prevoznik!=null && this.prevoznik.length()>0)
            GetPrevoznikId();
        
        if(datum != null){
            c.setTime(datum);
            datumm = dateFormat.format(c.getTime());
            setODandDo();
        }
        
        GetPocKrId();
        
        getFill(false);
        
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String q = "FROM GuestFilter";
            Query query = session.createQuery(q);
            results = (ArrayList<GuestFilter>) query.list();
            
            if(results.size()>0){
                this.imaRes = true;
                for(int i = 0; i < results.size(); i++){
                    ArrayList<String> medjust = new ArrayList<>();
                    Query query1 = session.createQuery("SELECT mg.naziv FROM MedjustaniceGuest mg WHERE mg.prod_id=?");
                    query1.setParameter(0, results.get(i).getId());
                    medjust = (ArrayList<String>)query1.list();
                    results.get(i).setMedjustanice(medjust.toString());
                }
            }
            tx.commit();
        } catch(HibernateException he){
            if(tx != null) tx.rollback();
            he.printStackTrace();
        }
        finally{
            session.close();
        }
        return null;
    }
    
    public void Top10(){
        topClick();
        getFill(true);
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String q = "FROM GuestFilter";
            Query query = session.createQuery(q);
            results = (ArrayList<GuestFilter>) query.list();
            
            if(results.size()>0){
                this.imaRes = true;
                for(int i = 0; i < results.size(); i++){
                    ArrayList<String> medjust = new ArrayList<>();
                    Query query1 = session.createQuery("SELECT mg.naziv FROM MedjustaniceGuest mg WHERE mg.prod_id=?");
                    query1.setParameter(0, results.get(i).getId());
                    medjust = (ArrayList<String>)query1.list();
                    results.get(i).setMedjustanice(medjust.toString());
                }
            }
            tx.commit();
        } catch(HibernateException he){
            if(tx != null) tx.rollback();
            he.printStackTrace();
        }
        finally{
            session.close();
        }
    }
    
    public String getPrevoznik() {
        return prevoznik;
    }

    public void setPrevoznik(String prevoznik) {
        this.prevoznik = prevoznik;
    }

    public int getPrevoznik_id() {
        return prevoznik_id;
    }

    public void setPrevoznik_id(int prevoznik_id) {
        this.prevoznik_id = prevoznik_id;
    }

    public String getPolaziste() {
        return polaziste;
    }

    public void setPolaziste(String polaziste) {
        this.polaziste = polaziste;
    }

    public int getPolaziste_id() {
        return polaziste_id;
    }

    public void setPolaziste_id(int polaziste_id) {
        this.polaziste_id = polaziste_id;
    }

    public String getOdrediste() {
        return odrediste;
    }

    public void setOdrediste(String odrediste) {
        this.odrediste = odrediste;
    }

    public int getOdrediste_id() {
        return odrediste_id;
    }

    public void setOdrediste_id(int odrediste_id) {
        this.odrediste_id = odrediste_id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKdo() {
        return kdo;
    }

    public void setKdo(String kdo) {
        this.kdo = kdo;
    }

    public boolean isImaRes() {
        return imaRes;
    }

    public void setImaRes(boolean imaRes) {
        this.imaRes = imaRes;
    }

    public ArrayList<GuestFilter> getResults() {
        return results;
    }

    public void setResults(ArrayList<GuestFilter> results) {
        this.results = results;
    }

    public boolean isPretrazivanje_klik() {
        return pretrazivanje_klik;
    }

    public void setPretrazivanje_klik(boolean pretrazivanje_klik) {
        this.pretrazivanje_klik = pretrazivanje_klik;
    }

    public boolean isTopten_klik() {
        return topten_klik;
    }

    public void setTopten_klik(boolean topten_klik) {
        this.topten_klik = topten_klik;
    }

    public ArrayList<GuestFilter> getTop10() {
        return top10;
    }

    public void setTop10(ArrayList<GuestFilter> top10) {
        this.top10 = top10;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
