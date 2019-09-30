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
public class RedVoznje {
    private int id;
    private int rbr_linije;
    private int id_medjustanica;
    private Time polazak;

    public RedVoznje(int rbr_linije, int id_medjustanica, Time polazak) {
        this.rbr_linije = rbr_linije;
        this.id_medjustanica = id_medjustanica;
        this.polazak = polazak;
    }

    public int getRbr_linije() {
        return rbr_linije;
    }

    public void setRbr_linije(int rbr_linije) {
        this.rbr_linije = rbr_linije;
    }

    public int getId_medjustanica() {
        return id_medjustanica;
    }

    public void setId_medjustanica(int id_medjustanica) {
        this.id_medjustanica = id_medjustanica;
    }

    public Time getPolazak() {
        return polazak;
    }

    public void setPolazak(Time polazak) {
        this.polazak = polazak;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
