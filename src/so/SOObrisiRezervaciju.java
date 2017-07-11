/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObject;
import domen.RezervacijaSkija;
import java.util.List;

/**
 *
 * @author Nadja
 */
public class SOObrisiRezervaciju extends AbstractSo{

    private RezervacijaSkija rez;
    private List<AbstractObject> lista;

    public SOObrisiRezervaciju(RezervacijaSkija rez) {
        this.rez = rez;
    }
    
    
    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        dbb.obrisiObjekat(rez);
        lista = dbb.vratiObjekte(new RezervacijaSkija());
    }

    public RezervacijaSkija getRez() {
        return rez;
    }

    public void setRez(RezervacijaSkija rez) {
        this.rez = rez;
    }

    public List<AbstractObject> getLista() {
        return lista;
    }

    public void setLista(List<AbstractObject> lista) {
        this.lista = lista;
    }
    
}
