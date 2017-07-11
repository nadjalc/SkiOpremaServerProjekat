/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObject;
import domen.Korisnik;
import java.util.List;

/**
 *
 * @author Nadja
 */
public class SOUcitajListuKorisnika extends AbstractSo {

    private List<AbstractObject> lista;

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        lista = dbb.vratiObjekte(new Korisnik());
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
        dbb.prekiniKonekciju();
    }

    
}
