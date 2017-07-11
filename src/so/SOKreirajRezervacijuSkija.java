/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObject;
import domen.RezervacijaSkija;
import domen.StavkaRezervacijeSkija;

/**
 *
 * @author Nadja
 */
public class SOKreirajRezervacijuSkija extends AbstractSo {

    private AbstractObject rezervacija; 

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        rezervacija = dbb.sacuvajIliIzmeniObjekat(rezervacija);
        sacuvajStavke();
    }

    private void sacuvajStavke() throws Exception {
        RezervacijaSkija rezS = (RezervacijaSkija) rezervacija;
        for (StavkaRezervacijeSkija stavkaRezervacijeSkija : rezS.getListaStavki()) {
            stavkaRezervacijeSkija.setRezervacijaSkija(rezS);
            dbb.sacuvajObjekat(stavkaRezervacijeSkija);
        }
    }

    public AbstractObject getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(AbstractObject rezervacija) {
        this.rezervacija = rezervacija;
    }

    
}
