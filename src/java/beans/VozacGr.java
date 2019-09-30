/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;

/**
 *
 * @author Malesevic
 */
public class VozacGr {
    
    private int id;
    private String ime;
    private String prezime;
    private Date dtmRodj;
    private Date dtmPoceo;

    public VozacGr() {
    }

    public VozacGr(String ime, String prezime, Date dtmRodj, Date dtmPoceo) {
        this.ime = ime;
        this.prezime = prezime;
        this.dtmRodj = dtmRodj;
        this.dtmPoceo = dtmPoceo;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDtmRodj() {
        return dtmRodj;
    }

    public void setDtmRodj(Date dtmRodj) {
        this.dtmRodj = dtmRodj;
    }

    public Date getDtmPoceo() {
        return dtmPoceo;
    }

    public void setDtmPoceo(Date dtmPoceo) {
        this.dtmPoceo = dtmPoceo;
    }
}
