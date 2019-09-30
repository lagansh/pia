/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpBeans;

import java.util.ArrayList;

/**
 *
 * @author Malesevic
 */
public class KorisnikGradske {
    private int rbr;
    private ArrayList<GrRedVoznje> redvz = new ArrayList<>();
    private String pocetna = null;
    private String odredisna = null;
   
    
    
    public void Odredisnaa(){
        int i = redvz.size();
        if(i>0)
            this.odredisna = redvz.get(i-1).getStanica();
    }
    public void Pocetnaa(){
        int i = redvz.size();
        if(i>0)
            this.pocetna = redvz.get(0).getStanica();
    }
    
    public int getRbr() {
        return rbr;
    }

    public void setRbr(int rbr) {
        this.rbr = rbr;
    }

    public ArrayList<GrRedVoznje> getRedvz() {
        return redvz;
    }

    public void setRedvz(ArrayList<GrRedVoznje> redvz) {
        this.redvz = redvz;
    }

    public String getPocetna() {
        return pocetna;
    }

    public void setPocetna(String pocetna) {
        this.pocetna = pocetna;
    }

    public String getOdredisna() {
        return odredisna;
    }

    public void setOdredisna(String odredisna) {
        this.odredisna = odredisna;
    }

    
}
