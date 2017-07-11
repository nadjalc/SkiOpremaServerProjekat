/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poslovnaLogika;

import domen.AbstractObject;
import domen.Korisnik;
import domen.ParSkija;
import domen.RezervacijaSkija;
import java.util.List;
import so.SOIzlogujKorisnika;
import so.SOKreirajRezervacijuSkija;
import so.SOObrisiKorisnika;
import so.SOObrisiParSkija;
import so.SOObrisiRezervaciju;
import so.SOPretraziRezervacije;
import so.SOPretraziSkijase;
import so.SOSacuvajKorisnika;
import so.SOUcitajListuKorisnika;
import so.SOUcitajListuParovaSkija;
import so.SOUcitajListuRezervacija;
import so.SOUcitajListuSkijasa;
import so.SOUcitajListuTipovaSkija;
import so.SOUlogujKorisnika;
import so.SOZapamtiParSkija;
import so.SOZapamtiRezervacijuSkija;

/**
 *
 * @author Nadja
 */
public class Kontroler {

    private static Kontroler instance;
    private List<AbstractObject> listaKorisnika;

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

   

    public AbstractObject zapamtiParSkija(ParSkija parS) throws Exception {
        SOZapamtiParSkija sozp = new SOZapamtiParSkija(parS);
        sozp.izvrsiOperaciju();
        return sozp.getParSkija();
    }

    public AbstractObject ulogujKorisnika(Korisnik korisnik) throws Exception {
        SOUlogujKorisnika souk = new SOUlogujKorisnika();
        souk.setParametri(korisnik);
        souk.izvrsiOperaciju();
        return souk.getKorisnikUlogovani();
    }

    public List<AbstractObject> getListaKorisnika() throws Exception {
        if (listaKorisnika == null) {
            listaKorisnika = ucitajListuKorisnika();
        }
        return listaKorisnika;
    }

    public void setListaKorisnika(List<AbstractObject> listaKorisnika) {
        this.listaKorisnika = listaKorisnika;
    }

    public List<AbstractObject> vratiListuTipovaSkija() throws Exception {
        SOUcitajListuTipovaSkija sout = new SOUcitajListuTipovaSkija();
        sout.izvrsiOperaciju();
        return sout.getListaTipovaSkija();
    }

    private List<AbstractObject> ucitajListuKorisnika() throws Exception {
        SOUcitajListuKorisnika soulk = new SOUcitajListuKorisnika();
        soulk.izvrsiOperaciju();
        return soulk.getLista();
    }

    public AbstractObject sacuvajKorisnika(Korisnik kor) throws Exception {
        SOSacuvajKorisnika sosk = new SOSacuvajKorisnika(kor);
        sosk.izvrsiOperaciju();
        return sosk.getKlijent();
    }

    public void obrisiKorisnika(AbstractObject kor) throws Exception {
        SOObrisiKorisnika sook = new SOObrisiKorisnika(kor);
        sook.izvrsiOperaciju();
    }

    public List<AbstractObject> obrisiParSkija(ParSkija parSkija) throws Exception {
        SOObrisiParSkija soops = new SOObrisiParSkija();
        soops.setParSkija(parSkija);
        soops.izvrsiOperaciju();
        return soops.getLista();

    }

    public List<AbstractObject> vratiListuParovaSkija() throws Exception {
        SOUcitajListuParovaSkija soulp = new SOUcitajListuParovaSkija();
        soulp.izvrsiOperaciju();
        return soulp.getListaParova();
    }

    public List<AbstractObject> ucitajListuSkijasa() throws Exception {
        SOUcitajListuSkijasa souls = new SOUcitajListuSkijasa();
        souls.izvrsiOperaciju();
        return souls.getListaSkijasa();
    }

    public List<AbstractObject> pretraziSkijase(String string) throws Exception {
        SOPretraziSkijase sops = new SOPretraziSkijase();
        sops.setPretraga(string);
        sops.izvrsiOperaciju();
        return sops.getLista();
    }

    public AbstractObject kreirajRezervacijuSkija(RezervacijaSkija rezervacijaSkija) throws Exception {
        SOKreirajRezervacijuSkija sokr = new SOKreirajRezervacijuSkija();
        sokr.setRezervacija(rezervacijaSkija);
        sokr.izvrsiOperaciju();
        return sokr.getRezervacija();
    }

    public AbstractObject zapamtiRezervacijuSkija(RezervacijaSkija rezervacijaSkija) throws Exception {
        SOZapamtiRezervacijuSkija sozr = new SOZapamtiRezervacijuSkija();
        sozr.setRezervacijaSkija(rezervacijaSkija);
        sozr.izvrsiOperaciju();
        return sozr.getRezervacijaSkija();
    }

    public List<AbstractObject> pretraziRezervacije(String string) throws Exception {
        SOPretraziRezervacije sopr = new SOPretraziRezervacije();
        sopr.setPretraga(string);
        sopr.izvrsiOperaciju();
        return sopr.getLista();
    }

    public List<AbstractObject> ucitajListuRezervacija() throws Exception {
        SOUcitajListuRezervacija soulr = new SOUcitajListuRezervacija();
        soulr.izvrsiOperaciju();
        return soulr.getListaRezervacija();
    }

    public List<AbstractObject> obrisiRezervaciju(RezervacijaSkija rezervacijaSkija) throws Exception {
        SOObrisiRezervaciju soobr = new SOObrisiRezervaciju(rezervacijaSkija);
        soobr.izvrsiOperaciju();
        return soobr.getLista();
    }

    public AbstractObject izlogujKorisnika(Korisnik korisnik) {
        SOIzlogujKorisnika soik = new SOIzlogujKorisnika();
        soik.setKorisnik(korisnik);
        return soik.getKorisnik();
    }

}
