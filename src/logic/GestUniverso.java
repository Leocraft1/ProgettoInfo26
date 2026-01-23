package logic;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import leolib.iodb.DBConnector;
import leolib.iodb.PropertiesRead;
import leolib.listutils.ALUtils;
import model.*;
import model.enums.*;
import myexceptions.*;

public class GestUniverso {

    private DBConnector dbc;
    private ArrayList<String> tab_names = new ArrayList<>();
    private ArrayList<ArrayList<String>> tab_attr = new ArrayList<>();
    private String cfg_path = "config.properties";

    /**
     *
     */
    public GestUniverso() {
    }

    /**
     *
     * @param dbc
     * @throws IOException
     */
    public GestUniverso(DBConnector dbc) throws IOException {
        this.dbc = dbc;
        this.loadProperties();
    }

    /**
     *
     * @param dbc
     * @param tab_names
     * @param tab_attr
     */
    public GestUniverso(DBConnector dbc, ArrayList<String> tab_names, ArrayList<ArrayList<String>> tab_attr) {
        this.dbc = dbc;
        this.tab_names = tab_names;
        this.tab_attr = tab_attr;
    }

    /**
     *
     * @throws IOException
     */
    public void loadProperties() throws IOException {
        Properties p = PropertiesRead.createObj(cfg_path);
        tab_names.add(p.getProperty("stella.name"));
        tab_names.add(p.getProperty("pianeta.name"));
        tab_names.add(p.getProperty("galassia.name"));
        tab_names.add(p.getProperty("eventocosmico.name"));

        tab_attr.add(ALUtils.stringToArrayList(p.getProperty("stella.attributes").split(",")));
        tab_attr.add(ALUtils.stringToArrayList(p.getProperty("pianeta.attributes").split(",")));
        tab_attr.add(ALUtils.stringToArrayList(p.getProperty("galassia.attributes").split(",")));
        tab_attr.add(ALUtils.stringToArrayList(p.getProperty("eventocosmico.attributes").split(",")));
    }

    // Getters and Setters omessi per brevità (lasciali come erano)
    /**
     *
     * @return
     */
    public DBConnector getDbc() {
        return dbc;
    }

