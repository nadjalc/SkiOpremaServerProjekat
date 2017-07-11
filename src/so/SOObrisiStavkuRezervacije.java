/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObject;
import domen.StavkaRezervacijeSkija;

/**
 *
 * @author Nadja
 */
public class SOObrisiStavkuRezervacije extends AbstractSo {

    private AbstractObject obrisani;
    private StavkaRezervacijeSkija stavka;
    int status;

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        obrisani = dbb.obrisiObjekat(stavka);
        status = 1;
    }

    public StavkaRezervacijeSkija getStavka() {
        return stavka;
    }

    public void setStavka(StavkaRezervacijeSkija stavka) {
        this.stavka = stavka;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
