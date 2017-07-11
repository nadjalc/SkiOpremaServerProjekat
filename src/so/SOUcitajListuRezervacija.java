/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObject;
import domen.ParSkija;
import domen.RezervacijaSkija;
import domen.StavkaRezervacijeSkija;
import domen.TipSkija;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nadja
 */
public class SOUcitajListuRezervacija extends AbstractSo {
    
    private List<AbstractObject> listaRezervacija;

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        listaRezervacija = dbb.vratiObjekte(new RezervacijaSkija());
        ucitajstavke();
    }
    
    private void ucitajstavke() throws Exception {
        List<AbstractObject> listaStavki = dbb.vratiObjekte(new StavkaRezervacijeSkija());
        for (AbstractObject abstractObject : listaRezervacija) {
            RezervacijaSkija r = (RezervacijaSkija) abstractObject;
            List<StavkaRezervacijeSkija> listaStav = new LinkedList<>();
            for (AbstractObject stav : listaStavki) {
                StavkaRezervacijeSkija s = (StavkaRezervacijeSkija) stav;
                if(s.getRezervacijaSkija().getRezervacijaID().equals(r.getRezervacijaID())){
                    listaStav.add(s);
                }
            }
            r.setListaStavki(listaStav);
        }
    }
    
    public List<AbstractObject> getListaRezervacija() {
        return listaRezervacija;
    }

    @Override
    public synchronized void izvrsiOperaciju() throws Exception {
        dbb.otvoriKonekciju();
        izvrsiKonkretnuOperaciju();
        dbb.prekiniKonekciju();
        
    }
    
    
}
