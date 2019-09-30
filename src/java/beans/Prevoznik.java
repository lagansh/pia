/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Malesevic
 */
public class Prevoznik {
    private int id;
    private String naziv;
    private String adresa;
    private String kontakt;

    public Prevoznik(){}

    public Prevoznik(String naziv, String adresa, String kontakt) {
        this.naziv = naziv;
        this.adresa = adresa;
        this.kontakt = kontakt;
    }
    

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
