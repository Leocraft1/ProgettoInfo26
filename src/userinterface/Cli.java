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

    public void start() throws SQLException {
        menuPrincipale();
    }
    // ================= MENU PRINCIPALE =================

    private void menuPrincipale() throws SQLException {
        int scelta;
        do {
            System.out.println("\n=== UNIVERSO DB ===");
            System.out.println("1) Visualizza");
            System.out.println("2) Inserisci");
            System.out.println("3) Elimina");
            System.out.println("0) Esci");
            System.out.print("Scelta: ");

            scelta = ConsoleRead.readInt();

            switch (scelta) {
                case 1 ->
                    menuVisualizza();
                case 2 ->
                    menuInserisci();
                case 3 ->
                    menuElimina();
                case 0 ->
                    System.out.println("Arrivederci 🚀");
                default ->
                    System.out.println("Scelta non valida");
            }
        } while (scelta != 0);
    }

    // ================= VISUALIZZA =================
    private void menuVisualizza() throws SQLException {
        int s;
        do {
            System.out.println("\n--- VISUALIZZA ---");
            System.out.println("1) Stelle");
            System.out.println("2) Pianeti");
            System.out.println("3) Galassie");
            System.out.println("0) Indietro");
            System.out.print("Scelta: ");

            s = ConsoleRead.readInt();

            switch (s) {
                case 1 ->
                    gu.getStelle().forEach(System.out::println);
                case 2 ->
                    gu.getPianeti().forEach(System.out::println);
                case 3 ->
                    gu.getGalassie().forEach(System.out::println);
            }
        } while (s != 0);
    }

    // ================= INSERISCI =================
    private void menuInserisci() {
        int s;
        do {
            System.out.println("\n--- INSERISCI ---");
            System.out.println("1) Stella");
            System.out.println("2) Pianeta");
            System.out.println("3) Galassia");
            System.out.println("0) Indietro");
            System.out.print("Scelta: ");

            s = ConsoleRead.readInt();

            try {
                switch (s) {
                    case 1 ->
                        inserisciStella();
                    case 2 ->
                        inserisciPianeta();
                    case 3 ->
                        inserisciGalassia();
                }
            } catch (Exception e) {
                System.out.println("Errore inserimento");
            }
        } while (s != 0);
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

    // ================= ELIMINA =================
    private void menuElimina() throws SQLException {
        int s;
        do {
            System.out.println("\n--- ELIMINA ---");
            System.out.println("1) Stella");
            System.out.println("2) Pianeta");
            System.out.println("3) Galassia");
            System.out.println("0) Indietro");
            System.out.print("Scelta: ");

            s = ConsoleRead.readInt();

            if (s != 0) {
                System.out.print("ID: ");
                int id = ConsoleRead.readPositiveInt();

                switch (s) {
                    case 1 ->
                        gu.deleteStella(id);
                    case 2 ->
                        gu.deletePianeta(id);
                    case 3 ->
                        gu.deleteGalassia(id);
                }
                System.out.println("Eliminazione completata");
            }
        } while (s != 0);
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
