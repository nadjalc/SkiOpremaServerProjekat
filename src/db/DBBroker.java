/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.AbstractObject;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DBUtil;

/**
 *
 * @author Nadja
 */
public class DBBroker {

//    private static DBBroker instance;
    private Connection connection;

    public DBBroker() {
    }

//    public static DBBroker getInstance() {
//        if (instance == null) {
//            instance = new DBBroker();
//        }
//        return instance;
//    }
    public void otvoriKonekciju() {
        try {
            DBUtil dbUtil = new DBUtil();
            String url = dbUtil.vratiURL();
            String username = dbUtil.vratiKorisnika();
            String password = dbUtil.vratiSifru();
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
            System.out.println("Uspesna konekcija sa bazom!");

        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void prekiniKonekciju() {
        try {
            connection.close();
            System.out.println("Prekinuta konekcija");
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Prekidanje konekcije nije uspelo");
        }

    }

    public void potvrdiTransakciju() {
        try {
            connection.commit();
            System.out.println("Commit uspesan");
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Commit neuspesan");
        }
    }

    public void ponistiTransakciju() {
        try {
            connection.rollback();
            System.out.println("Rollback uspesan");
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Rollback neuspesan");
        }
    }

    public List<AbstractObject> vratiObjekte(AbstractObject object) throws Exception {
        try {
            String sql = "SELECT * FROM " + object.vratiNazivTabele();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(sql);
            List<AbstractObject> listaObjekata = object.RSuTabelu(rs);
            s.close();
            System.out.println("Proosli objekti iz baze");
            return listaObjekata;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
    }

    public AbstractObject sacuvajIliIzmeniObjekat(AbstractObject parametar) throws Exception {
        try {
            System.out.println("Pozvalo se sacuvaj @@@@@@@");
            List<AbstractObject> lista = vratiObjekte(parametar);
            System.out.println("Stigla lista @@@@@");
            String sqlUpit;
            String tipUpita;
            for (AbstractObject obj : lista) {
                System.out.println(obj);
            }
            System.out.println("Parametar");
            System.out.println(parametar);

            if (lista.contains(parametar)) {
                tipUpita = "UPDATE";
                if (parametar.vratiPK() != null) {
                    sqlUpit = String.format("UPDATE %s SET %s WHERE %s = %s", parametar.vratiNazivTabele(), parametar.vratiUpdate(),
                            parametar.vratiPK(), parametar.vratiVrednostPK());
                } else {
                    sqlUpit = String.format("UPDATE %s SET %s WHERE %s", parametar.vratiNazivTabele(),
                            parametar.vratiUpdate(), parametar.vratiSlozenPK());
                }
            } else {
                tipUpita = "INSERT";
                sqlUpit = String.format("INSERT INTO %s VALUES (%s)", parametar.vratiNazivTabele(), parametar.vratiParametre());
            }
            System.out.println(sqlUpit);
            Statement s = connection.createStatement();
            s.executeUpdate(sqlUpit);
            if (tipUpita.equals("INSERT")) {
                System.out.println("Uslo je u insert @@@@@@@@@@@");
                ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID() AS last_id FROM " + parametar.vratiNazivTabele());
                while (rs.next()) {
                    System.out.println("Uslo u while da mu da index @@@@@@@");
                    String lastid = rs.getString("last_id");
                    System.out.println(lastid);
                    parametar.postaviVrednostPK(lastid);
                    break;
                }
            }
            s.close();
            return parametar;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Vec na dbb bacilo gresku");
            throw new Exception(ex.getMessage());
        }
    }

    public AbstractObject obrisiObjekat(AbstractObject obj) throws Exception {
        try {
            String sql;
            if (obj.vratiPK() != null) {
                sql = String.format("DELETE FROM %s WHERE %s = %s", obj.vratiNazivTabele(), obj.vratiPK(),
                        obj.vratiVrednostPK());
            } else {
                sql = String.format("DELETE FROM %s WHERE %s", obj.vratiNazivTabele(), obj.vratiSlozenPK());
            }
            Statement s = connection.createStatement();
            System.out.println(sql);
            s.executeUpdate(sql);
            s.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
        return obj;
    }

    public AbstractObject vratiObjekatPoKljucu(AbstractObject o, String ID) throws Exception {
        String upit;
        if (o.vratiPK() != null) {
            upit = "SELECT * FROM " + o.vratiNazivTabele() + " WHERE " + o.vratiPK() + "=" + ID;
        } else {
            upit = "SELECT * FROM " + o.vratiNazivTabele() + " WHERE " + o.vratiSlozenPK();
        }
        try (Statement s = connection.createStatement();) {
            ResultSet rs = s.executeQuery(upit);
            List<AbstractObject> listaObjekata = o.RSuTabelu(rs);
            s.close();
            System.out.println("Uspesno izvrsen mini SELECT");
            return listaObjekata.get(0);
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());

        }
    }

    public AbstractObject sacuvajObjekat(AbstractObject o) throws Exception {
        try {

            String tipUpita = "INSERT";
            String upit = String.format("INSERT INTO %s VALUES (%s)", o.vratiNazivTabele(), o.vratiParametre());

            System.out.println(upit);
            Statement s = connection.createStatement();
            s.executeUpdate(upit);
            ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID() as last_id from " + o.vratiNazivTabele());
            while (rs.next()) {
                String lastid = rs.getString("last_id");
                System.out.println(lastid);
                o.postaviVrednostPK(lastid);
                break;
            }

            s.close();
            return o;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
    }
}
