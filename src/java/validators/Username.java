/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static controlers.RegisterControler.factory;
/**
 *
 * @author Malesevic
 */
@FacesValidator("validators.Username")
public class Username implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(value.toString().length() <= 0){
            FacesMessage msg = new FacesMessage("Unesite username!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        Session session = factory.openSession();
        Transaction tx = null;
        int ima = 0;
        try{
            tx = session.beginTransaction();
            String q = "FROM Korisnik WHERE username='"+value.toString()+"'";
            Query query = session.createQuery(q);
            List list = null;
            list = query.list();
            ima = list.size();
            tx.commit();
        } catch(HibernateException he){
            if(tx != null) tx.rollback();
            he.printStackTrace();
        }
        finally{
            session.close();
        }
        if(ima != 0){
            FacesMessage msg = new FacesMessage("Username vec postoji!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
    
}
