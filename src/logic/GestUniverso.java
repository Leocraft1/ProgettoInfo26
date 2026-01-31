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

    public GestUniverso() {}

    public GestUniverso(DBConnector dbc) throws IOException {
        this.dbc = dbc;
        this.loadProperties();
    }

    private void loadProperties() throws IOException {
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
    
    //GET
    public ArrayList<Stella> getStelle() throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(0));
        return getStelleFromRS(rs);
    }

    public ArrayList<Pianeta> getPianeti() throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(1));
        return getPianetiFromRS(rs);
    }

    public ArrayList<Galassia> getGalassie() throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(2));
        return getGalassieFromRS(rs);
    }

    public ArrayList<EventoCosmico> getEventiCosmici() throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(3));
        return getECFromRS(rs);
    }

    //CREATE
    public void addStella(Stella s) throws SQLException, DuplicateException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(0) + " WHERE " + tab_attr.get(0).get(0) + " = ?", s.getIdStella());
        if (rs.next()) {
            throw new DuplicateException("Stella gia' presente con ID: " + s.getIdStella());
        }
        Integer idGal;
        if(s.getIdGalassia()==0) {
        	idGal = null;
        }else {
        	idGal = Integer.valueOf(s.getIdGalassia());
        }
        dbc.update("INSERT INTO " + tab_names.get(0) + " VALUES(?,?,?,?,?,?)",
                s.getIdStella(),
                s.getNome(),
                s.getSistema(),
                s.getTemperatura(),
                s.getFase().toString(),
                idGal);
    }

    public void addPianeta(Pianeta p) throws SQLException, DuplicateException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(1) + " WHERE " + tab_attr.get(1).get(0) + " = ?", p.getIdPianeta());
        if (rs.next()) {
            throw new DuplicateException("Pianeta gia' presente con ID: " + p.getIdPianeta());
        }
        Integer idGal;
        if(p.getIdGalassia()==0) {
        	idGal = null;
        }else{
        	idGal = Integer.valueOf(p.getIdGalassia());
        }
        dbc.update("INSERT INTO " + tab_names.get(1) + " VALUES (?,?,?,?,?,?)",
                p.getIdPianeta(),
                p.getNome(),
                p.getSistema(),
                p.getTipo().toString(),
                p.getTemperatura(),
                idGal
        );
    }

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

    public void addEventoCosmico(EventoCosmico e) throws SQLException, DuplicateException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(3) + " WHERE " + tab_attr.get(3).get(0) + " = ?", e.getIdEventoCosmico());
        if (rs.next()) {
            throw new DuplicateException("Evento gia' presente con ID: " + e.getIdEventoCosmico());
        }
        Integer idStella;
        if(e.getIdStella()==0) {
        	idStella = null;
        }else{
        	idStella = Integer.valueOf(e.getIdStella());
        }
        dbc.update("INSERT INTO " + tab_names.get(3) + " VALUES (?,?,?,?,?,?)",
                e.getIdEventoCosmico(),
                e.getNome(),
                e.getTipo().toString(),
                e.getDataEvento(),
                e.getOraEvento(),
                idStella);
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
    public ArrayList<Stella> getStelleByMinTemperatura(int minTemp) throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(0) + " WHERE temperatura > ? ORDER BY temperatura", minTemp);
        return getStelleFromRS(rs);
    }

    public ArrayList<Stella> getStelleByFase(FaseStella fase) throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(0) + " WHERE fase = ?", fase.toString());
        return getStelleFromRS(rs);
    }

    public ArrayList<Stella> getStellaPiuCalda() throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(0) + " ORDER BY temperatura DESC");
        ArrayList<Stella> all = getStelleFromRS(rs);
        ArrayList<Stella> top = new ArrayList<>();
        if (!all.isEmpty()) {
            top.add(all.get(0));
        }
        return top;
    }

    public ArrayList<EventoCosmico> getEventiByTipo(TipoEventoCosmico tipo) throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(3) + " WHERE tipo = ?", tipo.toString());
        return getECFromRS(rs);
    }

    public ArrayList<EventoCosmico> getEventiDopoData(LocalDate data) throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(3) + " WHERE dataEvento > ?", data);
        return getECFromRS(rs);
    }

    public int countEventiByStella(int idStella) throws SQLException {
        ResultSet rs = dbc.query("SELECT COUNT(*) AS totale FROM " + tab_names.get(3) + " WHERE idStella = ?", idStella);
        rs.next();
        return rs.getInt("totale");
    }

    public ArrayList<Galassia> getGalassieByMinMassa(int minMassa) throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(2) + " WHERE massa > ?", minMassa);
        return getGalassieFromRS(rs);
    }

    public ArrayList<Galassia> getGalassiaPiuPesante() throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(2) + " ORDER BY massa DESC");
        ArrayList<Galassia> all = getGalassieFromRS(rs);
        ArrayList<Galassia> top = new ArrayList<>();
        if (!all.isEmpty()) {
            top.add(all.get(0));
        }
        return top;
    }

    public ArrayList<Galassia> getGalassieByTipo(TipoGalassia tipo) throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(2) + " WHERE tipo = ?", tipo.name());
        return getGalassieFromRS(rs);
    }

    public ArrayList<Pianeta> getPianetiByTipo(TipoPianeta tipo) throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(1) + " WHERE tipo = ?", tipo.toString());
        return getPianetiFromRS(rs);
    }

    public ArrayList<Pianeta> getPianetiByTemperaturaRange(int minTemp, int maxTemp) throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(1) + " WHERE temperatura BETWEEN ? AND ?", minTemp, maxTemp);
        return getPianetiFromRS(rs);
    }

    public ArrayList<Pianeta> getPianetiByGalassia(int idGalassia) throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(1) + " WHERE idGalassia = ?", idGalassia);
        return getPianetiFromRS(rs);
    }

    public ArrayList<Stella> getStelleSenzaGalassia() throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(0) + " WHERE idGalassia IS NULL");
        return getStelleFromRS(rs);
    }

    public ArrayList<Pianeta> getPianetiSenzaSistema() throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(1) + " WHERE (idGalassia IS NULL OR idGalassia = 0) AND (sistema IS NULL OR sistema = '')");
        return getPianetiFromRS(rs);
    }

    public int countStelleInGalassia(int idGalassia) throws SQLException {
        ResultSet rs = dbc.query("SELECT COUNT(*) AS totale FROM " + tab_names.get(0) + " WHERE idGalassia = ?", idGalassia);
        rs.next();
        return rs.getInt("totale");
    }

    public EventoCosmico getUltimoEventoCosmico() throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(3) + " ORDER BY dataEvento DESC, oraEvento DESC");
        if (rs.next()) {
            ArrayList<EventoCosmico> list = getECFromRS(rs);
            return list.get(0);
        } else {
            return null;
        }
    }

    public ArrayList<Galassia> getGalassieSenzaStelle() throws SQLException {
        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(2)
                + " WHERE idGalassia NOT IN ("
                + "SELECT DISTINCT idGalassia FROM " + tab_names.get(0)
                + " WHERE idGalassia IS NOT NULL)"
        );
        return getGalassieFromRS(rs);
    }
    

    public ArrayList<Pianeta> getPianetiAbitabili() throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(1) + " WHERE temperatura BETWEEN -20 AND 50");
        return getPianetiFromRS(rs);
    }


    public int countPianetiInGalassia(int idGalassia) throws SQLException {
        ResultSet rs = dbc.query("SELECT COUNT(*) AS totale FROM " + tab_names.get(1) + " WHERE idGalassia = ?", idGalassia);
        rs.next();
        return rs.getInt("totale");
    }

    public ArrayList<Pianeta> getPianetiByMinTemperatura(int minTemp) throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(1) + " WHERE temperatura > ?", minTemp);
        return getPianetiFromRS(rs);
    }

    public ArrayList<Pianeta> getPianetaPiuCaldo() throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(1) + " ORDER BY temperatura DESC");
        ArrayList<Pianeta> tmp = getPianetiFromRS(rs);
        ArrayList<Pianeta> lista = new ArrayList<>();
        if (!tmp.isEmpty()) {
            lista.add(tmp.get(0));
        }
        return lista;
    }

    public ArrayList<EventoCosmico> getEventiSenzaStella() throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(3) + " WHERE idStella IS NULL");
        return getECFromRS(rs);
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
                "Evento", "Tipo", "Stella", "Galassia", "Tipo Galassia", "N Pianeti"
        )));

        while (rs.next()) {
            ArrayList<String> row = new ArrayList<>();
            row.add(rs.getString("evento"));
            row.add(rs.getString("tipoEvento"));
            row.add(rs.getString("stella"));
            row.add(rs.getString("galassia"));
            row.add(rs.getString("tipoGalassia"));
            row.add(String.valueOf(rs.getInt("numeroPianeti")));

            table.add(row);
        }
        return table;
    }

    //GET FROM RS PRIVATE METHODS
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
        dbc.close();
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
            int idGalassia = rs.getInt(tab_attr.get(1).get(5));

            out.add(new Pianeta(id, nome, sistema, tipo, temperatura, idGalassia));
        }
        dbc.close();
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
        dbc.close();
        return out;
    }

    private ArrayList<EventoCosmico> getECFromRS(ResultSet rs) throws SQLException {
        ArrayList<EventoCosmico> out = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt(tab_attr.get(3).get(0));
            String nome = rs.getString(tab_attr.get(3).get(1));
            TipoEventoCosmico tipo = TipoEventoCosmico.valueOf(rs.getString(tab_attr.get(3).get(2)));
            LocalDate data = rs.getDate(tab_attr.get(3).get(3)).toLocalDate();
            LocalTime ora = rs.getTime(tab_attr.get(3).get(4)).toLocalTime();
            int idStella = rs.getInt(tab_attr.get(3).get(5));

            out.add(new EventoCosmico(id, nome, tipo, data, ora, idStella));
        }
        dbc.close();
        return out;
    }
}
