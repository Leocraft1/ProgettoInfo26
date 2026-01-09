package userinterface;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import leolib.internalexceptions.InvalidSeparatorException;
import leolib.ioconsole.ConsolePrint;
import leolib.ioconsole.ConsoleRead;
import leolib.iodb.DBConnector;
import leolib.iodb.PropertiesRead;
import logic.GestUniverso;
import model.EventoCosmico;
import model.Galassia;
import model.Pianeta;
import model.Stella;
import model.enums.FaseStella;
import model.enums.TipoEventoCosmico;
import model.enums.TipoPianeta;
import myexceptions.DuplicateException;
import utils.TableFormatter;

public class Cli {

    private GestUniverso g = new GestUniverso();
    String[] app_info = getAppInfo();
    private String app_name = app_info[0];

    public Cli() {
        try {
            String[] info = PropertiesRead.readBaseInfo(g.getCfg_path());
            g = new GestUniverso(new DBConnector(info[2], info[3], info[4]));
        } catch (IOException e) {
            System.out.println("Errore di lettura del file config.properties.");
        } catch (SQLException e) {
            System.out.println("Errore di creazione DBConnector.");
        }
    }

    public GestUniverso getGest() {
        return g;
    }

    public void startMessage() {
        System.out.println("\n--- INIZIALIZZAZIONE MISSIONE RAPIDA ---");
        pausa(700);

        // --- FASE 1: Conto alla rovescia ---
        System.out.print("Conto alla rovescia: 3...");
        pausa(700);
        System.out.print(" 2...");
        pausa(700);
        System.out.println(" 1...");
        pausa(700);
        System.out.println(">>> MOTORI AL MASSIMO! DECOLLO! <<<");

        // Razzo LLCH
        String razzo
                = "        ^\n"
                + "       / \\\n"
                + "      | L |\n"
                + "      | L |\n"
                + "      | C |\n"
                + "      | H |\n"
                + "     /|   |\\\n"
                + "    * * * * *"
                + "      * * *"
                + "       * *"
                + "        *";

        // --- FASE 2: Lancio ---
        for (int i = 0; i < 5; i++) {
            System.out.print("\n\n\n\n\n\n\n\n\n\n");

            for (int spazio = 0; spazio < i * 2; spazio++) {
                System.out.println("");
            }
            System.out.println(razzo);
            System.out.println("...LLCH IN ASCESA...");
            pausa(350);
        }

        // --- FASE 3: Arrivo a Saturno ---
        pausa(1000);
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        System.out.println("                  _______________________                  ");
        System.out.println("           ________---                   ---________        AGGUANCIO RIUSCITO!");
        System.out.println("      ____---           _____________           ---____");
        System.out.println("   __--               .-\"             \"-.               --__");
        System.out.println("  /                 .'     S A T U R N O    '.                 \\");
        System.out.println(" |                 /                           \\                 |");
        System.out.println("  \\               '.                         .'                 /");
        System.out.println("   --__              '-._                  _.-'               __--");
        System.out.println("        ---____              \"\"\"\"\"\"\"\"\"\"\"              ____---");
        System.out.println("                ---___________         ___________---");
        System.out.println("                            -----------------");
        System.out.println("                     === ANELLI DI SATURNO ===");
        System.out.println("              =======---------------------------=======");
        System.out.println("---------------------------------------------");

        pausa(1600);
        System.out.println("Benvenuto in: " + app_name.toUpperCase());
    }

// --- Metodo di supporto PAUSA (se ce l'hai già nella classe, non ricopiarlo) ---
    private void pausa(int millisecondi) {
        try {
            Thread.sleep(millisecondi);
        } catch (InterruptedException e) {
            // Ignora eccezioni
        }
    }

    public void testDBConnection() {
        Scanner keyb = new Scanner(System.in);
        do {
            if (g.getDbc().testConnection()) {
                System.out.println("Connessione al database riuscita!");
                break;
            } else {
                System.out.println("==========================================================");
                System.out.println("ERRORE! Database non connesso! Controlla che: \n"
                        + "- Il server sia acceso\n"
                        + "- Le credenziali siano corrette\n"
                        + "- L'utente abbia accesso al database");
                System.out.println("==========================================================");
                System.out.print("Premi invio per riprovare...");
                keyb.nextLine();
            }
        } while (true);
    }

    public void printMainMenuPage() {

        System.out.println("############## MENU' PRINCIPALE ##############");
        System.out.println("Seleziona un'opzione:");
        System.out.println("");
        System.out.println("1) Menu' stelle");
        System.out.println("2) Menu' pianeti");
        System.out.println("3) Menu' galassie");
        System.out.println("4) Menu' eventi cosmici");
        System.out.println("");
        System.out.println("0) Esci");
        System.out.println("##############################################");
        System.out.println("Opzione:");
    }

