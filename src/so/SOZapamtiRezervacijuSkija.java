/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.RezervacijaSkija;
import domen.StavkaRezervacijeSkija;

/**
 *
 * @author Nadja
 */
public class SOZapamtiRezervacijuSkija extends AbstractSo{

    private RezervacijaSkija rezervacijaSkija;
    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
//        StavkaRezervacijeSkija nova = new StavkaRezervacijeSkija();
//        nova.setRezervacijaSkija(rezervacijaSkija);
//        dbb.obrisiObjekat(nova);
        RezervacijaSkija rs = (RezervacijaSkija) dbb.sacuvajIliIzmeniObjekat(rezervacijaSkija);
        for (StavkaRezervacijeSkija stavkaRezervacijeVoznje : rezervacijaSkija.getListaStavki()) {
            stavkaRezervacijeVoznje = (StavkaRezervacijeSkija) dbb.sacuvajIliIzmeniObjekat(stavkaRezervacijeVoznje);
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
