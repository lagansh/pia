/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpBeans;

import java.sql.Time;

/**
 *
 * @author Malesevic
 */
public class GrRedVoznje {
    private String stanica;
    private Time polazak;

    public GrRedVoznje(String stanica, Time polazak) {
        this.stanica = stanica;
        this.polazak = polazak;
    }

    public String getStanica() {
        return stanica;
    }

    public void setStanica(String stanica) {
        this.stanica = stanica;
    }

    public Time getPolazak() {
        return polazak;
    }

    public void setPolazak(Time polazak) {
        this.polazak = polazak;
    }
}
