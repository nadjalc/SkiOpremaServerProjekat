/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import forme.FrmKorisnici;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nadja
 */
public class NitOsvezavanja extends Thread {

    FrmKorisnici frm;

    public NitOsvezavanja(FrmKorisnici forma) {
        this.frm = forma;

    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Thread.sleep(5000);
                frm.osveziFormu();
            } catch (InterruptedException ex) {
                Logger.getLogger(NitOsvezavanja.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
