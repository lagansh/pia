/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Malesevic
 */
public class ProdDet {
    private int id;
    private int prod_id;
    private int linijedet_id;
    private int rbr;
    private Timestamp dolazak;
    private Timestamp polazak;

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public ProdDet(int prod_id, int linijedet_id, Timestamp dolazak, Timestamp polazak) {
        this.prod_id = prod_id;
        this.linijedet_id = linijedet_id;
        this.rbr = 0;
        this.dolazak = dolazak;
        this.polazak = polazak;
    }

    public int getLinijadet_id() {
        return linijedet_id;
    }

    public void setLinijadet_id(int linijadet_id) {
        this.linijedet_id = linijadet_id;
    }

    public int getRbr() {
        return rbr;
    }

    public void setRbr(int rbr) {
        this.rbr = rbr;
    }

    public Date getDolazak() {
        return dolazak;
    }

    public void setDolazak(Timestamp dolazak) {
        this.dolazak = dolazak;
    }

    public Date getPolazak() {
        return polazak;
    }

    public void setPolazak(Timestamp polazak) {
        this.polazak = polazak;
    }

    public ProdDet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLinijedet_id() {
        return linijedet_id;
    }

    public void setLinijedet_id(int linijedet_id) {
        this.linijedet_id = linijedet_id;
    }
}
