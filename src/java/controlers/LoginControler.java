/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import beans.Korisnik;
import static controlers.RegisterControler.factory;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Malesevic
 */
@ManagedBean(name = "loginctrl")
@SessionScoped
public class LoginControler {

    private String username;
    private String password;

    public LoginControler() {
        if (factory == null) {
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
    private Korisnik currKor;

    public String Login() {

        if (this.username.equals("admin") && this.password.equals("admin")) {
            return "newxhtml";
        }

        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            String q = "FROM Korisnik WHERE username='" + this.username + "' AND password='" + this.password + "' AND odobren=1";
            Query query = session.createQuery(q);

            List list = query.list();
            if (list.size() <= 0) {
                FacesMessage msg = new FacesMessage("Ne postoji korisnik sa korisnickim imenom: " + this.username + "!");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            } else {
                currKor = (Korisnik) list.get(0);
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
        return "regkorisnik";
    }

    public String LogOut() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.invalidate();
        return "index";
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

    public Korisnik getCurrKor() {
        return currKor;
    }

    public void setCurrKor(Korisnik currKor) {
        this.currKor = currKor;
    }

}
