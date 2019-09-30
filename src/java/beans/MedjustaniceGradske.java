/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Malesevic
 */
public class MedjustaniceGradske {
    private int id;
    private String naziv;
    private Date polazak;

    public MedjustaniceGradske(int id, String naziv) {
        this.polazak = new Date();
        this.id = id;
        this.naziv = naziv;
        
    }

    public MedjustaniceGradske() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Date getPolazak() {
        return polazak;
    }

    public void setPolazak(Date polazak) {
        this.polazak = polazak;
    }
}
