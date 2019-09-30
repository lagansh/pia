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
public class RezervacijeMgr {
    private int id;
    private String user;
    private int status;
    private String prevoznik;
    private String od_do;
    private int cena;

    public RezervacijeMgr(String user, int status, String prevoznik, String od_do, int cena) {
        this.user = user;
        this.status = status;
        this.prevoznik = prevoznik;
        this.od_do = od_do;
        this.cena = cena;
    }
    
    public RezervacijeMgr() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPrevoznik() {
        return prevoznik;
    }

    public void setPrevoznik(String prevoznik) {
        this.prevoznik = prevoznik;
    }

    public String getOd_do() {
        return od_do;
    }

    public void setOd_do(String od_do) {
        this.od_do = od_do;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }
}
