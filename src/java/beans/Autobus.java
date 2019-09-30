/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Blob;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Malesevic
 */
public class Autobus {
    private int id;

    
    private int prevoznik_id;
    private String marka;
    private String model;
    private int br_sedista;
    private int rbr;
    private Blob foto1;
    private Blob foto2;
    private Blob foto3;
    private Blob foto4;
    private Blob foto5;
    
    public Autobus(int prevoznik_id, String marka, String model, int br_sedista, Blob foto1, Blob foto2, Blob foto3, Blob foto4, Blob foto5) {
        this.prevoznik_id = prevoznik_id;
        this.marka = marka;
        this.model = model;
        this.br_sedista = br_sedista;
        this.rbr = 0;
        this.foto1 = foto1;
        this.foto2 = foto2;
        this.foto3 = foto3;
        this.foto4 = foto4;
        this.foto5 = foto5;
    }
    
    public Autobus(){}
    
    public boolean imaSlike(){
        return ((foto1 != null)||(foto2 != null)||(foto3 != null)||(foto4 != null)||(foto5 != null));
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrevoznik_id() {
        return prevoznik_id;
    }

    public void setPrevoznik_id(int prevoznik_id) {
        this.prevoznik_id = prevoznik_id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getBr_sedista() {
        return br_sedista;
    }

    public void setBr_sedista(int br_sedista) {
        this.br_sedista = br_sedista;
    }

    public int getRbr() {
        return rbr;
    }

    public void setRbr(int rbr) {
        this.rbr = rbr;
    }

    public Blob getFoto1() {
        return foto1;
    }

    public void setFoto1(Blob foto1) {
        this.foto1 = foto1;
    }

    public Blob getFoto2() {
        return foto2;
    }

    public void setFoto2(Blob foto2) {
        this.foto2 = foto2;
    }

    public Blob getFoto3() {
        return foto3;
    }

    public void setFoto3(Blob foto3) {
        this.foto3 = foto3;
    }

    public Blob getFoto4() {
        return foto4;
    }

    public void setFoto4(Blob foto4) {
        this.foto4 = foto4;
    }

    public Blob getFoto5() {
        return foto5;
    }

    public void setFoto5(Blob foto5) {
        this.foto5 = foto5;
    }
}
