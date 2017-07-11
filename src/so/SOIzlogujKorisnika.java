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
public class SOIzlogujKorisnika extends AbstractSo{

    Korisnik korisnik;
    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        SOUcitajListuKorisnika so = new SOUcitajListuKorisnika();
        List<AbstractObject> listaKorisnika = so.getLista();
        for (AbstractObject abstractObject : listaKorisnika) {
            if(korisnik.equals(abstractObject)){
                korisnik.setUlogovan(false);
            }
        }
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
    
    
    
}
