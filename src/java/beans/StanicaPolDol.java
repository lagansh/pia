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
public class StanicaPolDol {
    private int id;
    private String naziv;
    private Date polazak;
    private Date dolazak;

    public int getId() {
        return id;
    }

    public StanicaPolDol(int id, String nz) {
        this.id = id;
        this.naziv = nz;
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

    public Date getDolazak() {
        return dolazak;
    }

    public void setDolazak(Date dolazak) {
        this.dolazak = dolazak;
    }

   
}
