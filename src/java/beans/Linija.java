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
public class Linija {
    private int id;
    private int stanica0_id;
    private int stanica1_id;
    private int rbr;
    private String opis;
    private int status;

    public Linija(){}

    public Linija(int stanica0_id, int stanica1_id) {
        this.stanica0_id = stanica0_id;
        this.stanica1_id = stanica1_id;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRbr() {
        return rbr;
    }

    public void setRbr(int rbr) {
        this.rbr = rbr;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStanica0_id() {
        return stanica0_id;
    }

    public void setStanica0_id(int stanica0_id) {
        this.stanica0_id = stanica0_id;
    }

    public int getStanica1_id() {
        return stanica1_id;
    }

    public void setStanica1_id(int stanica1_id) {
        this.stanica1_id = stanica1_id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
