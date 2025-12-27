package logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import model.Galassia;
import model.Pianeta;
import model.enums.TipoPianeta;
import leolib.iodb.DBConnector;
import model.EventoCosmico;
import model.Stella;
import model.enums.FaseStella;
import model.enums.TipoEventoCosmico;
import myexceptions.ParsingException;
import utils.EnumParser;

public class GestUniverso {

    private DBConnector dbc;
    private ArrayList<String> tab_names; //Ordine di nomi di config.properties
    private ArrayList<ArrayList<String>> tab_attr;

    public GestUniverso(DBConnector dbc, ArrayList<String> tab_names, ArrayList<ArrayList<String>> tab_attr) {
        this.dbc = dbc;
        this.tab_names = tab_names;
        this.tab_attr = tab_attr;
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

    /**
     * Gets Stella ArrayList from DB.
     *
     * @return
     * @throws SQLException
     * @throws ParsingException incorrect value for fase attribute in the
     * database
     */
    public ArrayList<Stella> getStelle() throws SQLException, ParsingException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(0));
        ArrayList<Stella> out = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt(tab_attr.get(0).get(0));
            String nome = rs.getString(tab_attr.get(0).get(1));
            String sistema = rs.getString(tab_attr.get(0).get(2));
            int temperatura = rs.getInt(tab_attr.get(0).get(3));
            String fase_str = rs.getString(tab_attr.get(0).get(4));
            int idGalassia = rs.getInt(tab_attr.get(0).get(5));
            FaseStella fase = EnumParser.parseStella(fase_str);
            if (idGalassia == 0 && sistema == null) {
                //Constructor without idGalassia and sistema
                out.add(new Stella(id, nome, temperatura, fase));
            } else if (idGalassia == 0) {
                //Constructor without idGalassia
                out.add(new Stella(id, nome, sistema, temperatura, fase));
            } else if (sistema == null) {
                //Constructor without sistema
                out.add(new Stella(id, nome, temperatura, fase, idGalassia));
            } else {
                //Normal constructor
                out.add(new Stella(id, nome, sistema, temperatura, fase, idGalassia));
            }
        }
        return out;
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
            if (s.get(i).getIdGalassia() == 0) {
                dbc.update("INSERT INTO " + tab_names.get(0) + " VALUES(?,?,?,?,?,?)", s.get(i).getIdStella(), s.get(i).getNome(), s.get(i).getSistema(), s.get(i).getTemperatura(), s.get(i).getFase().toString(), null);
            } else {
                dbc.update("INSERT INTO " + tab_names.get(0) + " VALUES(?,?,?,?,?,?)", s.get(i).getIdStella(), s.get(i).getNome(), s.get(i).getSistema(), s.get(i).getTemperatura(), s.get(i).getFase().toString(), s.get(i).getIdGalassia());
            }
        }
    }

    /**
     * Gets EventoCosmico ArrayList from DB.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<EventoCosmico> getEventiCosmici() throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(1));
        ArrayList<EventoCosmico> out = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt(tab_attr.get(1).get(0));
            String nome = rs.getString(tab_attr.get(1).get(1));

            String tipoStr = rs.getString(tab_attr.get(1).get(2));
            TipoEventoCosmico tipo = TipoEventoCosmico.valueOf(tipoStr);

            LocalDate data = rs.getDate(tab_attr.get(1).get(3)).toLocalDate();
            LocalTime ora = rs.getTime(tab_attr.get(1).get(4)).toLocalTime();

            int idStella = rs.getInt(tab_attr.get(1).get(5));

            out.add(new EventoCosmico(id, nome, tipo, data, ora, idStella));
        }
        return out;
    }

    /**
     * Saves EventoCosmico ArrayList to DB.
     *
     * @param eventi
     * @throws SQLException
     */
    public void saveEventiCosmici(ArrayList<EventoCosmico> eventi) throws SQLException {
        dbc.update("DELETE FROM " + tab_names.get(1));

        for (EventoCosmico e : eventi) {
            dbc.update(
                    "INSERT INTO " + tab_names.get(1) + " VALUES (?,?,?,?,?,?)",
                    e.getIdEventoCosmico(),
                    e.getNome(),
                    e.getTipo().toString(),
                    java.sql.Date.valueOf(e.getDataEvento()),
                    java.sql.Time.valueOf(e.getOraEvento()),
                    e.getIdStella()
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

    /**
     * Saves Galassia ArrayList to DB.
     *
     * @param galassie
     * @throws SQLException
     */
    public void saveGalassie(ArrayList<Galassia> galassie) throws SQLException {
        dbc.update("DELETE FROM " + tab_names.get(2));

        for (Galassia g : galassie) {
            dbc.update(
                    "INSERT INTO " + tab_names.get(2) + " VALUES (?,?,?,?)",
                    g.getIdGalassia(),
                    g.getNome(),
                    g.getTipo(),
                    g.getMassa()
            );
        }
    }

    /**
     * Gets Pianeta ArrayList from DB.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Pianeta> getPianeti() throws SQLException {
        ResultSet rs = dbc.query("SELECT * FROM " + tab_names.get(3));
        ArrayList<Pianeta> out = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt(tab_attr.get(3).get(0));
            String nome = rs.getString(tab_attr.get(3).get(1));
            String sistema = rs.getString(tab_attr.get(3).get(2));

            String tipoStr = rs.getString(tab_attr.get(3).get(3));
            TipoPianeta tipo = TipoPianeta.valueOf(tipoStr);

            int temperatura = rs.getInt(tab_attr.get(3).get(4));
            int idGalassia = rs.getInt(tab_attr.get(3).get(5));

            if (rs.wasNull()) {
                if (sistema == null) {
                    out.add(new Pianeta(id, nome, tipo, temperatura));
                } else {
                    out.add(new Pianeta(id, nome, sistema, tipo, temperatura));
                }
            } else {
                if (sistema == null) {
                    out.add(new Pianeta(id, nome, tipo, temperatura, idGalassia));
                } else {
                    out.add(new Pianeta(id, nome, sistema, tipo, temperatura, idGalassia));
                }
            }
        }
        return out;
    }

    /**
     * Saves Pianeta ArrayList to DB.
     *
     * @param pianeti
     * @throws SQLException
     */
    public void savePianeti(ArrayList<Pianeta> pianeti) throws SQLException {
        dbc.update("DELETE FROM " + tab_names.get(3));

        for (Pianeta p : pianeti) {
            if (p.getIdGalassia() == 0) {
                dbc.update(
                        "INSERT INTO " + tab_names.get(3) + " VALUES (?,?,?,?,?,?)",
                        p.getIdPianeta(),
                        p.getNome(),
                        p.getSistema(),
                        p.getTipo().toString(),
                        p.getTemperatura(),
                        null
                );
            } else {
                dbc.update(
                        "INSERT INTO " + tab_names.get(3) + " VALUES (?,?,?,?,?,?)",
                        p.getIdPianeta(),
                        p.getNome(),
                        p.getSistema(),
                        p.getTipo().toString(),
                        p.getTemperatura(),
                        p.getIdGalassia()
                );
            }
        }
    }

    /**
     * Gets all stars with temperature greater than the given value.
     *
     * @param minTemp
     * @return
     * @throws SQLException
     * @throws ParsingException
     */
    public ArrayList<Stella> getStelleByMinTemperatura(int minTemp)
            throws SQLException, ParsingException {

        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(0) + " WHERE temperatura > ?",
                minTemp
        );

        ArrayList<Stella> out = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("idStella");
            String nome = rs.getString("nome");
            String sistema = rs.getString("sistema");
            int temperatura = rs.getInt("temperatura");
            FaseStella fase = FaseStella.valueOf(rs.getString("fase"));
            int idGalassia = rs.getInt("idGalassia");

            out.add(new Stella(id, nome, sistema, temperatura, fase, idGalassia));
        }
        return out;
    }

    /**
     * Gets all stars in the given stellar phase.
     *
     * @param fase
     * @return
     * @throws SQLException
     */
    public ArrayList<Stella> getStelleByFase(FaseStella fase) throws SQLException {

        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(0) + " WHERE fase = ?",
                fase.toString()
        );

        ArrayList<Stella> out = new ArrayList<>();
        while (rs.next()) {
            out.add(new Stella(
                    rs.getInt("idStella"),
                    rs.getString("nome"),
                    rs.getString("sistema"),
                    rs.getInt("temperatura"),
                    fase,
                    rs.getInt("idGalassia")
            ));
        }
        return out;
    }

    /**
     * Gets the hottest star in the database.
     *
     * @return
     * @throws SQLException
     * @throws ParsingException
     */
    public Stella getStellaPiuCalda() throws SQLException, ParsingException {
        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(0)
                + " ORDER BY temperatura DESC LIMIT 1"
        );

        if (rs.next()) {
            return new Stella(
                    rs.getInt("idStella"),
                    rs.getString("nome"),
                    rs.getString("sistema"),
                    rs.getInt("temperatura"),
                    FaseStella.valueOf(rs.getString("fase")),
                    rs.getInt("idGalassia")
            );
        }
        return null;
    }

    /**
     * Gets all cosmic events of the given type.
     *
     * @param tipo
     * @return
     * @throws SQLException
     */
    public ArrayList<EventoCosmico> getEventiByTipo(TipoEventoCosmico tipo)
            throws SQLException {

        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(1) + " WHERE tipo = ?",
                tipo.toString()
        );

        ArrayList<EventoCosmico> out = new ArrayList<>();
        while (rs.next()) {
            out.add(new EventoCosmico(
                    rs.getInt("idEventoCosmico"),
                    rs.getString("nome"),
                    tipo,
                    rs.getDate("dataEvento").toLocalDate(),
                    rs.getTime("oraEvento").toLocalTime(),
                    rs.getInt("idStella")
            ));
        }
        return out;
    }

    /**
     * Gets all cosmic events after the given date.
     *
     * @param data
     * @return
     * @throws SQLException
     */
    public ArrayList<EventoCosmico> getEventiDopoData(LocalDate data)
            throws SQLException {

        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(1) + " WHERE dataEvento > ?",
                java.sql.Date.valueOf(data)
        );

        ArrayList<EventoCosmico> out = new ArrayList<>();
        while (rs.next()) {
            out.add(new EventoCosmico(
                    rs.getInt("idEventoCosmico"),
                    rs.getString("nome"),
                    TipoEventoCosmico.valueOf(rs.getString("tipo")),
                    rs.getDate("dataEvento").toLocalDate(),
                    rs.getTime("oraEvento").toLocalTime(),
                    rs.getInt("idStella")
            ));
        }
        return out;
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
    public ArrayList<Galassia> getGalassieByMinMassa(int minMassa)
            throws SQLException {
        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(2) + " WHERE massa > ?",
                minMassa
        );

        ArrayList<Galassia> out = new ArrayList<>();
        while (rs.next()) {
            out.add(new Galassia(
                    rs.getInt("idGalassia"),
                    rs.getString("nome"),
                    rs.getString("tipo"),
                    rs.getInt("massa")
            ));
        }
        return out;
    }

    /**
     * Gets the most massive galaxy.
     *
     * @return
     * @throws SQLException
     */
    public Galassia getGalassiaPiuMassiccia() throws SQLException {
        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(2)
                + " ORDER BY massa DESC LIMIT 1"
        );

        if (rs.next()) {
            return new Galassia(
                    rs.getInt("idGalassia"),
                    rs.getString("nome"),
                    rs.getString("tipo"),
                    rs.getInt("massa")
            );
        }
        return null;
    }

    /**
     * Gets all galaxies of the given type.
     *
     * @param tipo
     * @return
     * @throws SQLException
     */
    public ArrayList<Galassia> getGalassieByTipo(String tipo)
            throws SQLException {

        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(2) + " WHERE tipo = ?",
                tipo
        );

        ArrayList<Galassia> out = new ArrayList<>();
        while (rs.next()) {
            out.add(new Galassia(
                    rs.getInt("idGalassia"),
                    rs.getString("nome"),
                    tipo,
                    rs.getInt("massa")
            ));
        }
        return out;
    }

    /**
     * Gets all planets of the given type.
     *
     * @param tipo
     * @return
     * @throws SQLException
     */
    public ArrayList<Pianeta> getPianetiByTipo(TipoPianeta tipo)
            throws SQLException {

        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(3) + " WHERE tipo = ?",
                tipo.toString()
        );

        ArrayList<Pianeta> out = new ArrayList<>();
        while (rs.next()) {
            out.add(new Pianeta(
                    rs.getInt("idPianeta"),
                    rs.getString("nome"),
                    rs.getString("sistema"),
                    tipo,
                    rs.getInt("temperatura"),
                    rs.getInt("idGalassia")
            ));
        }
        return out;
    }

    /**
     * Gets all planets with temperature between two values.
     *
     * @param minTemp
     * @param maxTemp
     * @return
     * @throws SQLException
     */
    public ArrayList<Pianeta> getPianetiByTemperaturaRange(
            int minTemp, int maxTemp) throws SQLException {

        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(3)
                + " WHERE temperatura BETWEEN ? AND ?",
                minTemp, maxTemp
        );

        ArrayList<Pianeta> out = new ArrayList<>();
        while (rs.next()) {
            out.add(new Pianeta(
                    rs.getInt("idPianeta"),
                    rs.getString("nome"),
                    rs.getString("sistema"),
                    TipoPianeta.valueOf(rs.getString("tipo")),
                    rs.getInt("temperatura"),
                    rs.getInt("idGalassia")
            ));
        }
        return out;
    }

    /**
     * Gets all planets belonging to the given galaxy.
     *
     * @param idGalassia
     * @return
     * @throws SQLException
     */
    public ArrayList<Pianeta> getPianetiByGalassia(int idGalassia)
            throws SQLException {

        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(3) + " WHERE idGalassia = ?",
                idGalassia
        );

        ArrayList<Pianeta> out = new ArrayList<>();
        while (rs.next()) {
            out.add(new Pianeta(
                    rs.getInt("idPianeta"),
                    rs.getString("nome"),
                    rs.getString("sistema"),
                    TipoPianeta.valueOf(rs.getString("tipo")),
                    rs.getInt("temperatura"),
                    idGalassia
            ));
        }
        return out;
    }

    /**
     * Gets all stars not associated with any galaxy.
     *
     * @return
     * @throws SQLException
     * @throws ParsingException
     */
    public ArrayList<Stella> getStelleSenzaGalassia()
            throws SQLException, ParsingException {

        ResultSet rs = dbc.query(
                "SELECT * FROM " + tab_names.get(0) + " WHERE idGalassia IS NULL"
        );

        ArrayList<Stella> out = new ArrayList<>();
        while (rs.next()) {
            out.add(new Stella(
                    rs.getInt("idStella"),
                    rs.getString("nome"),
                    rs.getString("sistema"),
                    rs.getInt("temperatura"),
                    FaseStella.valueOf(rs.getString("fase"))
            ));
        }
        return out;
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

        ArrayList<Pianeta> out = new ArrayList<>();
        while (rs.next()) {
            out.add(new Pianeta(
                    rs.getInt("idPianeta"),
                    rs.getString("nome"),
                    TipoPianeta.valueOf(rs.getString("tipo")),
                    rs.getInt("temperatura")
            ));
        }
        return out;
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
                + " ORDER BY dataEvento DESC, oraEvento DESC LIMIT 1"
        );

        if (rs.next()) {
            return new EventoCosmico(
                    rs.getInt("idEventoCosmico"),
                    rs.getString("nome"),
                    TipoEventoCosmico.valueOf(rs.getString("tipo")),
                    rs.getDate("dataEvento").toLocalDate(),
                    rs.getTime("oraEvento").toLocalTime(),
                    rs.getInt("idStella")
            );
        }
        return null;
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

        ArrayList<Galassia> out = new ArrayList<>();
        while (rs.next()) {
            out.add(new Galassia(
                    rs.getInt("idGalassia"),
                    rs.getString("nome"),
                    rs.getString("tipo"),
                    rs.getInt("massa")
            ));
        }
        return out;
    }

}
