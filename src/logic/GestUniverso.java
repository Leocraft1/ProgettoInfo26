package logic;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.time.LocalDate;
import java.time.LocalTime;
import leolib.iodb.DBConnector;
import leolib.iodb.PropertiesRead;
import leolib.listutils.ALUtils;
import model.*;
import model.enums.*;
import myexceptions.*;

public class GestUniverso {

    private DBConnector dbc;
    private ArrayList<String> tab_names = new ArrayList<>(); //Ordine di nomi di config.properties
    private ArrayList<ArrayList<String>> tab_attr = new ArrayList<>();
    private String cfg_path = "config.properties";

    public GestUniverso() {
    } //Empty constructor because Cli needs to access cfg_path

    //TEST constructors
    public GestUniverso(DBConnector dbc) throws IOException {
        this.dbc = dbc;
        this.loadProperties();
    }

    public GestUniverso(DBConnector dbc, ArrayList<String> tab_names, ArrayList<ArrayList<String>> tab_attr) {
        this.dbc = dbc;
        this.tab_names = tab_names;
        this.tab_attr = tab_attr;
    }

    public void loadProperties() throws IOException {
        Properties p = PropertiesRead.createObj(cfg_path);
        //Loads tab names
        tab_names.add(p.getProperty("stella.name"));
        tab_names.add(p.getProperty("pianeta.name"));
        tab_names.add(p.getProperty("galassia.name"));
        tab_names.add(p.getProperty("eventocosmico.name"));
        //Loads attributes
        tab_attr.add(ALUtils.stringToArrayList(p.getProperty("stella.attributes").split(",")));
        tab_attr.add(ALUtils.stringToArrayList(p.getProperty("pianeta.attributes").split(",")));
        tab_attr.add(ALUtils.stringToArrayList(p.getProperty("galassia.attributes").split(",")));
        tab_attr.add(ALUtils.stringToArrayList(p.getProperty("eventocosmico.attributes").split(",")));
    }

    public DBConnector getDbc() {
        return dbc;
    }

    public void setDbc(DBConnector dbc) {
        this.dbc = dbc;
    }

    public ArrayList<String> getTabNames() {
        return tab_names;
    }

    public void setTabNames(ArrayList<String> tab_names) {
        this.tab_names = tab_names;
    }

    public ArrayList<ArrayList<String>> getTabAttr() {
        return tab_attr;
    }

    public void setTabAttr(ArrayList<ArrayList<String>> tab_attr) {
        this.tab_attr = tab_attr;
    }

    public String getCfg_path() {
        return cfg_path;
    }

    public void setCfg_path(String cfg_path) {
        this.cfg_path = cfg_path;
    }

    /**
     * Gets Stella ArrayList from DB.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Stella> getStelle() throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(0));
        return getStelleFromRS(rs);
    }

    /**
     * Saves Stella ArrayList to DB.
     *
     * @param s
     * @throws SQLException
     */
    public void saveStelle(ArrayList<Stella> s) throws SQLException {
        //Deletes everything before saving new copy of data
        dbc.update("DELETE FROM " + tab_names.get(0));
        for (int i = 0; i < s.size(); i++) {
            dbc.update("INSERT INTO " + tab_names.get(0) + " VALUES(?,?,?,?,?,?)", s.get(i).getIdStella(), s.get(i).getNome(), s.get(i).getSistema(), s.get(i).getTemperatura(), s.get(i).getFase().toString(), s.get(i).getIdGalassia());
        }
    }

    /**
     * Gets Pianeta ArrayList from DB.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Pianeta> getPianeti() throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(1));
        return getPianetiFromRS(rs);
    }

    /**
     * Saves Pianeta ArrayList to DB.
     *
     * @param pianeti
     * @throws SQLException
     */
    public void savePianeti(ArrayList<Pianeta> p) throws SQLException {
        dbc.update("DELETE FROM " + tab_names.get(1));

        for (int i = 0; i < p.size(); i++) {
            dbc.update(
                    "INSERT INTO " + tab_names.get(1) + " VALUES (?,?,?,?,?,?)",
                    p.get(i).getIdPianeta(),
                    p.get(i).getNome(),
                    p.get(i).getSistema(),
                    p.get(i).getTipo().toString(),
                    p.get(i).getTemperatura(),
                    p.get(i).getIdGalassia()
            );
        }
    }

