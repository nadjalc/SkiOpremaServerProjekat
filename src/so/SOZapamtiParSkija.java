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
public class SOZapamtiParSkija extends AbstractSo{
    private AbstractObject parametar;
    private AbstractObject parSkija;

    public SOZapamtiParSkija(AbstractObject parametar) {
        this.parametar = parametar;
    }
    
    

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        System.out.println("Otisao zahtev za cuvanje na bazu @@@@@@@");
        parSkija = dbb.sacuvajIliIzmeniObjekat(parametar);
        
    }

    public AbstractObject getParametar() {
        return parametar;
    }

    public void setParametar(AbstractObject parametar) {
        this.parametar = parametar;
    }

    public AbstractObject getParSkija() {
        return parSkija;
    }

    public void setParSkija(AbstractObject parSkija) {
        this.parSkija = parSkija;
    }
    
    
    
}
