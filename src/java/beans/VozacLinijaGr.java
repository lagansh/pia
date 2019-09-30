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
public class VozacLinijaGr {
    private int id;
    private int id_linija;
    private int id_vozac;

    public VozacLinijaGr() {
    }

    public VozacLinijaGr(int id_linija, int id_vozac) {
        this.id_linija = id_linija;
        this.id_vozac = id_vozac;
    }

    public int getId_linija() {
        return id_linija;
    }

    public void setId_linija(int id_linija) {
        this.id_linija = id_linija;
    }

    public int getId_vozac() {
        return id_vozac;
    }

    public void setId_vozac(int id_vozac) {
        this.id_vozac = id_vozac;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
