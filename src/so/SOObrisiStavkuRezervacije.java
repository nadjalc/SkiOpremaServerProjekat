/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.StavkaRezervacijeSkija;

/**
 *
 * @author Nadja
 */
public class SOObrisiStavkuRezervacije extends AbstractSo{

    private StavkaRezervacijeSkija stavka;
    
    
    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        dbb.obrisiObjekat(stavka);
    }

    public StavkaRezervacijeSkija getStavka() {
        return stavka;
    }

    public void setStavka(StavkaRezervacijeSkija stavka) {
        this.stavka = stavka;
    }
    
    
    
}
