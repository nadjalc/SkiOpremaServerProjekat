/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObject;
import domen.ParSkija;
import java.util.List;

/**
 *
 * @author Nadja
 */
public class SOObrisiParSkija extends AbstractSo {

    private ParSkija parSkija;
    private List<AbstractObject> lista;

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        dbb.obrisiObjekat(parSkija);
        dbb.potvrdiTransakciju();
        lista = dbb.vratiObjekte(new ParSkija());
        dbb.prekiniKonekciju();
        
    }

    public SOObrisiParSkija() {
    }

    public ParSkija getParSkija() {
        return parSkija;
    }

    public void setParSkija(ParSkija parSkija) {
        this.parSkija = parSkija;
    }

    public List<AbstractObject> getLista() {
        return lista;
    }

    public void setLista(List<AbstractObject> lista) {
        this.lista = lista;
    }

    @Override
    public synchronized void izvrsiOperaciju() throws Exception {
        dbb.otvoriKonekciju();
        izvrsiKonkretnuOperaciju();
    }

    
    
}
