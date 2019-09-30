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
public class LinijaDetails {
    private int id;
    private int linija_id;

    public LinijaDetails(int linija_id, int stanica_id) {
        this.linija_id = linija_id;
        this.stanica_id = stanica_id;
        this.rbr = 0;
        this.pocetna_krajnja = 0;
    }
    private int stanica_id;
    private int rbr;
    private int pocetna_krajnja;
    
    public LinijaDetails(){}
    
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

    public int getStanica_id() {
        return stanica_id;
    }

    public LinijaDetails(int linija_id, int stanica_id, int rbr, int pocetna_krajnja) {
        this.linija_id = linija_id;
        this.stanica_id = stanica_id;
        this.rbr = rbr;
        this.pocetna_krajnja = pocetna_krajnja;
    }

    public void setStanica_id(int stanica_id) {
        this.stanica_id = stanica_id;
    }

    public int getRbr() {
        return rbr;
    }

    public void setRbr(int rbr) {
        this.rbr = rbr;
    }

    public int getPocetna_krajnja() {
        return pocetna_krajnja;
    }

    public void setPocetna_krajnja(int pocetna_krajnja) {
        this.pocetna_krajnja = pocetna_krajnja;
    }
}