    public void printStelleMenuPage() {
        System.out.println("*************** MENU' STELLE *****************");
        System.out.println("Seleziona un'opzione:");
        System.out.println("");
        System.out.println("1) Lista stelle");
        System.out.println("2) Crea stella");
        System.out.println("3) Rimuovi stella");
        System.out.println("");
        System.out.println("0) Esci");
        System.out.println("**********************************************");
        System.out.println("Opzione:");
    }

    public void printPianetiMenuPage() {
        System.out.println("<<<<<<<<<<<<<<<< MENU' PIANETI >>>>>>>>>>>>>>>>");
        System.out.println("Seleziona un'opzione:");
        System.out.println("");
        System.out.println("1) Lista pianeti");
        System.out.println("2) Crea pianeta");
        System.out.println("3) Rimuovi pianeta");
        System.out.println("");
        System.out.println("0) Esci");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println("Opzione:");
    }

    public void printGalassieMenuPage() {
        System.out.println("================ MENU' GALASSIE ================");
        System.out.println("Seleziona un'opzione:");
        System.out.println("");
        System.out.println("1) Lista galassie");
        System.out.println("2) Crea galassia");
        System.out.println("3) Rimuovi galassia");
        System.out.println("");
        System.out.println("0) Esci");
        System.out.println("==============================================");
        System.out.println("Opzione:");
    }

    public void printEventoCosmicoMenuPage() {
        System.out.println("++++++++++++ MENU' EVENTI COSMICI ++++++++++++");
        System.out.println("Seleziona un'opzione:");
        System.out.println("");
        System.out.println("1) Lista eventi cosmici");
        System.out.println("2) Crea evento cosmico");
        System.out.println("3) Rimuovi evento cosmico");
        System.out.println("");
        System.out.println("0) Esci");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Opzione:");
    }

    // ================= INSERIMENTIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII =================
    public void addStella() {
        do {
            System.out.print("ID: ");
            int id = ConsoleRead.readIntGreaterThan(0);

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

            try {
                g.addStella(new Stella(id, nome, sistema, temp, fase, idGal));
            } catch (SQLException e) {
                System.out.println("Errore di comunicazione con il database.");
            } catch (DuplicateException e) {
                System.out.println("E' gia' presente una stella con l'id " + id);
            }
            System.out.println("Stella inserita");
            return;
        } while (true);
    }

    public void addPianeta() {
        do {
            System.out.print("ID: ");
            int id = ConsoleRead.readIntGreaterThan(0);

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

            try {
                g.addPianeta(new Pianeta(id, nome, sistema, tipo, temp, idGal));
            } catch (SQLException e) {
                System.out.println("Errore di comunicazione con il database.");
            } catch (DuplicateException e) {
                System.out.println("E' gia' presente un pianeta con l'id " + id);
            }
            System.out.println("Pianeta inserito");
            return;
        } while (true);
    }

    public void addGalassia() throws SQLException, DuplicateException {
        do {
            System.out.print("ID: ");
            int id = ConsoleRead.readIntGreaterThan(0);

            System.out.print("Nome: ");
            String nome = ConsoleRead.readNotBlankString();

            System.out.print("Tipo: ");
            String tipo = ConsoleRead.readNotBlankString();

            System.out.print("Massa: ");
            int massa = ConsoleRead.readPositiveInt();

            try {
                g.addGalassia(new Galassia(id, nome, tipo, massa));
            } catch (SQLException e) {
                System.out.println("Errore di comunicazione con il database.");
            } catch (DuplicateException e) {
                System.out.println("E' gia' presente una galassia con l'id " + id);
            }
            System.out.println("Galassia inserita");
            return;
        } while (true);
    }

    public void addEventoCosmico() throws SQLException, DuplicateException {
        do {
            System.out.print("ID Evento: ");
            int id = ConsoleRead.readIntGreaterThan(0);

            System.out.print("Nome: ");
            String nome = ConsoleRead.readNotBlankString();

            stampaTipiEventoCosmico();
            System.out.print("Tipo: ");
            TipoEventoCosmico tipo = TipoEventoCosmico.valueOf(ConsoleRead.readNotBlankString());

            System.out.print("Data (YYYY-MM-DD): ");
            LocalDate data = LocalDate.parse(ConsoleRead.readNotBlankString());

            System.out.print("Ora (HH:MM): ");
            LocalTime ora = LocalTime.parse(ConsoleRead.readNotBlankString());

            System.out.print("ID Stella: ");
            int idStella = ConsoleRead.readPositiveInt();

            try {
                g.addEventoCosmico(new EventoCosmico(id, nome, tipo, data, ora, idStella));
            } catch (SQLException e) {
                System.out.println("Errore di comunicazione con il database.");
            } catch (DuplicateException e) {
                System.out.println("E' gia' presente un'evento cosmico con l'id " + id);
            }
            System.out.println("Evento cosmico inserito");
            return;
        } while (true);
    }