    /**
     * Gets Galassia ArrayList from DB.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Galassia> getGalassie() throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(2));
        return getGalassieFromRS(rs);
    }

    /**
     * Saves Galassia ArrayList to DB.
     *
     * @param galassie
     * @throws SQLException
     */
    public void saveGalassie(ArrayList<Galassia> g) throws SQLException {
        dbc.update("DELETE FROM " + tab_names.get(2));

        for (int i = 0; i < g.size(); i++) {
            dbc.update(
                    "INSERT INTO " + tab_names.get(2) + " VALUES (?,?,?,?)",
                    g.get(i).getIdGalassia(),
                    g.get(i).getNome(),
                    g.get(i).getTipo(),
                    g.get(i).getMassa()
            );
        }
    }

    /**
     * Gets EventoCosmico ArrayList from DB.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<EventoCosmico> getEventiCosmici() throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(3));
        return getECFromRS(rs);
    }

    /**
     * Saves EventoCosmico ArrayList to DB.
     *
     * @param eventi
     * @throws SQLException
     */
    public void saveEventiCosmici(ArrayList<EventoCosmico> e) throws SQLException {
        dbc.update("DELETE FROM " + tab_names.get(3));

        for (int i = 0; i < e.size(); i++) {
            dbc.update(
                    "INSERT INTO " + tab_names.get(3) + " VALUES (?,?,?,?,?,?)",
                    e.get(i).getIdEventoCosmico(),
                    e.get(i).getNome(),
                    e.get(i).getTipo().toString(),
                    e.get(i).getDataEvento(),
                    e.get(i).getOraEvento(),
                    e.get(i).getIdStella()
            );
        }
    }

