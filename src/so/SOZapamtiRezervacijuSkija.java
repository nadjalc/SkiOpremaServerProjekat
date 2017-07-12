/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObject;
import domen.ParSkija;
import domen.RezervacijaSkija;
import domen.StavkaRezervacijeSkija;
import domen.TipSkija;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nadja
 */
public class SOZapamtiRezervacijuSkija extends AbstractSo {

    private RezervacijaSkija rezervacijaSkija;

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
//        StavkaRezervacijeSkija nova = new StavkaRezervacijeSkija();
//        nova.setRezervacijaSkija(rezervacijaSkija);
//        dbb.obrisiObjekat(nova);
        List<AbstractObject> listaStavki = dbb.vratiObjekte(new StavkaRezervacijeSkija());
        RezervacijaSkija r = new RezervacijaSkija();
        r.setRezervacijaID(rezervacijaSkija.getRezervacijaID());
        List<StavkaRezervacijeSkija> novaLista = new LinkedList<>();
        for (AbstractObject stav : listaStavki) {
            StavkaRezervacijeSkija s = (StavkaRezervacijeSkija) stav;
            if (s.getRezervacijaSkija().getRezervacijaID().equals(r.getRezervacijaID())) {
                novaLista.add(s);
            }
        }
        for (StavkaRezervacijeSkija stavkaRezervacijeSkija : novaLista) {
            stavkaRezervacijeSkija.setRezervacijaSkija(r);
        }
        r.setListaStavki(novaLista);
        for (StavkaRezervacijeSkija brisStavka : r.getListaStavki()) {
            dbb.obrisiObjekat(brisStavka);
        }
        dbb.potvrdiTransakciju();
        RezervacijaSkija rs = (RezervacijaSkija) dbb.sacuvajIliIzmeniObjekat(rezervacijaSkija);
        for (StavkaRezervacijeSkija stavkaRezervacijeVoznje : rezervacijaSkija.getListaStavki()) {
            stavkaRezervacijeVoznje = (StavkaRezervacijeSkija) dbb.sacuvajObjekat(stavkaRezervacijeVoznje);
        }
        rs.setListaStavki(rezervacijaSkija.getListaStavki());
        rezervacijaSkija = rs;
    }

    public RezervacijaSkija getRezervacijaSkija() {
        return rezervacijaSkija;
    }

    public void setRezervacijaSkija(RezervacijaSkija rezervacijaSkija) {
        this.rezervacijaSkija = rezervacijaSkija;
    }

}
