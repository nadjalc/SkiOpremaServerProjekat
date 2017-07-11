/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObject;
import domen.RezervacijaSkija;
import domen.Skijas;
import domen.StavkaRezervacijeSkija;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nadja
 */
public class SOPretraziRezervacije extends AbstractSo {

    private String pretraga;
    private List<AbstractObject> lista;

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        SOUcitajListuRezervacija sou = new SOUcitajListuRezervacija();
        sou.izvrsiOperaciju();
        List<AbstractObject> izBaze = sou.getListaRezervacija();

        lista = new ArrayList<>();
        pretraga = pretraga.toLowerCase();
        for (AbstractObject abstractO : izBaze) {
            RezervacijaSkija s = (RezervacijaSkija) abstractO;
            if ( s.getRezervacijaID().toLowerCase().contains(pretraga)
                    || (s.getDatumRezervacije().toString().contains(pretraga)
                    || sadrziParSkija(s, pretraga))) {
                lista.add(s);
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

    private boolean sadrziParSkija(RezervacijaSkija s, String pretraga1) {
        for (StavkaRezervacijeSkija stavkaRezervacijeSkija : s.getListaStavki()) {
            if(stavkaRezervacijeSkija.getParSkija().getParSkijaID().toLowerCase().contains(pretraga)){
                return true;
            }
        }
        return false;
    }

    @Override
    public synchronized void izvrsiOperaciju() throws Exception {
        dbb.otvoriKonekciju();
        izvrsiKonkretnuOperaciju();
        dbb.prekiniKonekciju();
    }



}
