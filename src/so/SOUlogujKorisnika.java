/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObject;
import domen.Korisnik;
import java.util.List;
import poslovnaLogika.Kontroler;

/**
 *
 * @author Nadja
 */
public class SOUlogujKorisnika extends AbstractSo {

    private AbstractObject parametri;
    private AbstractObject korisnikUlogovani;

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        List<AbstractObject> listaKorisnika = dbb.vratiObjekte(new Korisnik());
        Korisnik korisnikNovi = (Korisnik) parametri;
        for (AbstractObject abstractObject : listaKorisnika) {
            Korisnik korisnikIzBaze = (Korisnik) abstractObject;
            if (korisnikIzBaze.getKorisnickoIme().equals(korisnikNovi.getKorisnickoIme())) {
                korisnikUlogovani = korisnikIzBaze;
                int index = Kontroler.getInstance().getListaKorisnika().indexOf(korisnikIzBaze);
                Korisnik korisnikIzListe = (Korisnik) Kontroler.getInstance().getListaKorisnika().get(index);
                if (korisnikIzListe.isUlogovan()) {
                    throw new Exception("Korisnik je vec ulogovan!");
                } else {
                    if (korisnikIzBaze.getLozinka().equals(korisnikNovi.getLozinka())) {
                        korisnikIzListe.setUlogovan(true);
                        System.out.println("Lozinka dobra!");
                        return;

                    } else {
                        throw new Exception("Pogresna lozinka!");
                    }

                }
            }
        }
        throw new Exception("Korisnik nije pronadjen!");
    }

    public AbstractObject getParametri() {
        return parametri;
    }

    public void setParametri(AbstractObject parametri) {
        this.parametri = parametri;
    }

    public AbstractObject getKorisnikUlogovani() {
        return korisnikUlogovani;
    }

    public void setKorisnikUlogovani(AbstractObject korisnikUlogovani) {
        this.korisnikUlogovani = korisnikUlogovani;
    }

    @Override
    public synchronized void izvrsiOperaciju() throws Exception {
        dbb.otvoriKonekciju();
        izvrsiKonkretnuOperaciju();
        dbb.prekiniKonekciju();
    }

}
