/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DBBroker;

/**
 *
 * @author Nadja
 */
public abstract class AbstractSo {

    protected DBBroker dbb = new DBBroker();


    synchronized public void izvrsiOperaciju() throws Exception {
        otvoriKonekciju();
        izvrsiKonkretnuOperaciju();
        potvrdiTransakciju();
        zatvoriKonekciju();
    }

    private void otvoriKonekciju() {
        dbb.otvoriKonekciju();
    }

    protected abstract void izvrsiKonkretnuOperaciju() throws Exception;

    private void potvrdiTransakciju() {
        dbb.potvrdiTransakciju();
    }

    private void zatvoriKonekciju() {
        dbb.prekiniKonekciju();
    }

}
