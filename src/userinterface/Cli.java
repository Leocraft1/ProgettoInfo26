package userinterface;

import java.io.IOException;
import java.sql.SQLException;
import leolib.ioconsole.ConsoleRead;
import leolib.iodb.DBConnector;
import leolib.iodb.PropertiesRead;
import logic.GestUniverso;
import model.Galassia;
import model.Pianeta;
import model.Stella;
import model.enums.FaseStella;
import model.enums.TipoPianeta;
import myexceptions.ConnectionException;
import myexceptions.DuplicateException;

public class Cli {

    GestUniverso gu = new GestUniverso();

    public Cli() throws ConnectionException {
        try {
            String[] info = PropertiesRead.readBaseInfo(gu.getCfg_path());
            DBConnector dbc = new DBConnector(info[2], info[3], info[4]);
            if (!dbc.testConnection()) {
                throw new ConnectionException();
            }
            gu.setDbc(dbc);
        } catch (IOException e) {
            System.out.println("Errore di lettura del file config.properties: manca il file o l'applicazione non ha i permessi di lettura.");
        } catch (SQLException e) {
            System.out.println("Errore di creazione di DBConnector.");
        }
    }

    private void inserisciStella() throws SQLException, DuplicateException {
        System.out.print("ID: ");
        int id = ConsoleRead.readPositiveInt();

        System.out.print("Nome: ");
        String nome = ConsoleRead.readNotBlankString();

        System.out.print("Sistema: ");
        String sistema = ConsoleRead.readNotBlankString();

        System.out.print("Temperatura: ");
        int temp = ConsoleRead.readPositiveInt();

        stampaFasiStella();
        System.out.print("Fase: ");
        FaseStella fase = FaseStella.valueOf(ConsoleRead.readNotBlankString());

        System.out.print("ID Galassia: ");
        int idGal = ConsoleRead.readPositiveInt();

        gu.addStella(new Stella(id, nome, sistema, temp, fase, idGal));
        System.out.println("Stella inserita");
    }

    private void inserisciPianeta() throws SQLException, DuplicateException {
        System.out.print("ID: ");
        int id = ConsoleRead.readPositiveInt();

        System.out.print("Nome: ");
        String nome = ConsoleRead.readNotBlankString();

        System.out.print("Sistema: ");
        String sistema = ConsoleRead.readNotBlankString();

        stampaTipiPianeta();
        System.out.print("Tipo: ");
        TipoPianeta tipo = TipoPianeta.valueOf(ConsoleRead.readNotBlankString());

        System.out.print("Temperatura: ");
        int temp = ConsoleRead.readPositiveInt();

        System.out.print("ID Galassia: ");
        int idGal = ConsoleRead.readPositiveInt();

        gu.addPianeta(new Pianeta(id, nome, sistema, tipo, temp, idGal));
        System.out.println("Pianeta inserito");
    }

    private void inserisciGalassia() throws SQLException, DuplicateException {
        System.out.print("ID: ");
        int id = ConsoleRead.readPositiveInt();

        System.out.print("Nome: ");
        String nome = ConsoleRead.readNotBlankString();

        System.out.print("Tipo: ");
        String tipo = ConsoleRead.readNotBlankString();

        System.out.print("Massa: ");
        int massa = ConsoleRead.readPositiveInt();

        gu.addGalassia(new Galassia(id, nome, tipo, massa));
        System.out.println("Galassia inserita");
    }

    // ================= SUPPORTO ENUM =================
    private void stampaFasiStella() {
        System.out.println("Fasi disponibili:");
        for (FaseStella f : FaseStella.values()) {
            System.out.println("- " + f);
        }
    }

    private void stampaTipiPianeta() {
        System.out.println("Tipi disponibili:");
        for (TipoPianeta t : TipoPianeta.values()) {
            System.out.println("- " + t);
        }
    }
}
