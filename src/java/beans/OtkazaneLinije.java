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
public class OtkazaneLinije {
    private int id;
    private int rbrLinije;
    private Date otkazod;
    private Date otkazdo;

    public OtkazaneLinije(int rbrLinije, Date otkazod, Date otkazdo) {
        this.rbrLinije = rbrLinije;
        this.otkazod = otkazod;
        this.otkazdo = otkazdo;
    }

    public OtkazaneLinije() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRbrLinije() {
        return rbrLinije;
    }

    public void setRbrLinije(int rbrLinije) {
        this.rbrLinije = rbrLinije;
    }

    public Date getOtkazod() {
        return otkazod;
    }

    public void setOtkazod(Date otkazod) {
        this.otkazod = otkazod;
    }

    public Date getOtkazdo() {
        return otkazdo;
    }

    public void setOtkazdo(Date otkazdo) {
        this.otkazdo = otkazdo;
    }
}