    //ADD
    public void addStella(Stella s) throws SQLException, DuplicateException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(0) + " WHERE " + tab_attr.get(0).get(0) + " = ?", s.getIdStella());
        if (rs.next()) {
            throw new DuplicateException("Stella gia'� presente con ID: " + s.getIdStella());
        }

        dbc.update("INSERT INTO " + tab_names.get(0) + " VALUES(?,?,?,?,?,?)",
                s.getIdStella(), s.getNome(), s.getSistema(), s.getTemperatura(), s.getFase().toString(), s.getIdGalassia());
    }

    public void addPianeta(Pianeta p) throws SQLException, DuplicateException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(1) + " WHERE " + tab_attr.get(1).get(0) + " = ?", p.getIdPianeta());
        if (rs.next()) {
            throw new DuplicateException("Pianeta gia'� presente con ID: " + p.getIdPianeta());
        }

        dbc.update("INSERT INTO " + tab_names.get(1) + " VALUES (?,?,?,?,?,?)",
                p.getIdPianeta(), p.getNome(), p.getSistema(), p.getTipo().toString(), p.getTemperatura(), p.getIdGalassia());
    }

    public void addGalassia(Galassia g) throws SQLException, DuplicateException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(2) + " WHERE " + tab_attr.get(2).get(0) + " = ?", g.getIdGalassia());
        if (rs.next()) {
            throw new DuplicateException("Galassia gia'� presente con ID: " + g.getIdGalassia());
        }

        dbc.update("INSERT INTO " + tab_names.get(2) + " VALUES (?,?,?,?)",
                g.getIdGalassia(), g.getNome(), g.getTipo(), g.getMassa());
    }

    public void addEventoCosmico(EventoCosmico e) throws SQLException, DuplicateException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(3) + " WHERE " + tab_attr.get(3).get(0) + " = ?", e.getIdEventoCosmico());
        if (rs.next()) {
            throw new DuplicateException("Evento gia'� presente con ID: " + e.getIdEventoCosmico());
        }

        dbc.update("INSERT INTO " + tab_names.get(3) + " VALUES (?,?,?,?,?,?)",
                e.getIdEventoCosmico(), e.getNome(), e.getTipo().toString(), e.getDataEvento(), e.getOraEvento(), e.getIdStella());
    }

    //DELETE
    public int deleteStella(int id) throws SQLException {
        return dbc.update("DELETE FROM " + tab_names.get(0) + " WHERE " + tab_attr.get(0).get(0) + " = ?", id);
    }

    public int deletePianeta(int id) throws SQLException {
        return dbc.update("DELETE FROM " + tab_names.get(1) + " WHERE " + tab_attr.get(1).get(0) + " = ?", id);
    }

    public int deleteGalassia(int id) throws SQLException {
        return dbc.update("DELETE FROM " + tab_names.get(2) + " WHERE " + tab_attr.get(2).get(0) + " = ?", id);
    }

    public int deleteEventoCosmico(int id) throws SQLException {
        return dbc.update("DELETE FROM " + tab_names.get(3) + " WHERE " + tab_attr.get(3).get(0) + " = ?", id);
    }

    //QUERIES
    /**
     * Gets all stars with temperature greater than the given value.
     *
     * @param minTemp
     * @return
     * @throws SQLException
     * @throws ParsingException
     */
    public ArrayList<Stella> getStelleByMinTemperatura(int minTemp) throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(0) + " WHERE temperatura > ? ORDER BY temperatura", minTemp);
        return getStelleFromRS(rs);
    }

    /**
     * Gets all stars in the given stellar phase.
     *
     * @param fase
     * @return
     * @throws SQLException
     */
    public ArrayList<Stella> getStelleByFase(FaseStella fase) throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(0) + " WHERE fase = ?", fase.toString());
        return getStelleFromRS(rs);
    }

    /**
     * Gets the hottest star in the database.
     *
     * @return
     * @throws SQLException
     * @throws ParsingException
     */
    public Stella getStellaPiuCalda() throws SQLException {
        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(0)
                + " ORDER BY temperatura DESC"
        );

        rs.next();
        return new Stella(
                rs.getInt("idStella"),
                rs.getString("nome"),
                rs.getString("sistema"),
                rs.getInt("temperatura"),
                FaseStella.valueOf(rs.getString("fase")),
                rs.getInt("idGalassia")
        );
    }

    /**
     * Gets all cosmic events of the given type.
     *
     * @param tipo
     * @return
     * @throws SQLException
     */
    public ArrayList<EventoCosmico> getEventiByTipo(TipoEventoCosmico tipo) throws SQLException {
        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(1) + " WHERE tipo = ?",
                tipo.toString()
        );

        return getECFromRS(rs);
    }

    /**
     * Gets all cosmic events after the given date.
     *
     * @param data
     * @return
     * @throws SQLException
     */
    public ArrayList<EventoCosmico> getEventiDopoData(LocalDate data) throws SQLException {
        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(1) + " WHERE dataEvento > ?",
                data
        );

        return getECFromRS(rs);
    }

    /**
     * Counts the number of cosmic events for a given star.
     *
     * @param idStella
     * @return
     * @throws SQLException
     */
    public int countEventiByStella(int idStella) throws SQLException {
        ResultSet rs = dbc.query(
                "SELECT COUNT(*) AS totale FROM " + tab_names.get(1)
                + " WHERE idStella = ?",
                idStella
        );

        rs.next();
        return rs.getInt("totale");
    }

    /**
     * Gets all galaxies with mass greater than the given value.
     *
     * @param minMassa
     * @return
     * @throws SQLException
     */
    public ArrayList<Galassia> getGalassieByMinMassa(int minMassa) throws SQLException {
        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(2) + " WHERE massa > ?",
                minMassa
        );

        return getGalassieFromRS(rs);
    }

    /**
     * Gets the most massive galaxy.
     *
     * @return
     * @throws SQLException
     */
    public Galassia getGalassiaPiuPesante() throws SQLException {
        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(2)
                + " ORDER BY massa DESC"
        );

        rs.next();
        return new Galassia(
                rs.getInt("idGalassia"),
                rs.getString("nome"),
                rs.getString("tipo"),
                rs.getInt("massa")
        );
    }

    /**
     * Gets all galaxies of the given type.
     *
     * @param tipo
     * @return
     * @throws SQLException
     */
    public ArrayList<Galassia> getGalassieByTipo(String tipo) throws SQLException {
        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(2) + " WHERE tipo = ?",
                tipo
        );

        return getGalassieFromRS(rs);
    }

    /**
     * Gets all planets of the given type.
     *
     * @param tipo
     * @return
     * @throws SQLException
     */
    public ArrayList<Pianeta> getPianetiByTipo(TipoPianeta tipo) throws SQLException {
        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(3) + " WHERE tipo = ?",
                tipo.toString()
        );

        return getPianetiFromRS(rs);
    }

    /**
     * Gets all planets with temperature between two values.
     *
     * @param minTemp
     * @param maxTemp
     * @return
     * @throws SQLException
     */
    public ArrayList<Pianeta> getPianetiByTemperaturaRange(int minTemp, int maxTemp) throws SQLException {
        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(3)
                + " WHERE temperatura BETWEEN ? AND ?",
                minTemp, maxTemp
        );

        return getPianetiFromRS(rs);
    }

    /**
     * Gets all planets belonging to the given galaxy.
     *
     * @param idGalassia
     * @return
     * @throws SQLException
     */
    public ArrayList<Pianeta> getPianetiByGalassia(int idGalassia) throws SQLException {
        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(3) + " WHERE idGalassia = ?",
                idGalassia
        );

        return getPianetiFromRS(rs);
    }

    /**
     * Gets all stars not associated with any galaxy.
     *
     * @return
     * @throws SQLException
     * @throws ParsingException
     */
    public ArrayList<Stella> getStelleSenzaGalassia() throws SQLException {
        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(0) + " WHERE idGalassia IS NULL"
        );

        return getStelleFromRS(rs);
    }

    /**
     * Gets all planets without a star system.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Pianeta> getPianetiSenzaSistema() throws SQLException {
        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(3) + " WHERE sistema IS NULL"
        );

        return getPianetiFromRS(rs);
    }

    /**
     * Counts the number of stars in the given galaxy.
     *
     * @param idGalassia
     * @return
     * @throws SQLException
     */
    public int countStelleInGalassia(int idGalassia) throws SQLException {
        ResultSet rs = dbc.query(
                "SELECT COUNT(*) AS totale FROM " + tab_names.get(0)
                + " WHERE idGalassia = ?",
                idGalassia
        );

        rs.next();
        return rs.getInt("totale");
    }

    /**
     * Gets the most recent cosmic event.
     *
     * @return
     * @throws SQLException
     */
    public EventoCosmico getUltimoEventoCosmico() throws SQLException {
        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(1)
                + " ORDER BY dataEvento DESC, oraEvento DESC"
        );

        rs.next();
        return new EventoCosmico(
                rs.getInt("idEventoCosmico"),
                rs.getString("nome"),
                TipoEventoCosmico.valueOf(rs.getString("tipo")),
                rs.getDate("dataEvento").toLocalDate(),
                rs.getTime("oraEvento").toLocalTime(),
                rs.getInt("idStella")
        );
    }

    /**
     * Gets all galaxies without any associated stars.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Galassia> getGalassieSenzaStelle() throws SQLException {
        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(2)
                + " WHERE idGalassia NOT IN ("
                + "SELECT DISTINCT idGalassia FROM " + tab_names.get(0)
                + " WHERE idGalassia IS NOT NULL)"
        );

        return getGalassieFromRS(rs);
    }

    //Private methods for parsing ResultSets into objects
    private ArrayList<Stella> getStelleFromRS(ResultSet rs) throws SQLException {
        ArrayList<Stella> out = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt(tab_attr.get(0).get(0));
            String nome = rs.getString(tab_attr.get(0).get(1));
            String sistema = rs.getString(tab_attr.get(0).get(2));
            int temperatura = rs.getInt(tab_attr.get(0).get(3));
            String fase_str = rs.getString(tab_attr.get(0).get(4));
            int idGalassia = rs.getInt(tab_attr.get(0).get(5));
            FaseStella fase = FaseStella.valueOf(fase_str);

            out.add(new Stella(id, nome, sistema, temperatura, fase, idGalassia));
        }
        return out;
    }
    
    private ArrayList<Pianeta> getPianetiFromRS(ResultSet rs) throws SQLException {
        ArrayList<Pianeta> out = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt(tab_attr.get(1).get(0));
            String nome = rs.getString(tab_attr.get(1).get(1));
            String sistema = rs.getString(tab_attr.get(1).get(2));
            String tipoStr = rs.getString(tab_attr.get(1).get(3));
            TipoPianeta tipo = TipoPianeta.valueOf(tipoStr);
            int temperatura = rs.getInt(tab_attr.get(1).get(4));
            int idGalassia = rs.getInt(tab_attr.get(1).get(5));

            out.add(new Pianeta(id, nome, sistema, tipo, temperatura, idGalassia));
        }
        return out;
    }

    private ArrayList<Galassia> getGalassieFromRS(ResultSet rs) throws SQLException {
        ArrayList<Galassia> out = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt(tab_attr.get(2).get(0));
            String nome = rs.getString(tab_attr.get(2).get(1));
            String tipo = rs.getString(tab_attr.get(2).get(2));
            int massa = rs.getInt(tab_attr.get(2).get(3));

            out.add(new Galassia(id, nome, tipo, massa));
        }
        return out;
    }

    private ArrayList<EventoCosmico> getECFromRS(ResultSet rs) throws SQLException {
        ArrayList<EventoCosmico> out = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt(tab_attr.get(3).get(0));
            String nome = rs.getString(tab_attr.get(3).get(1));
            String tipoStr = rs.getString(tab_attr.get(3).get(2));
            TipoEventoCosmico tipo = TipoEventoCosmico.valueOf(tipoStr);
            LocalDate data = rs.getDate(tab_attr.get(3).get(3)).toLocalDate();
            LocalTime ora = rs.getTime(tab_attr.get(3).get(4)).toLocalTime();
            int idStella = rs.getInt(tab_attr.get(3).get(5));

            out.add(new EventoCosmico(id, nome, tipo, data, ora, idStella));
        }
        return out;
    }

    public boolean existsStella(int id) throws SQLException {
        ResultSet rs = dbc.query(
                "SELECT 1 FROM " + tab_names.get(0)
                + " WHERE " + tab_attr.get(0).get(0) + " = ?",
                id
        );
        return rs.next();
    }

    public boolean existsPianeta(int id) throws SQLException {
        ResultSet rs = dbc.query(
                "SELECT 1 FROM " + tab_names.get(1)
                + " WHERE " + tab_attr.get(1).get(0) + " = ?",
                id
        );
        return rs.next();
    }

    public boolean existsGalassia(int id) throws SQLException {
        ResultSet rs = dbc.query(
                "SELECT 1 FROM " + tab_names.get(2)
                + " WHERE " + tab_attr.get(2).get(0) + " = ?",
                id
        );
        return rs.next();
    }

    public ArrayList<Stella> getStelleByGalassia(int idGalassia) throws SQLException {
        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(0)
                + " WHERE idGalassia = ?",
                idGalassia
        );
        return getStelleFromRS(rs);
    }

    public ArrayList<Pianeta> getPianetiAbitabili() throws SQLException {
        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(1)
                + " WHERE temperatura BETWEEN -20 AND 50"
        );
        return getPianetiFromRS(rs);
    }

    public int countPianetiInGalassia(int idGalassia) throws SQLException {
        ResultSet rs = dbc.query(
                "SELECT COUNT(*) AS totale FROM " + tab_names.get(1)
                + " WHERE idGalassia = ?",
                idGalassia
        );
        rs.next();
        return rs.getInt("totale");
    }

    public ArrayList<Pianeta> getPianetiByMinTemperatura(int minTemp) throws SQLException {
        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(1)
                + " WHERE temperatura > ?",
                minTemp
        );
        return getPianetiFromRS(rs);
    }
}
