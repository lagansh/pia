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
public class Vozac {
    private int id;
    private int prevoznik_id;
    private int rbr;
    private String ime;
    private String prezime;
    private Date datum_rodj;
    private Date started_working;

    public Vozac(){}

    public Vozac(int prevoznik_id, String ime, String prezime, Date datum_rodj, Date started_working) {
        this.prevoznik_id = prevoznik_id;
        this.rbr = 0;
        this.ime = ime;
        this.prezime = prezime;
        this.datum_rodj = datum_rodj;
        this.started_working = started_working;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrevoznik_id() {
        return prevoznik_id;
    }

    public void setPrevoznik_id(int prevoznik_id) {
        this.prevoznik_id = prevoznik_id;
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

    public Date getDatum_rodj() {
        return datum_rodj;
    }

    public void setDatum_rodj(Date datum_rodj) {
        this.datum_rodj = datum_rodj;
    }

    public Date getStarted_working() {
        return started_working;
    }

    public void setStarted_working(Date started_working) {
        this.started_working = started_working;
    }

    public int getRbr() {
        return rbr;
    }

    public void setRbr(int rbr) {
        this.rbr = rbr;
    }
    
}