    // ================= RIMOZIONIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII =================
    private boolean confermaEliminazione() {
        System.out.print("Sei sicuro di voler eliminare? (S/N): ");
        String s = ConsoleRead.readNotBlankString();
        return s.equalsIgnoreCase("S");
    }

    public void removeStella() {
        System.out.print("ID Stella da rimuovere: ");
        int id = ConsoleRead.readIntGreaterThan(0);

        if (!confermaEliminazione()) {
            System.out.println("Operazione annullata.");
            return;
        }

        try {
            int righe = g.deleteStella(id);
            if (righe > 0) {
                System.out.println("Stella rimossa");
            } else {
                System.out.println("Stella non trovata");
            }
        } catch (SQLException e) {
            System.out.println("Errore di comunicazione con il database.");
        }
    }

    public void removePianeta() {
        System.out.print("ID Pianeta da rimuovere: ");
        int id = ConsoleRead.readIntGreaterThan(0);

        if (!confermaEliminazione()) {
            System.out.println("Operazione annullata.");
            return;
        }

        try {
            int righe = g.deletePianeta(id);
            if (righe > 0) {
                System.out.println("Pianeta rimosso");
            } else {
                System.out.println("Pianeta non trovato");
            }
        } catch (SQLException e) {
            System.out.println("Errore di comunicazione con il database.");
        }
    }

    public void removeGalassia() {
        System.out.print("ID Galassia da rimuovere: ");
        int id = ConsoleRead.readIntGreaterThan(0);

        if (!confermaEliminazione()) {
            System.out.println("Operazione annullata.");
            return;
        }

        try {
            int righe = g.deleteGalassia(id);
            if (righe > 0) {
                System.out.println("Galassia rimossa");
            } else {
                System.out.println("Galassia non trovata");
            }
        } catch (SQLException e) {
            System.out.println("Errore di comunicazione con il database.");
        }
    }

    public void removeEventoCosmico() {
        System.out.print("ID Evento Cosmico da rimuovere: ");
        int id = ConsoleRead.readIntGreaterThan(0);

        if (!confermaEliminazione()) {
            System.out.println("Operazione annullata.");
            return;
        }

        try {
            int righe = g.deleteEventoCosmico(id);
            if (righe > 0) {
                System.out.println("Evento rimosso");
            } else {
                System.out.println("Evento non trovato");
            }
        } catch (SQLException e) {
            System.out.println("Errore di comunicazione con il database.");
        }
    }

    // ================= VISUALIZZAZIONEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE =================
    public void listStelle() {
        try {
            ArrayList<Stella> stelle = g.getStelle();
            if (stelle.isEmpty()) {
                System.out.println("Nessuna stella presente");
                return;
            }
            ConsolePrint.printTable(TableFormatter.parseStelleToTable(stelle, g.getTabAttr()), "*");
        } catch (SQLException e) {
            System.out.println("Errore di comunicazione con il database.");
        } catch (InvalidSeparatorException e) {
        }
    }

    public void listPianeti() {
        try {
            ArrayList<Pianeta> pianeti = g.getPianeti();
            if (pianeti.isEmpty()) {
                System.out.println("Nessun pianeta presente");
                return;
            }
            ConsolePrint.printTable(TableFormatter.parsePianetiToTable(pianeti, g.getTabAttr()), "*");
        } catch (SQLException e) {
            System.out.println("Errore di comunicazione con il database.");
        } catch (InvalidSeparatorException e) {
        }
    }

    public void listGalassie() {
        try {
            ArrayList<Galassia> galassie = g.getGalassie();
            if (galassie.isEmpty()) {
                System.out.println("Nessuna galassia presente");
                return;
            }
            ConsolePrint.printTable(TableFormatter.parseGalassieToTable(galassie, g.getTabAttr()), "*");
        } catch (SQLException e) {
            System.out.println("Errore di comunicazione con il database.");
        } catch (InvalidSeparatorException e) {
        }
    }

    public void listEventiCosmici() {
        try {
            ArrayList<EventoCosmico> eventi = g.getEventiCosmici();
            if (eventi.isEmpty()) {
                System.out.println("Nessun evento presente");
                return;
            }
            ConsolePrint.printTable(TableFormatter.parseECToTable(eventi, g.getTabAttr()), "*");
        } catch (SQLException e) {
            System.out.println("Errore di comunicazione con il database.");
        } catch (InvalidSeparatorException e) {
        }
    }

    // ================= SUPPORTO ENUMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM =================
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

    private void stampaTipiEventoCosmico() {
        System.out.println("Tipi disponibili:");
        for (TipoEventoCosmico t : TipoEventoCosmico.values()) {
            System.out.println("- " + t);
        }
    }

    private String[] getAppInfo() {
        try {
            return PropertiesRead.readBaseInfo(g.getCfg_path());
        } catch (IOException e) {
            System.out.println("Errore di lettura del file config.properties.");
            return null;
        }
    }
}
