/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import domen.ParSkija;
import domen.TipSkija;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.SOPretraziRezervacije;
import so.SOPretraziSkijase;
import so.SOZapamtiParSkija;

/**
 *
 * @author Nadja
 */
public class Test {
    public void testSOPretraziRezervacije(){
        try {
            SOPretraziRezervacije sopr = new SOPretraziRezervacije();
            sopr.setPretraga("143");
            sopr.izvrsiOperaciju();
            System.out.println(sopr.getLista());
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void testSOPretraziSkijase(){
        try {
            SOPretraziSkijase sopr = new SOPretraziSkijase();
            sopr.setPretraga("Kostic");
            sopr.izvrsiOperaciju();
            System.out.println(sopr.getLista());
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void testSOSacuvajSkije(ParSkija ps){
        try {
            SOZapamtiParSkija sopr = new SOZapamtiParSkija(ps);
            sopr.izvrsiOperaciju();
            System.out.println(sopr.getParSkija());
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        Test t1 = new Test();
//        t1.testSOPretraziRezervacije();
//        t1.testSOPretraziSkijase();
        
        ParSkija ps = new ParSkija("0", 132, 12, "Nordica", "Nordica", new TipSkija("1"));
        t1.testSOSacuvajSkije(ps);
    }
}
