/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObject;

/**
 *
 * @author Nadja
 */
public class SOObrisiKorisnika extends AbstractSo{
    private AbstractObject klijent;

    public SOObrisiKorisnika(AbstractObject klijent) {
        this.klijent = klijent;
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        dbb.obrisiObjekat(klijent);
    }

    public AbstractObject getKlijent() {
        return klijent;
    }
    
    
}
