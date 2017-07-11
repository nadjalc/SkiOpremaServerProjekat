/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObject;
import domen.TipSkija;
import java.util.List;

/**
 *
 * @author Nadja
 */
public class SOUcitajListuTipovaSkija extends AbstractSo{

    private List<AbstractObject> listaTipovaSkija;

    public SOUcitajListuTipovaSkija() {
    }

    public List<AbstractObject> getListaTipovaSkija() {
        return listaTipovaSkija;
    }

    public void setListaTipovaSkija(List<AbstractObject> listaTipovaSkija) {
        this.listaTipovaSkija = listaTipovaSkija;
    }
    
    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        listaTipovaSkija = dbb.vratiObjekte(new TipSkija());
    }

    @Override
    public synchronized void izvrsiOperaciju() throws Exception {
        dbb.otvoriKonekciju();
        izvrsiKonkretnuOperaciju();
        dbb.prekiniKonekciju();
        
    }
    
    
    
}
