/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Malesevic
 */
public class LazyGradskaLinija extends LazyDataModel<GradskaLinija> {
    private List<GradskaLinija> datasource;

    public LazyGradskaLinija(List<GradskaLinija> datasource) {
        this.datasource = datasource;
    }
}
