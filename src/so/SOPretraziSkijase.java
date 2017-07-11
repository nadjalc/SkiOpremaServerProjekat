/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObject;
import domen.Skijas;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nadja
 */
public class SOPretraziSkijase extends AbstractSo {

    private String pretraga;
    private List<AbstractObject> lista;

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        List<AbstractObject> izBaze = dbb.vratiObjekte(new Skijas());
        lista = new ArrayList<>();
        pretraga = pretraga.toLowerCase();
        for (AbstractObject abstractO : izBaze) {
            Skijas s = (Skijas) abstractO;
            if (s.getIme().toLowerCase().contains(pretraga)
                    || s.getPrezime().toLowerCase().contains(pretraga)
                    || s.getBrojTelefona().contains(pretraga)) {
                lista.add(abstractO);
            }
            
        }
    }

    public String getPretraga() {
        return pretraga;
    }

    public void setPretraga(String pretraga) {
        this.pretraga = pretraga;
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
