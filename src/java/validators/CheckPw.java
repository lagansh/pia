/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Malesevic
 */
@FacesValidator("validators.CheckPw")
public class CheckPw implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        if(value.toString().length() <= 0){
            FacesMessage msg = new FacesMessage("Unesite username!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        else if(value.toString().length() < 6 || value.toString().length() > 12){
            FacesMessage msg = new FacesMessage("Lozinka mora da ima najmanje 6 a najvise 12 karaktera!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        
        char c = value.toString().charAt(0);
        String pw = value.toString();
        
        if (!Character.isUpperCase(c) && !Character.isLowerCase(c)){
            FacesMessage msg = new FacesMessage("Lozinka mora da pocne malim ili velikim slovom!!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg); 
        }
        
        int brMala = 0;
        int brVelika = 0;
        int brZnakova = 0;
        int brCifara = 0;
        int brUzastopnih = 0;
        for(int i = 0; i<pw.length(); i++){
            c = pw.charAt(i);
            if(Character.isUpperCase(c))
                brVelika++;
            else if(Character.isLowerCase(c))
                brMala++;
            else if(Character.isDigit(c))
                brCifara++;
            else
                brZnakova++;
        }
        if(brMala < 3){
            FacesMessage msg = new FacesMessage("Lozinka mora da sadrzi najmanje 3 mala slova!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        if(brVelika == 0){
            FacesMessage msg = new FacesMessage("Lozinka mora da sadrzi najmanje 1 veliko slovo!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        if(brZnakova == 0){
            FacesMessage msg = new FacesMessage("Lozinka mora da sadrzi najmanje 1 specijalni znak!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        if(brCifara == 0){
            FacesMessage msg = new FacesMessage("Lozinka mora da sadrzi najmanje 1 cifru!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
    
   
    
}
