/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nadja
 */
public class Server extends Thread {

    private ServerSocket serverSocket;
    public static int brPorta = 9000;
    static List<KlijentNit> klijenti = new ArrayList<>();
    private static boolean aktivan = false;

    public Server() {
        try {
            serverSocket = new ServerSocket(brPorta);
            System.out.println("Server socket kreiran na portu: " + brPorta);
        } catch (IOException ex) {
            System.out.println("Nije uspelo kreiranje server socketa");
        }
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                Socket socket = serverSocket.accept();
                KlijentNit kn = new KlijentNit(socket, klijenti);
                kn.start();
                System.out.println("Klijent stigao");
            }
        } catch (SocketException s) {
            System.out.println("Server se gasi!");
        } catch (IOException ex) {
            System.out.println("Greska prilikom povezivanja klijenta!");
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void prekiniNiti(){
        try {
            serverSocket.close();
            for (KlijentNit klijentNit : klijenti) {
              klijentNit.getSocket().close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public static boolean isAktivan() {
        return aktivan;
    }

    public static void setAktivan(boolean aktivan) {
        Server.aktivan = aktivan;
    }
    
    
}
