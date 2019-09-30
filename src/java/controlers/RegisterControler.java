/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import beans.Korisnik;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
/**
 *
 * @author Malesevic
 */
@ManagedBean(name="regctrl")
@SessionScoped
public class RegisterControler {

    
    private String ime;
    private String prezime;
    private String username;
    private String password;
    private String adresa;
    private Date datum;
    private String telefon;
    private String zaposlenje;
    private String email;
    private String confirmpw;
    
    public static SessionFactory factory = null;
    
    public RegisterControler(){
         if(factory == null){
            try {
                Configuration configuration = new Configuration();
                configuration.configure();
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                factory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Throwable ex) { 
                ex.printStackTrace();
            }
        }
    }
    
    public String RegisterUser(){
        
        
        Session session = factory.openSession();
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            Korisnik k = new Korisnik(ime,prezime,username,password,adresa,datum,telefon,zaposlenje,email);
            int id = (Integer)session.save(k);
            tx.commit();
        } catch(HibernateException he){
            if(tx != null) tx.rollback();
            he.printStackTrace();
        }
        finally{
            session.close();
        }
        
        return "index";
    }
    
    public String goGuest(){
        return "guest";
    }
    
    public String go(){
        return "register";
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getZaposlenje() {
        return zaposlenje;
    }

    public void setZaposlenje(String zaposlenje) {
        this.zaposlenje = zaposlenje;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmpw() {
        return confirmpw;
    }

    public void setConfirmpw(String confirmpw) {
        this.confirmpw = confirmpw;
    }
    
    public void validatePassword(FacesContext context, UIComponent component, Object value){
        
    }
    
}
