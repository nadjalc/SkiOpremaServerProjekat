/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObject;
import domen.ParSkija;
import domen.TipSkija;
import java.util.List;

/**
 *
 * @author Nadja
 */
public class SOUcitajListuParovaSkija extends AbstractSo{

    private List<AbstractObject> listaParova;
    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        listaParova = dbb.vratiObjekte(new ParSkija());
        ucitajTipove();
    }

    private void ucitajTipove() throws Exception {
        for (AbstractObject abstractObject : listaParova) {
            ParSkija ps = (ParSkija) abstractObject;
            ps.setTipSkija((TipSkija) dbb.vratiObjekatPoKljucu(new TipSkija(), ps.getTipSkija().getTipSkijaID()));
        }
    }

    public List<AbstractObject> getListaParova() {
        return listaParova;
    }

    @Override
    public synchronized void izvrsiOperaciju() throws Exception {
        dbb.otvoriKonekciju();
        izvrsiKonkretnuOperaciju();
        dbb.prekiniKonekciju();
       
    }
    
    
    
}
