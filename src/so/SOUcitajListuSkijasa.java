/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObject;
import domen.Skijas;
import java.util.List;

/**
 *
 * @author Nadja
 */
public class SOUcitajListuSkijasa extends AbstractSo {

    private List<AbstractObject> listaSkijasa;

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        listaSkijasa = dbb.vratiObjekte(new Skijas());
    }

    public List<AbstractObject> getListaSkijasa() {
        return listaSkijasa;
    }

    @Override
    public synchronized void izvrsiOperaciju() throws Exception {
        dbb.otvoriKonekciju();
        izvrsiKonkretnuOperaciju();
        dbb.prekiniKonekciju();
    }

}