    /**
     *
     * @param dbc
     */
    public void setDbc(DBConnector dbc) {
        this.dbc = dbc;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getTabNames() {
        return tab_names;
    }

    /**
     *
     * @param tab_names
     */
    public void setTabNames(ArrayList<String> tab_names) {
        this.tab_names = tab_names;
    }

    /**
     *
     * @return
     */
    public ArrayList<ArrayList<String>> getTabAttr() {
        return tab_attr;
    }

    /**
     *
     * @param tab_attr
     */
    public void setTabAttr(ArrayList<ArrayList<String>> tab_attr) {
        this.tab_attr = tab_attr;
    }

    /**
     *
     * @return
     */
    public String getCfg_path() {
        return cfg_path;
    }

    /**
     *
     * @param cfg_path
     */
    public void setCfg_path(String cfg_path) {
        this.cfg_path = cfg_path;
    }

    // --- METODI HELPER PER GESTIRE IL PROBLEMA 0 vs NULL ---
    // Questi metodi trasformano lo 0 di Java in un null reale per il DB
    private Object checkInt(int i) {
        return (i == 0) ? null : i;
    }

    private Object checkStr(String s) {
        return (s == null || s.trim().isEmpty()) ? null : s;
    }
    // -------------------------------------------------------

    /**
     *
     * @return @throws SQLException
     */
    public ArrayList<Stella> getStelle() throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(0));
        return getStelleFromRS(rs);
    }

    /**
     *
     * @param s
     * @throws SQLException
     */
    public void saveStelle(ArrayList<Stella> s) throws SQLException {
        dbc.update("DELETE FROM " + tab_names.get(0));
        for (int i = 0; i < s.size(); i++) {
            // FIX: Uso checkInt per idGalassia
            dbc.update("INSERT INTO " + tab_names.get(0) + " VALUES(?,?,?,?,?,?)",
                    s.get(i).getIdStella(),
                    s.get(i).getNome(),
                    checkStr(s.get(i).getSistema()),
                    s.get(i).getTemperatura(),
                    s.get(i).getFase().toString(),
                    checkInt(s.get(i).getIdGalassia()));
        }
    }

    /**
     *
     * @return @throws SQLException
     */
    public ArrayList<Pianeta> getPianeti() throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(1));
        return getPianetiFromRS(rs);
    }

    /**
     *
     * @param p
     * @throws SQLException
     */
    public void savePianeti(ArrayList<Pianeta> p) throws SQLException {
        dbc.update("DELETE FROM " + tab_names.get(1));
        for (int i = 0; i < p.size(); i++) {
            // FIX: Uso checkInt per idGalassia e checkStr per Sistema
            dbc.update("INSERT INTO " + tab_names.get(1) + " VALUES (?,?,?,?,?,?)",
                    p.get(i).getIdPianeta(),
                    p.get(i).getNome(),
                    checkStr(p.get(i).getSistema()),
                    p.get(i).getTipo().toString(),
                    p.get(i).getTemperatura(),
                    checkInt(p.get(i).getIdGalassia())
            );
        }
    }

    /**
     *
     * @return @throws SQLException
     */
    public ArrayList<Galassia> getGalassie() throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(2));
        return getGalassieFromRS(rs);
    }

    /**
     *
     * @param g
     * @throws SQLException
     */
    public void saveGalassie(ArrayList<Galassia> g) throws SQLException {
        dbc.update("DELETE FROM " + tab_names.get(2));
        for (int i = 0; i < g.size(); i++) {
            dbc.update("INSERT INTO " + tab_names.get(2) + " VALUES (?,?,?,?)",
                    g.get(i).getIdGalassia(),
                    g.get(i).getNome(),
                    g.get(i).getTipo().toString(),
                    g.get(i).getMassa()
            );
        }
    }

    /**
     *
     * @return @throws SQLException
     */
    public ArrayList<EventoCosmico> getEventiCosmici() throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(3));
        return getECFromRS(rs);
    }

    /**
     *
     * @param e
     * @throws SQLException
     */
    public void saveEventiCosmici(ArrayList<EventoCosmico> e) throws SQLException {
        dbc.update("DELETE FROM " + tab_names.get(3));
        for (int i = 0; i < e.size(); i++) {
            // FIX: checkInt per idStella
            dbc.update("INSERT INTO " + tab_names.get(3) + " VALUES (?,?,?,?,?,?)",
                    e.get(i).getIdEventoCosmico(),
                    e.get(i).getNome(),
                    e.get(i).getTipo().toString(),
                    e.get(i).getDataEvento(),
                    e.get(i).getOraEvento(),
                    checkInt(e.get(i).getIdStella())
            );
        }
    }

    // --- ADD METHODS (QUELLE CHE DAVANO ERRORE DI COMUNICAZIONE) ---
    /**
     *
     * @param s
     * @throws SQLException
     * @throws DuplicateException
     */
    public void addStella(Stella s) throws SQLException, DuplicateException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(0) + " WHERE " + tab_attr.get(0).get(0) + " = ?", s.getIdStella());
        if (rs.next()) {
            throw new DuplicateException("Stella gia' presente con ID: " + s.getIdStella());
        }

        // FIX: Se idGalassia è 0, passiamo null al DB per evitare errore Constraint
        dbc.update("INSERT INTO " + tab_names.get(0) + " VALUES(?,?,?,?,?,?)",
                s.getIdStella(),
                s.getNome(),
                checkStr(s.getSistema()),
                s.getTemperatura(),
                s.getFase().toString(),
                checkInt(s.getIdGalassia()));
    }

    /**
     *
     * @param p
     * @throws SQLException
     * @throws DuplicateException
     */
    public void addPianeta(Pianeta p) throws SQLException, DuplicateException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(1) + " WHERE " + tab_attr.get(1).get(0) + " = ?", p.getIdPianeta());
        if (rs.next()) {
            throw new DuplicateException("Pianeta gia' presente con ID: " + p.getIdPianeta());
        }

        // FIX: Se sistema è vuoto -> null. Se idGalassia è 0 -> null.
        dbc.update("INSERT INTO " + tab_names.get(1) + " VALUES (?,?,?,?,?,?)",
                p.getIdPianeta(),
                p.getNome(),
                checkStr(p.getSistema()),
                p.getTipo().toString(),
                p.getTemperatura(),
                checkInt(p.getIdGalassia())
        );
    }

    /**
     *
     * @param g
     * @throws SQLException
     * @throws DuplicateException
     */
    public void addGalassia(Galassia g) throws SQLException, DuplicateException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(2) + " WHERE " + tab_attr.get(2).get(0) + " = ?", g.getIdGalassia());
        if (rs.next()) {
            throw new DuplicateException("Galassia gia' presente con ID: " + g.getIdGalassia());
        }
        dbc.update("INSERT INTO " + tab_names.get(2) + " VALUES (?,?,?,?)",
                g.getIdGalassia(),
                g.getNome(),
                g.getTipo().toString(),
                g.getMassa());
    }

    /**
     *
     * @param e
     * @throws SQLException
     * @throws DuplicateException
     */
    public void addEventoCosmico(EventoCosmico e) throws SQLException, DuplicateException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(3) + " WHERE " + tab_attr.get(3).get(0) + " = ?", e.getIdEventoCosmico());
        if (rs.next()) {
            throw new DuplicateException("Evento gia' presente con ID: " + e.getIdEventoCosmico());
        }

        // NOTA: Qui avevi messo un controllo "if idStella == 0 throw exception".
        // Se l'evento DEVE avere una stella per forza, lascia il controllo.
        // Se invece può esistere un evento senza stella, usa checkInt(e.getIdStella()).
        // Dal tuo DB: idStella è YES (Nullable). Quindi può essere null. 
        // Rimuovo l'eccezione forzata e permetto l'inserimento NULL.
        dbc.update("INSERT INTO " + tab_names.get(3) + " VALUES (?,?,?,?,?,?)",
                e.getIdEventoCosmico(),
                e.getNome(),
                e.getTipo().toString(),
                e.getDataEvento(),
                e.getOraEvento(),
                checkInt(e.getIdStella()));
    }

    // --- DELETE (Rimangono uguali) ---
    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public int deleteStella(int id) throws SQLException {
        return dbc.update("DELETE FROM " + tab_names.get(0) + " WHERE " + tab_attr.get(0).get(0) + " = ?", id);
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public int deletePianeta(int id) throws SQLException {
        return dbc.update("DELETE FROM " + tab_names.get(1) + " WHERE " + tab_attr.get(1).get(0) + " = ?", id);
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public int deleteGalassia(int id) throws SQLException {
        return dbc.update("DELETE FROM " + tab_names.get(2) + " WHERE " + tab_attr.get(2).get(0) + " = ?", id);
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public int deleteEventoCosmico(int id) throws SQLException {
        return dbc.update("DELETE FROM " + tab_names.get(3) + " WHERE " + tab_attr.get(3).get(0) + " = ?", id);
    }

    // --- QUERIES CORRETTE ---
    /**
     *
     * @param minTemp
     * @return
     * @throws SQLException
     */
    public ArrayList<Stella> getStelleByMinTemperatura(int minTemp) throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(0) + " WHERE temperatura > ? ORDER BY temperatura", minTemp);
        return getStelleFromRS(rs);
    }

    /**
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
     *
     * @return @throws SQLException
     */
    public ArrayList<Stella> getStellaPiuCalda() throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(0) + " ORDER BY temperatura DESC");
        // Logica semplificata per prendere solo la prima
        ArrayList<Stella> all = getStelleFromRS(rs);
        ArrayList<Stella> top = new ArrayList<>();
        if (!all.isEmpty()) {
            top.add(all.get(0));
        }
        return top;
    }

    /**
     *
     * @param tipo
     * @return
     * @throws SQLException
     */
    public ArrayList<EventoCosmico> getEventiByTipo(TipoEventoCosmico tipo) throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(3) + " WHERE tipo = ?", tipo.toString());
        return getECFromRS(rs);
    }

    /**
     *
     * @param data
     * @return
     * @throws SQLException
     */
    public ArrayList<EventoCosmico> getEventiDopoData(LocalDate data) throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(3) + " WHERE dataEvento > ?", data);
        return getECFromRS(rs);
    }

    /**
     *
     * @param idStella
     * @return
     * @throws SQLException
     */
    public int countEventiByStella(int idStella) throws SQLException {
        // Questa query è corretta se l'inserimento è stato fatto bene.
        // Se idStella nel DB è corretto, questa restituisce il numero giusto.
        ResultSet rs = dbc.query("SELECT COUNT(*) AS totale FROM " + tab_names.get(3) + " WHERE idStella = ?", idStella);
        rs.next();
        return rs.getInt("totale");
    }

    /**
     *
     * @param minMassa
     * @return
     * @throws SQLException
     */
    public ArrayList<Galassia> getGalassieByMinMassa(int minMassa) throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(2) + " WHERE massa > ?", minMassa);
        return getGalassieFromRS(rs);
    }

    /**
     *
     * @return @throws SQLException
     */
    public ArrayList<Galassia> getGalassiaPiuPesante() throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(2) + " ORDER BY massa DESC");
        ArrayList<Galassia> all = getGalassieFromRS(rs);
        ArrayList<Galassia> top = new ArrayList<>();
        if (!all.isEmpty()) {
            top.add(all.get(0));
        }
        return top;
    }

    /**
     *
     * @param tipo
     * @return
     * @throws SQLException
     */
    public ArrayList<Galassia> getGalassieByTipo(TipoGalassia tipo) throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(2) + " WHERE tipo = ?", tipo.name());
        return getGalassieFromRS(rs);
    }

    /**
     *
     * @param tipo
     * @return
     * @throws SQLException
     */
    public ArrayList<Pianeta> getPianetiByTipo(TipoPianeta tipo) throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(1) + " WHERE tipo = ?", tipo.toString());
        return getPianetiFromRS(rs);
    }

    /**
     *
     * @param minTemp
     * @param maxTemp
     * @return
     * @throws SQLException
     */
    public ArrayList<Pianeta> getPianetiByTemperaturaRange(int minTemp, int maxTemp) throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(1) + " WHERE temperatura BETWEEN ? AND ?", minTemp, maxTemp);
        return getPianetiFromRS(rs);
    }

    /**
     *
     * @param idGalassia
     * @return
     * @throws SQLException
     */
    public ArrayList<Pianeta> getPianetiByGalassia(int idGalassia) throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(1) + " WHERE idGalassia = ?", idGalassia);
        return getPianetiFromRS(rs);
    }

    /**
     * FIX CRITICO: Stelle senza galassia. Nel DB "senza galassia" è NULL, non
     * 0. La query corretta usa "IS NULL".
     *
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList<Stella> getStelleSenzaGalassia() throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(0) + " WHERE idGalassia IS NULL");
        return getStelleFromRS(rs);
    }

    /**
     * FIX CRITICO: Pianeti senza sistema. Stesso discorso, cerchiamo NULL o
     * stringa vuota. Spesso meglio IS NULL.
     *
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList<Pianeta> getPianetiSenzaSistema() throws SQLException {
        // Cerchiamo sia NULL che stringa vuota per sicurezza
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(1) + " WHERE (idGalassia IS NULL OR idGalassia = 0) AND (sistema IS NULL OR sistema = '')");
        return getPianetiFromRS(rs);
    }

    /**
     *
     * @param idGalassia
     * @return
     * @throws SQLException
     */
    public int countStelleInGalassia(int idGalassia) throws SQLException {
        ResultSet rs = dbc.query("SELECT COUNT(*) AS totale FROM " + tab_names.get(0) + " WHERE idGalassia = ?", idGalassia);
        rs.next();
        return rs.getInt("totale");
    }

    /**
     *
     * @return @throws SQLException
     */
    public EventoCosmico getUltimoEventoCosmico() throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(3) + " ORDER BY dataEvento DESC, oraEvento DESC");
        if (rs.next()) {
            // Ricostruisco l'oggetto manualmente per evitare di chiamare metodi privati complessi se serve solo uno
            // O uso getECFromRS che ritorna lista e prendo il primo
            ArrayList<EventoCosmico> list = getECFromRS(rs);
            return list.isEmpty() ? null : list.get(0);
        } else {
            return null;
        }
    }

    /**
     *
     * @return @throws SQLException
     */
    public ArrayList<Galassia> getGalassieSenzaStelle() throws SQLException {
        // Questa query è ok, ma occhio che idGalassia <> 0 non esclude i NULL nella subquery in alcuni dialetti SQL.
        // Meglio: "idGalassia IS NOT NULL".
        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(2)
                + " WHERE idGalassia NOT IN ("
                + "SELECT DISTINCT idGalassia FROM " + tab_names.get(0)
                + " WHERE idGalassia IS NOT NULL)"
        );
        return getGalassieFromRS(rs);
    }

    // --- METODI PRIVATE DI SUPPORTO (Parsing) ---
    private ArrayList<Stella> getStelleFromRS(ResultSet rs) throws SQLException {
        ArrayList<Stella> out = new ArrayList<>();
        // In JDBC, se leggi un int che è NULL, ti dà 0.
        // Dobbiamo controllare rs.wasNull() se volessimo distinguere 0 da NULL, 
        // ma dato che il tuo modello usa int primitivo, lo 0 va bene in lettura.
        while (rs.next()) {
            int id = rs.getInt(tab_attr.get(0).get(0));
            String nome = rs.getString(tab_attr.get(0).get(1));
            String sistema = rs.getString(tab_attr.get(0).get(2));
            int temperatura = rs.getInt(tab_attr.get(0).get(3));
            String fase_str = rs.getString(tab_attr.get(0).get(4));
            int idGalassia = rs.getInt(tab_attr.get(0).get(5)); // Se DB è NULL, qui diventa 0

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
            TipoPianeta tipo = TipoPianeta.valueOf(rs.getString(tab_attr.get(1).get(3)));
            int temperatura = rs.getInt(tab_attr.get(1).get(4));
            int idGalassia = rs.getInt(tab_attr.get(1).get(5)); // Se DB è NULL, qui diventa 0

            out.add(new Pianeta(id, nome, sistema, tipo, temperatura, idGalassia));
        }
        return out;
    }

    private ArrayList<Galassia> getGalassieFromRS(ResultSet rs) throws SQLException {
        ArrayList<Galassia> out = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt(tab_attr.get(2).get(0));
            String nome = rs.getString(tab_attr.get(2).get(1));
            TipoGalassia tipo = TipoGalassia.valueOf(rs.getString("tipo"));
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
            TipoEventoCosmico tipo = TipoEventoCosmico.valueOf(rs.getString(tab_attr.get(3).get(2)));
            LocalDate data = rs.getDate(tab_attr.get(3).get(3)) != null ? rs.getDate(tab_attr.get(3).get(3)).toLocalDate() : null;
            LocalTime ora = rs.getTime(tab_attr.get(3).get(4)) != null ? rs.getTime(tab_attr.get(3).get(4)).toLocalTime() : null;
            int idStella = rs.getInt(tab_attr.get(3).get(5)); // Se DB è NULL, qui diventa 0

            out.add(new EventoCosmico(id, nome, tipo, data, ora, idStella));
        }
        return out;
    }

    // --- CONTROLLI ESISTENZA ---
    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public boolean existsStella(int id) throws SQLException {
        ResultSet rs = dbc.query("SELECT 1 FROM " + tab_names.get(0) + " WHERE " + tab_attr.get(0).get(0) + " = ?", id);
        return rs.next();
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public boolean existsPianeta(int id) throws SQLException {
        ResultSet rs = dbc.query("SELECT 1 FROM " + tab_names.get(1) + " WHERE " + tab_attr.get(1).get(0) + " = ?", id);
        return rs.next();
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public boolean existsGalassia(int id) throws SQLException {
        ResultSet rs = dbc.query("SELECT 1 FROM " + tab_names.get(2) + " WHERE " + tab_attr.get(2).get(0) + " = ?", id);
        return rs.next();
    }

    // --- ALTRE UTILITIES ---
    /**
     *
     * @return @throws SQLException
     */
    public ArrayList<Pianeta> getPianetiAbitabili() throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(1) + " WHERE temperatura BETWEEN -20 AND 50");
        return getPianetiFromRS(rs);
    }

    /**
     *
     * @param idGalassia
     * @return
     * @throws SQLException
     */
    public int countPianetiInGalassia(int idGalassia) throws SQLException {
        ResultSet rs = dbc.query("SELECT COUNT(*) AS totale FROM " + tab_names.get(1) + " WHERE idGalassia = ?", idGalassia);
        rs.next();
        return rs.getInt("totale");
    }

    /**
     *
     * @param minTemp
     * @return
     * @throws SQLException
     */
    public ArrayList<Pianeta> getPianetiByMinTemperatura(int minTemp) throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(1) + " WHERE temperatura > ?", minTemp);
        return getPianetiFromRS(rs);
    }

    /**
     *
     * @return @throws SQLException
     */
    public ArrayList<Pianeta> getPianetaPiuCaldo() throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(1) + " ORDER BY temperatura DESC");
        ArrayList<Pianeta> tmp = getPianetiFromRS(rs);
        ArrayList<Pianeta> lista = new ArrayList<>();
        if (!tmp.isEmpty()) {
            lista.add(tmp.get(0));
        }
        return lista;
    }

    /**
     * FIX CRITICO: Eventi senza stella. Anche qui, nel database il valore è
     * NULL, non 0. Quindi usiamo IS NULL.
     *
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList<EventoCosmico> getEventiSenzaStella() throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(3) + " WHERE idStella IS NULL");
        return getECFromRS(rs);
    }

    public int countStelle() throws SQLException {
        ResultSet rs = dbc.query("SELECT COUNT(*) AS totale FROM " + tab_names.get(0));
        rs.next();
        return rs.getInt("totale");
    }

    public int countPianeti() throws SQLException {
        ResultSet rs = dbc.query("SELECT COUNT(*) AS totale FROM " + tab_names.get(1));
        rs.next();
        return rs.getInt("totale");
    }

    public int countGalassie() throws SQLException {
        ResultSet rs = dbc.query("SELECT COUNT(*) AS totale FROM " + tab_names.get(2));
        rs.next();
        return rs.getInt("totale");
    }

    public int countEventiCosmici() throws SQLException {
        ResultSet rs = dbc.query("SELECT COUNT(*) AS totale FROM " + tab_names.get(3));
        rs.next();
        return rs.getInt("totale");
    }

    public ResultSet getUniversoCompleto() throws SQLException {
        String sql
                = "SELECT "
                + " g.idGalassia, g.nome AS nomeGalassia, g.tipo AS tipoGalassia, g.massa, "
                + " s.idStella, s.nome AS nomeStella, s.fase, s.temperatura AS tempStella, "
                + " p.idPianeta, p.nome AS nomePianeta, p.tipo AS tipoPianeta, p.temperatura AS tempPianeta, "
                + " e.idEventoCosmico, e.nome AS nomeEvento, e.tipo AS tipoEvento, e.dataEvento, e.oraEvento "
                + "FROM " + tab_names.get(2) + " g "
                + // Galassia
                "LEFT JOIN " + tab_names.get(0) + " s ON s.idGalassia = g.idGalassia "
                + // Stella
                "LEFT JOIN " + tab_names.get(1) + " p ON p.idGalassia = g.idGalassia "
                + // Pianeta
                "LEFT JOIN " + tab_names.get(3) + " e ON e.idStella = s.idStella "
                + // Evento
                "ORDER BY g.nome, s.nome, p.nome";

        return dbc.query(sql);
    }

    // path: logic/GestioneUniverso.java
    public ArrayList<ArrayList<String>> getEventiConContestoTable() throws SQLException {

        String sql
                = "SELECT "
                + " e.nome AS evento, "
                + " e.tipo AS tipoEvento, "
                + " s.nome AS stella, "
                + " g.nome AS galassia, "
                + " g.tipo AS tipoGalassia, "
                + " COUNT(p.idPianeta) AS numeroPianeti "
                + "FROM " + tab_names.get(3) + " e "
                + "LEFT JOIN " + tab_names.get(0) + " s ON e.idStella = s.idStella "
                + "LEFT JOIN " + tab_names.get(2) + " g ON s.idGalassia = g.idGalassia "
                + "LEFT JOIN " + tab_names.get(1) + " p ON p.idGalassia = g.idGalassia "
                + "GROUP BY e.idEventoCosmico, s.nome, g.nome, g.tipo "
                + "ORDER BY e.dataEvento DESC";

        ResultSet rs = dbc.query(sql);

        ArrayList<ArrayList<String>> table = new ArrayList<>();

        table.add(new ArrayList<>(List.of(
                "Evento", "Tipo", "Stella", "Galassia", "Tipo Galassia", "N° Pianeti"
        )));

        while (rs.next()) {

            ArrayList<String> row = new ArrayList<>();

            row.add(nvl(rs.getString("evento")));
            row.add(nvl(rs.getString("tipoEvento")));
            row.add(nvl(rs.getString("stella")));
            row.add(nvl(rs.getString("galassia")));
            row.add(nvl(rs.getString("tipoGalassia")));
            row.add(String.valueOf(rs.getInt("numeroPianeti")));

            table.add(row);
        }

        return table;
    }

    private String nvl(String value) {
        return value != null ? value : "-";
    }

}
