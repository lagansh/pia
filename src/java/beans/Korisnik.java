/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;


public class Korisnik{
   
    private int id;
    private String ime;
    private String prezime;
    private String username;
    private String password;
    private String adresa;
    private Date datum;
    private String telefon;
    private String zaposlenje;
    private String email;
    private int odobren; // 0 - poslat zahtev, 1-odobren, -1 - odbijen
    
    public Korisnik(){}
    
    public Korisnik(String ime, String prezime, String username, String password, String adresa, Date datum, String telefon, String zaposlenje, String email) {
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.adresa = adresa;
        this.datum = datum;
        this.telefon = telefon;
        this.zaposlenje = zaposlenje;
        this.email = email;
        this.odobren = 0;
    }

    public int getOdobren() {
        return odobren;
    }

    public void setOdobren(int odobren) {
        this.odobren = odobren;
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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getZaposlenje() {
        return zaposlenje;
    }

    public void setZaposlenje(String zaposlenje) {
        this.zaposlenje = zaposlenje;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
