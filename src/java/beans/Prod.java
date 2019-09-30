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
public class Prod {
    private int id;
    private int linija_id;
    private int vozac_id;
    private int autobus_id;
    private int rbr;

    public Prod(int linija_id, int vozac_id, int autobus_id) {
        this.linija_id = linija_id;
        this.vozac_id = vozac_id;
        this.autobus_id = autobus_id;
        this.rbr = 0;
    }

    public Prod() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLinija_id() {
        return linija_id;
    }

    public void setLinija_id(int linija_id) {
        this.linija_id = linija_id;
    }

    public int getVozac_id() {
        return vozac_id;
    }

    public void setVozac_id(int vozac_id) {
        this.vozac_id = vozac_id;
    }

    public int getAutobus_id() {
        return autobus_id;
    }

    public void setAutobus_id(int autobus_id) {
        this.autobus_id = autobus_id;
    }

    public int getRbr() {
        return rbr;
    }

    public void setRbr(int rbr) {
        this.rbr = rbr;
    }
}
