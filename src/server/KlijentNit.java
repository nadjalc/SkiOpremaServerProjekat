/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import domen.AbstractObject;
import domen.Korisnik;
import domen.ParSkija;
import domen.RezervacijaSkija;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import konstante.Konstante;
import poslovnaLogika.Kontroler;
import transfer.KlijentTransfer;
import transfer.ServerTransfer;

/**
 *
 * @author Nadja
 */
public class KlijentNit extends Thread {

    private Socket socket;
    private List<KlijentNit> klijenti;
    ObjectInputStream in;
    ObjectOutputStream out;
    AbstractObject korinisnik;

    public KlijentNit(Socket socket, List<KlijentNit> klijenti) {
        this.klijenti = klijenti;
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Nit klijenta je pokrenuta");
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("out kreiran");
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("in kreiran");
            while (true) {
                System.out.println("Cekanje na klijenta");
                KlijentTransfer kt = (KlijentTransfer) in.readObject();
                System.out.println("Stigao je objekat");
                ServerTransfer st = new ServerTransfer();
                int operacija = kt.getOperacija();
                try {
                    switch (operacija) {
                        case Konstante.ULOGUJ_KORISNIKA:
                            korinisnik = Kontroler.getInstance().ulogujKorisnika((Korisnik) kt.getParametar());
                            st.setPodaci(korinisnik);
                            break;
                        case Konstante.UCITAJ_LISTU_TIPOVA_SKIJA:
                            List<AbstractObject> listaTipovaSkija = Kontroler.getInstance().vratiListuTipovaSkija();
                            st.setPodaci(listaTipovaSkija);

                            break;
                        case Konstante.SACUVAJ_PAR_SKIJA:
                            System.out.println("Stigao zahtev lepo na klijenta za cuvanje @@@@@");
                            AbstractObject parSkija = Kontroler.getInstance().zapamtiParSkija((ParSkija) kt.getParametar());
                            st.setPodaci(parSkija);
                            break;

                        case Konstante.OBRISI_PAR_SKIJA:
                            List<AbstractObject> listaParovaBezObrisanih = Kontroler.getInstance().obrisiParSkija((ParSkija) kt.getParametar());
                            st.setPodaci(listaParovaBezObrisanih);
                            break;
                        case Konstante.UCITAJ_LISTU_PAROVA_SKIJA:
                            List<AbstractObject> listaParovaSkija = Kontroler.getInstance().vratiListuParovaSkija();
                            st.setPodaci(listaParovaSkija);
                            break;
                        case Konstante.UCITAJ_LISTU_SKIJASA:
                            List<AbstractObject> listaSkijasa = Kontroler.getInstance().ucitajListuSkijasa();
                            st.setPodaci(listaSkijasa);
                            break;
                        case Konstante.PRETRAZI_SKIJASA:
                            List<AbstractObject> listaDatogSkijasa = Kontroler.getInstance().pretraziSkijase((String) kt.getParametar());
                            st.setPodaci(listaDatogSkijasa);
                            break;
                        case Konstante.PRETRAZI_REZERVACIJE:
                            List<AbstractObject> listaRezervacija = Kontroler.getInstance().pretraziRezervacije((String) kt.getParametar());
                            st.setPodaci(listaRezervacija);
                            break;
                            case Konstante.KREIRAJ_REZERVACIJU_SKIJA:
                            AbstractObject rs = Kontroler.getInstance().kreirajRezervacijuSkija((RezervacijaSkija) kt.getParametar());
                            st.setPodaci(rs);
                            break;
                            case Konstante.ZAPAMTI_REZERVACIJU_SKIJA:
                            AbstractObject rezerv = Kontroler.getInstance().zapamtiRezervacijuSkija((RezervacijaSkija) kt.getParametar());
                            st.setPodaci(rezerv);
                            break;
                        default:
                            kt.getParametar();
                            break;
                    }
                    st.setUspesnost(1);
                } catch (Exception e) {
                    st.setUspesnost(-1);
                    st.setException(e);

                }
                out.writeObject(st);

            }

        } catch (IOException ex) {
            Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("**********Logout korisnik");
            Korisnik k = (Korisnik) korinisnik;
            k.setUlogovan(false);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Socket getSocket() {
        return socket;
    }

}
