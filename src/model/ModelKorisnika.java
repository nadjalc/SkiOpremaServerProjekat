/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.AbstractObject;
import domen.Korisnik;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nadja
 */
public class ModelKorisnika extends AbstractTableModel {

    private List<AbstractObject> listaKorisnika;

    public void setListaKorisnika(List<AbstractObject> listaKorisnika) {
        this.listaKorisnika = listaKorisnika;
    }

    public ModelKorisnika(List<AbstractObject> listaKorisnika) {
        this.listaKorisnika = listaKorisnika;
    }

    String[] kolone = new String[]{"ID", "Korisnicko ime", "Lozinka", "Ime", "Prezime", "Status"};

    @Override
    public int getRowCount() {
        if (listaKorisnika == null) {
            return 0;
        }
        return listaKorisnika.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Korisnik korisnik = (Korisnik) listaKorisnika.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return korisnik.getKorisnikID();
            case 1:
                return korisnik.getKorisnickoIme();
            case 2:
                return korisnik.getLozinka();
            case 3:
                return korisnik.getIme();
            case 4:
                return korisnik.getPrezime();
            case 5:
                return korisnik.getStatusText();
            default:
                return "n/a";
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    
    public List<AbstractObject> getListaKorisnika() {
        return listaKorisnika;
    }

}
