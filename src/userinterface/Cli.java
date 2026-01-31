package userinterface;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import leolib.dtformatters.IT;

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
import model.enums.TipoGalassia;
import model.enums.TipoPianeta;
import myexceptions.DuplicateException;
import utils.TableFormatter;
import java.sql.ResultSet;

public class Cli {
    private GestUniverso g = new GestUniverso();
    private String[] app_info = getAppInfo();
    private String app_name = app_info[0];

    public Cli() {
        try {
            g = new GestUniverso(new DBConnector(app_info[2], app_info[3], app_info[4]));
            this.testDBConnection();
        } catch (IOException e) {
            System.out.println("Errore di lettura del file config.properties.");
        } catch (SQLException e) {
            System.out.println("Errore di creazione DBConnector.");
        }
    }

    public GestUniverso getGest() {
        return g;
    }

    private void testDBConnection() {
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
    
    public void startMessage() {
        if (app_info[5].equals("true")) {
            System.out.println("--- INIZIALIZZAZIONE MISSIONE RAPIDA ---");
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
                    + "    * * * * *\n"
                    + "      * * *\n"
                    + "       * *\n"
                    + "        *\n";

            // --- FASE 2: Lancio ---
            System.out.print("\n\n\n\n\n\n\n\n\n\n");
            System.out.println("LLCH IN ASCESA...");
            System.out.println(razzo);
            pausa(350);
            for (int i = 0; i < 5; i++) {
                for (int spazio = 0; spazio < i * 2; spazio++) {
                    System.out.println("");
                }
                pausa(350);
            }
            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

            // --- FASE 3: Arrivo a Saturno ---
            pausa(1000);

            System.out.println("                  _______________________                  ");
            System.out.println("           ________---                   ---________        AGGANCIO RIUSCITO!");
            System.out.println("      ____---           _____________           ---____");
            System.out.println("   __--               .-\"             \"-.               --__");
            System.out.println("  /                 .'     S A T U R N O    '.                 \\");
            System.out.println(" |                 /                           \\                 |");
            System.out.println("  \\               '.                         .'                 /");
            System.out.println("   --__              '-._                  _.-'               __--");
            System.out.println("        ---____              \"\"\"\"\"\"\"\"\"\"\"              ____---");
            System.out.println("                ---___________         ___________---");
            System.out.println("                            -----------------");
            System.out.println("");
            System.out.println("---------------------------------------------");
        }
        System.out.println("Benvenuto in: " + app_name.toUpperCase());
    }
    
    private void printMainMenuPage() {
        System.out.println("############## MENU' PRINCIPALE ##############");
        System.out.println("Seleziona un'opzione:");
        System.out.println("");
        System.out.println("1) Menu' stelle");
        System.out.println("2) Menu' pianeti");
        System.out.println("3) Menu' galassie");
        System.out.println("4) Menu' eventi cosmici");
        System.out.println("5) Resoconto delle relazioni fra i dati");
        System.out.println("");
        System.out.println("0) Esci");
        System.out.println("##############################################");
        System.out.print("Opzione:");
    }

    private void printStelleMenuPage() {
        System.out.println("*************** MENU' STELLE *****************");
        System.out.println("Seleziona un'opzione:");
        System.out.println("");
        System.out.println("1) Lista stelle");
        System.out.println("2) Crea stella");
        System.out.println("3) Rimuovi stella");
        System.out.println("4) Stelle per fase");
        System.out.println("5) Stelle per temperatura minima");
        System.out.println("6) Stella piu' calda");
        System.out.println("7) Stelle senza galassia");
        System.out.println("8) Conta stelle in una galassia");
        System.out.println("");
        System.out.println("0) Indietro");
        System.out.println("**********************************************");
        System.out.print("Opzione: ");
    }

    private void printPianetiMenuPage() {
        System.out.println("<<<<<<<<<<<<<<<< MENU' PIANETI >>>>>>>>>>>>>>>>");
        System.out.println("Seleziona un'opzione:");
        System.out.println("");
        System.out.println("1) Lista pianeti");
        System.out.println("2) Crea pianeta");
        System.out.println("3) Rimuovi pianeta");
        System.out.println("4) Pianeti per tipo");
        System.out.println("5) Pianeti per range di temperatura");
        System.out.println("6) Pianeta piu' caldo");
        System.out.println("7) Pianeti senza sistema");
        System.out.println("8) Conta pianeti in una galassia");
        System.out.println("");
        System.out.println("0) Indietro");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.print("Opzione: ");
    }

    private void printGalassieMenuPage() {
        System.out.println("================ MENU' GALASSIE ================");
        System.out.println("Seleziona un'opzione:");
        System.out.println("");
        System.out.println("1) Lista galassie");
        System.out.println("2) Crea galassia");
        System.out.println("3) Rimuovi galassia");
        System.out.println("4) Galassie per tipo");
        System.out.println("5) Galassie per massa minima");
        System.out.println("6) Galassia piu' pesante");
        System.out.println("7) Galassie senza stelle");
        System.out.println("8) Conta stelle in una galassia");
        System.out.println("");
        System.out.println("0) Indietro");
        System.out.println("==============================================");
        System.out.print("Opzione: ");
    }

    private void printEventoCosmicoMenuPage() {
        System.out.println("++++++++++++ MENU' EVENTI COSMICI ++++++++++++");
        System.out.println("Seleziona un'opzione:");
        System.out.println("");
        System.out.println("1) Lista eventi cosmici");
        System.out.println("2) Crea evento cosmico");
        System.out.println("3) Rimuovi evento cosmico");
        System.out.println("4) Eventi per tipo");
        System.out.println("5) Eventi dopo una data");
        System.out.println("6) Eventi senza stella");
        System.out.println("7) Conta eventi per stella");
        System.out.println("8) Ultimo evento cosmico");
        System.out.println("9) Esplora tutti gli eventi cosmici con dettagli su stelle, pianeti e galassie");
        System.out.println("");
        System.out.println("0) Indietro");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.print("Opzione: ");
    }

    public int mainMenu() {
        do {
            printMainMenuPage();
            int opzione = ConsoleRead.readInt();
            if (opzione < 0 || opzione > 5) {
                System.out.println("L'opzione " + opzione + " non e' valida");
            } else {
                return opzione;
            }
        } while (true);
    }

    public int stelleMenu() {
        do {
            printStelleMenuPage();
            int opzione = ConsoleRead.readInt();
            if (opzione < 0 || opzione > 8) {
                System.out.println("L'opzione " + opzione + " non e' valida");
            } else {
                return opzione;
            }
        } while (true);
    }

    public int pianetiMenu() {
        do {
            printPianetiMenuPage();
            int opzione = ConsoleRead.readInt();
            if (opzione < 0 || opzione > 8) {
                System.out.println("L'opzione " + opzione + " non e' valida");
            } else {
                return opzione;
            }
        } while (true);
    }

    public int galassieMenu() {
        do {
            printGalassieMenuPage();
            int opzione = ConsoleRead.readInt();
            if (opzione < 0 || opzione > 8) {
                System.out.println("L'opzione " + opzione + " non e' valida");
            } else {
                return opzione;
            }
        } while (true);
    }

    public int eventicosmiciMenu() {
        do {
            printEventoCosmicoMenuPage();
            int opzione = ConsoleRead.readInt();
            if (opzione < 0 || opzione > 9) {
                System.out.println("L'opzione " + opzione + " non e' valida");
            } else {
                return opzione;
            }
        } while (true);
    }
    
    //CREATE
    public void addStella() {
        do {
            System.out.print("ID: ");
            Integer id = ConsoleRead.readIntGreaterThan(1);

            System.out.print("Nome: ");
            String nome = ConsoleRead.readNotBlankString();

            System.out.print("Sistema (null se nessuno): ");
            String sistema = ConsoleRead.readNotBlankString();
            if(sistema.equals("null")) {
            	sistema = null;
            }

            System.out.print("Temperatura: ");
            Integer temp = ConsoleRead.readPositiveInt();

            stampaFasiStella();
            System.out.print("Fase: ");
            FaseStella fase = readFase();

            this.listGalassie();
        	System.out.print("ID Galassia (0 se nessuna): ");
            int idGal = ConsoleRead.readIntGreaterThan(0);

            if (!confermaInserimento()) {
                System.out.println("Operazione annullata.");
                return;
            }

            try {
                g.addStella(new Stella(id, nome, sistema, temp, fase, idGal));
                System.out.println("Stella inserita");
            } catch (SQLException e) {
                System.out.println("Errore di comunicazione con il database."+e);
            } catch (DuplicateException e) {
                System.out.println("E' gia' presente una stella con l'id " + id);
            }
            return;

        } while (true);
    }
    
    public void addPianeta() {
        do {
            System.out.print("ID: ");
            Integer id = ConsoleRead.readIntGreaterThan(0);

            System.out.print("Nome: ");
            String nome = ConsoleRead.readNotBlankString();

            System.out.print("Sistema (null se nessuno): ");
            String sistema = ConsoleRead.readNotBlankString();
            if(sistema.equals("null")) {
            	sistema = null;
            }

            stampaTipiPianeta();
            System.out.print("Tipo: ");
            TipoPianeta tipo = readTipoPian();

            System.out.print("Temperatura: ");
            Integer temp = ConsoleRead.readPositiveInt();

            this.listGalassie();
            System.out.print("ID Galassia (0 se nessuna): ");
            int idGal = ConsoleRead.readIntGreaterThan(0);

            if (!confermaInserimento()) {
                System.out.println("Operazione annullata.");
                return;
            }
            try {
                g.addPianeta(new Pianeta(id, nome, sistema, tipo, temp, idGal));
                System.out.println("Pianeta inserito");
            } catch (SQLException e) {
                System.out.println("Errore di comunicazione con il database."+e);
            } catch (DuplicateException e) {
                System.out.println("E' gia' presente un pianeta con l'id " + id);
            }
            return;

        } while (true);
    }
    
    public void addEventoCosmico() {
        do {
            System.out.print("ID Evento: ");
            int id = ConsoleRead.readIntGreaterThan(0);

            System.out.print("Nome: ");
            String nome = ConsoleRead.readNotBlankString();

            stampaTipiEventoCosmico();
            System.out.print("Tipo: ");
            TipoEventoCosmico tipo = readTipoEC();

            System.out.print("Data (DD-MM-YYYY): ");
            LocalDate data = ConsoleRead.readDate(IT.DATE);

            System.out.print("Ora (HH:MM): ");
            LocalTime ora = ConsoleRead.readTime(IT.TIME);

            this.listStelle();

            int idStella;
            System.out.print("ID Stella (OBBLIGATORIO): ");
            idStella = ConsoleRead.readIntGreaterThan(1);

            if (!confermaInserimento()) {
                System.out.println("Operazione annullata.");
                return;
            }
            try {
                g.addEventoCosmico(new EventoCosmico(id, nome, tipo, data, ora, idStella));
                System.out.println("Evento cosmico inserito");
            } catch (SQLException e) {
                System.out.println("Errore di comunicazione con il database."+e);
            } catch (DuplicateException e) {
                System.out.println("E' gia' presente un evento cosmico con l'id " + id);
            }
            return;

        } while (true);
    }
    
    public void addGalassia() {
        do {
            System.out.print("ID: ");
            int id = ConsoleRead.readIntGreaterThan(0);

            System.out.print("Nome: ");
            String nome = ConsoleRead.readNotBlankString();

            stampaTipiGalassiaEnum();
            System.out.print("Tipo: ");
            TipoGalassia tipo = readTipoGal();

            System.out.print("Massa: ");
            int massa = ConsoleRead.readPositiveInt();

            if (!confermaInserimento()) {
                System.out.println("Operazione annullata.");
                return;
            }
            try {
                g.addGalassia(new Galassia(id, nome, tipo, massa));
                System.out.println("Galassia inserita");
            } catch (SQLException e) {
                System.out.println("Errore di comunicazione con il database."+e);
            } catch (DuplicateException e) {
                System.out.println("E' gia' presente una galassia con l'id " + id);
            }
            return;
        } while (true);
    }

    //DELETE
    public void removeStella() {
        this.listStelle();
        System.out.print("ID Stella da rimuovere: ");
        Integer id = ConsoleRead.readIntGreaterThan(0);

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
            System.out.println("Errore di comunicazione con il database."+e);
        }
    }

    public void removePianeta() {
        this.listPianeti();
        System.out.print("ID Pianeta da rimuovere: ");
        Integer id = ConsoleRead.readIntGreaterThan(0);

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
            System.out.println("Errore di comunicazione con il database."+e);
        }
    }

    public void removeGalassia() {
        this.listGalassie();
        System.out.print("ID Galassia da rimuovere: ");
        Integer id = ConsoleRead.readIntGreaterThan(0);

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
            System.out.println("Errore di comunicazione con il database."+e);
        }
    }

    public void removeEventoCosmico() {
        this.listEventiCosmici();
        System.out.print("ID Evento Cosmico da rimuovere: ");
        Integer id = ConsoleRead.readIntGreaterThan(0);

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
            System.out.println("Errore di comunicazione con il database."+e);
        }
    }

    //LIST
    public void listStelle() {
        try {
            ArrayList<Stella> stelle = g.getStelle();
            if (stelle.isEmpty()) {
                System.out.println("Nessuna stella presente");
                return;
            }
            ConsolePrint.printTable(TableFormatter.parseStelleToTable(stelle, g.getTabAttr()), "*");

            int totale = stelle.size();
            System.out.println("Totale stelle nel database: " + totale);
        } catch (SQLException e) {
            System.out.println("Errore di comunicazione con il database."+e);
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

            int totale = pianeti.size();
            System.out.println("Totale pianeti nel database: " + totale);
        } catch (SQLException e) {
            System.out.println("Errore di comunicazione con il database."+e);
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

            int totale = galassie.size();
            System.out.println("Totale galassie nel database: " + totale);
        } catch (SQLException e) {
            System.out.println("Errore di comunicazione con il database."+e);
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

            int totale = eventi.size();
            System.out.println("Totale eventi cosmici nel database: " + totale);
        } catch (SQLException e) {
            System.out.println("Errore di comunicazione con il database."+e);
        } catch (InvalidSeparatorException e) {
        }
    }

    //ENUM
    private void stampaFasiStella() {
        FaseStella[] valori = FaseStella.values();

        System.out.println("Fasi disponibili:");
        for (FaseStella f : valori) {
            System.out.println("- " + f);
        }
    }

    private void stampaTipiPianeta() {
        TipoPianeta[] valori = TipoPianeta.values();

        System.out.println("Tipi disponibili:");
        for (TipoPianeta t : valori) {
            System.out.println("- " + t);
        }
    }

    private void stampaTipiEventoCosmico() {
        TipoEventoCosmico[] valori = TipoEventoCosmico.values();

        System.out.println("Tipi disponibili:");
        for (TipoEventoCosmico t : valori) {
            System.out.println("- " + t);
        }
    }

    private void stampaTipiGalassiaEnum() {
        TipoGalassia[] valori = TipoGalassia.values();

        System.out.println("Tipi disponibili:");
        for (TipoGalassia t : valori) {
            System.out.println("- " + t);
        }
    }
    
    //QUERIES
    public void stellePerFase() {
        stampaFasiStella();
        System.out.print("Fase: ");
        FaseStella fase = readFase();

        try {
            ArrayList<Stella> lista = g.getStelleByFase(fase);
            if (lista.isEmpty()) {
                System.out.println("Non ci sono stelle in questa fase.");
                return;
            }
            ConsolePrint.printTable(TableFormatter.parseStelleToTable(lista, g.getTabAttr()), "*");
        } catch (SQLException e) {
            System.out.println("Errore DB."+e);
        } catch (InvalidSeparatorException e) {
        }
    }

    public void stellePerMinTemperatura() {
        System.out.print("Temperatura minima: ");
        int t = ConsoleRead.readPositiveInt();

        try {
            ArrayList<Stella> lista = g.getStelleByMinTemperatura(t);
            if (lista.isEmpty()) {
                System.out.println("Non ci sono stelle con questa temperatura minima.");
                return;
            }
            ConsolePrint.printTable(TableFormatter.parseStelleToTable(lista, g.getTabAttr()), "*");
        } catch (SQLException e) {
            System.out.println("Errore DB."+e);
        } catch (InvalidSeparatorException e) {
        }
    }

    public void stellaPiuCalda() {
        try {
            ArrayList<Stella> lista = g.getStellaPiuCalda();
            if (lista.isEmpty()) {
                System.out.println("Non ci sono stelle presenti.");
                return;
            }
            ConsolePrint.printTable(TableFormatter.parseStelleToTable(lista, g.getTabAttr()), "*");
        } catch (SQLException e) {
            System.out.println("Errore DB."+e);
        } catch (InvalidSeparatorException e) {
        }
    }

    public void stelleSenzaGalassia() {
        try {
            ArrayList<Stella> lista = g.getStelleSenzaGalassia();
            if (lista.isEmpty()) {
                System.out.println("Non ci sono stelle senza galassia.");
                return;
            }
            ConsolePrint.printTable(TableFormatter.parseStelleToTable(lista, g.getTabAttr()), "*");
        } catch (SQLException e) {
            System.out.println("Errore DB."+e);
        } catch (InvalidSeparatorException e) {
        }
    }

    public void countStelleInGalassia() {
        this.listGalassie();
        System.out.print("ID Galassia: ");
        Integer id = ConsoleRead.readPositiveInt();

        try {
            int n = g.countStelleInGalassia(id);
            System.out.println("Numero stelle nella galassia: " + n);
        } catch (SQLException e) {
            System.out.println("Errore DB."+e);
        }
    }

    public void pianetiPerTipo() {
        stampaTipiPianeta();
        System.out.print("Tipo: ");
        TipoPianeta tipo = readTipoPian();

        try {
            ArrayList<Pianeta> lista = g.getPianetiByTipo(tipo);
            if (lista.isEmpty()) {
                System.out.println("Nessun pianeta trovato");
                return;
            }
            ConsolePrint.printTable(TableFormatter.parsePianetiToTable(lista, g.getTabAttr()), "*");
        } catch (SQLException e) {
            System.out.println("Errore DB."+e);
        } catch (InvalidSeparatorException e) {
        }
    }

    public void pianetiPerRangeTemperatura() {
        System.out.print("Temperatura minima: ");
        int min = ConsoleRead.readPositiveInt();
        System.out.print("Temperatura massima: ");
        int max = ConsoleRead.readPositiveInt();

        try {
            ArrayList<Pianeta> lista = g.getPianetiByTemperaturaRange(min, max);
            if (lista.isEmpty()) {
                System.out.println("Nessun pianeta trovato");
                return;
            }
            ConsolePrint.printTable(TableFormatter.parsePianetiToTable(lista, g.getTabAttr()), "*");
        } catch (SQLException e) {
            System.out.println("Errore DB."+e);
        } catch (InvalidSeparatorException e) {
        }
    }

    public void pianetaPiuCaldo() {
        try {
            ArrayList<Pianeta> lista = g.getPianetaPiuCaldo();
            if (lista.isEmpty()) {
                System.out.println("Nessun pianeta trovato");
                return;
            }
            ConsolePrint.printTable(TableFormatter.parsePianetiToTable(lista, g.getTabAttr()), "*");
        } catch (SQLException e) {
            System.out.println("Errore DB."+e);
        } catch (InvalidSeparatorException e) {
        }
    }

    public void countPianetiInGalassia() {
        this.listGalassie();
        System.out.print("ID Galassia: ");
        Integer id = ConsoleRead.readPositiveInt();

        try {
            int n = g.countPianetiInGalassia(id);
            System.out.println("Numero pianeti: " + n);
        } catch (SQLException e) {
            System.out.println("Errore DB."+e);
        }
    }

    public void pianetiSenzaSistema() {
        try {
            ArrayList<Pianeta> lista = g.getPianetiSenzaSistema();
            if (lista.isEmpty()) {
                System.out.println("Nessun pianeta trovato");
                return;
            }
            ConsolePrint.printTable(TableFormatter.parsePianetiToTable(lista, g.getTabAttr()), "*");
        } catch (SQLException e) {
            System.out.println("Errore DB."+e);
        } catch (InvalidSeparatorException e) {
        }
    }

    public void eventiPerTipo() {
        stampaTipiEventoCosmico();
        System.out.print("Tipo: ");
        TipoEventoCosmico tipo = readTipoEC();

        try {
            ArrayList<EventoCosmico> lista = g.getEventiByTipo(tipo);
            if (lista.isEmpty()) {
                System.out.println("Nessun evento trovato");
                return;
            }
            ConsolePrint.printTable(TableFormatter.parseECToTable(lista, g.getTabAttr()), "*");
        } catch (SQLException e) {
            System.out.println("Errore DB."+e);
        } catch (InvalidSeparatorException e) {
        }
    }

    public void eventiDopoData() {
        System.out.print("Data (dd-mm-yyyy): ");
        LocalDate d = ConsoleRead.readDate(IT.DATE);

        try {
            ArrayList<EventoCosmico> lista = g.getEventiDopoData(d);
            if (lista.isEmpty()) {
                System.out.println("Nessun evento trovato");
                return;
            }
            ConsolePrint.printTable(TableFormatter.parseECToTable(lista, g.getTabAttr()), "*");
        } catch (SQLException e) {
            System.out.println("Errore DB."+e);
        } catch (InvalidSeparatorException e) {
        }
    }

    public void countEventiPerStella() {
        this.listStelle();
        System.out.print("ID Stella: ");
        Integer id = ConsoleRead.readPositiveInt();

        try {
            int n = g.countEventiByStella(id);
            System.out.println("Numero eventi: " + n);
        } catch (SQLException e) {
            System.out.println("Errore DB."+e);
        }
    }

    public void eventiSenzaStella() {
        try {
            ArrayList<EventoCosmico> lista = g.getEventiSenzaStella();
            if (lista.isEmpty()) {
                System.out.println("Nessun evento trovato");
                return;
            }
            ConsolePrint.printTable(TableFormatter.parseECToTable(lista, g.getTabAttr()), "*");
        } catch (SQLException e) {
            System.out.println("Errore DB."+e);
        } catch (InvalidSeparatorException e) {
        }
    }

    public void galassiePerTipo() {
        stampaTipiGalassiaEnum();
        System.out.print("Tipo galassia: ");
        TipoGalassia tipo = readTipoGal();

        try {
            ArrayList<Galassia> lista = g.getGalassieByTipo(tipo);
            if (lista.isEmpty()) {
                System.out.println("Nessuna galassia trovata");
                return;
            }
            ConsolePrint.printTable(TableFormatter.parseGalassieToTable(lista, g.getTabAttr()), "*");
        } catch (SQLException e) {
            System.out.println("Errore DB."+e);
        } catch (InvalidSeparatorException e) {
        }
    }

    public void galassiePerMinMassa() {
        System.out.print("Massa minima: ");
        int massa = ConsoleRead.readPositiveInt();

        try {
            ArrayList<Galassia> lista = g.getGalassieByMinMassa(massa);
            if (lista.isEmpty()) {
                System.out.println("Nessuna galassia trovata");
                return;
            }
            ConsolePrint.printTable(TableFormatter.parseGalassieToTable(lista, g.getTabAttr()), "*");
        } catch (SQLException e) {
            System.out.println("Errore DB."+e);
        } catch (InvalidSeparatorException e) {
        }
    }

    public void galassiaPiuPesante() {
        try {
            ArrayList<Galassia> lista = g.getGalassiaPiuPesante();
            if (lista.isEmpty()) {
                System.out.println("Nessuna galassia trovata");
                return;
            }
            ConsolePrint.printTable(TableFormatter.parseGalassieToTable(lista, g.getTabAttr()), "*");
        } catch (SQLException e) {
            System.out.println("Errore DB."+e);
        } catch (InvalidSeparatorException e) {
        }
    }

    public void galassieSenzaStelle() {
        try {
            ArrayList<Galassia> lista = g.getGalassieSenzaStelle();
            if (lista.isEmpty()) {
                System.out.println("Nessuna galassia trovata");
                return;
            }
            ConsolePrint.printTable(TableFormatter.parseGalassieToTable(lista, g.getTabAttr()), "*");
        } catch (SQLException e) {
            System.out.println("Errore DB."+e);
        } catch (InvalidSeparatorException e) {
        }
    }

    public void ultimoEventoCosmico() {
        try {
            EventoCosmico ec = g.getUltimoEventoCosmico();
            if (ec == null) {
                System.out.println("Nessun evento presente.");
                return;
            }
            ArrayList<EventoCosmico> lista = new ArrayList<>();
            lista.add(ec);
            ConsolePrint.printTable(TableFormatter.parseECToTable(lista, g.getTabAttr()), "*");

        } catch (SQLException e) {
            System.out.println("Errore DB."+e);
        } catch (InvalidSeparatorException e) {
        }
    }
    
    public void universoCompleto() {
        try {
            ResultSet rs = g.getUniversoCompleto();

            ArrayList<ArrayList<String>> table = new ArrayList<>();

            table.add(new ArrayList<>(List.of(
                    "Galassia", "Stella", "Pianeta", "Evento"
            )));

            while (rs.next()) {
                table.add(new ArrayList<>(List.of(
                        rs.getString("nomeGalassia"),
                        rs.getString("nomeStella"),
                        rs.getString("nomePianeta"),
                        rs.getString("nomeEvento")
                )));
            }

            System.out.println("===== UNIVERSO COMPLETO =====");
            ConsolePrint.printTable(table, "*");

        } catch (SQLException e) {
            System.out.println("Errore di comunicazione con il database."+e);
        } catch (InvalidSeparatorException e) {
            System.out.println("Errore di formattazione tabella.");
        }
    }

    public void eventiConContesto() {
        try {
            System.out.println("===== EVENTI COSMICI CON CONTESTO =====");

            ArrayList<ArrayList<String>> table
                    = g.getEventiConContestoTable();

            ConsolePrint.printTable(table, "*");

        } catch (SQLException e) {
            System.out.println("Errore di comunicazione con il database."+e);
        } catch (InvalidSeparatorException e) {
            System.out.println("Errore di formattazione tabella.");
        }
    }
    
    //APP INFO
    private String[] getAppInfo() {
        try {
            String[] out = new String[6];
            out[0] = PropertiesRead.readBaseInfo(g.getCfg_path())[0];
            out[1] = PropertiesRead.readBaseInfo(g.getCfg_path())[1];
            out[2] = PropertiesRead.readBaseInfo(g.getCfg_path())[2];
            out[3] = PropertiesRead.readBaseInfo(g.getCfg_path())[3];
            out[4] = PropertiesRead.readBaseInfo(g.getCfg_path())[4];
            out[5] = PropertiesRead.readInfo(g.getCfg_path(), "doanimation");
            return out;
        } catch (IOException e) {
            System.out.println("Errore di lettura del file config.properties.");
            return null;
        }
    }
    //UTIL
    private boolean confermaInserimento() {
        do {
            System.out.print("Sei sicuro di voler aggiungere l'elemento? (S/N): ");
            String s = ConsoleRead.readNotBlankString();
            if (s.equalsIgnoreCase("S") || s.equalsIgnoreCase("Y")) {
                return true;
            } else if (s.equalsIgnoreCase("N")) {
                return false;
            }
            System.out.print(s.toUpperCase() + " non e' un opzione (S/N): ");
        } while (true);
    }

    private String readString() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    private FaseStella readFase() {
        do {
            String input = ConsoleRead.readNotBlankString().toUpperCase();
            try {
                return FaseStella.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.print("Valore non valido. Riprova: ");
            }
        } while (true);
    }
    
    private TipoEventoCosmico readTipoEC() {
        do {
            String input = ConsoleRead.readNotBlankString().toUpperCase();
            try {
                return TipoEventoCosmico.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.print("Valore non valido. Riprova: ");
            }
        } while (true);
    }
    
    private TipoGalassia readTipoGal() {
        do {
            String input = ConsoleRead.readNotBlankString().toUpperCase();
            try {
                return TipoGalassia.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.print("Valore non valido. Riprova: ");
            }
        } while (true);
    }
    
    private TipoPianeta readTipoPian() {
        do {
            String input = ConsoleRead.readNotBlankString().toUpperCase();
            try {
                return TipoPianeta.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.print("Valore non valido. Riprova: ");
            }
        } while (true);
    }
    
    private boolean confermaEliminazione() {
        do {
            System.out.print("Sei sicuro di voler eliminare? (S/N): ");
            String s = ConsoleRead.readNotBlankString();
            if (s.equalsIgnoreCase("S") || s.equalsIgnoreCase("Y")) {
                return true;
            } else if (s.equalsIgnoreCase("N")) {
                return false;
            }
            System.out.print(s.toUpperCase() + " non e' un opzione (S/N): ");
        } while (true);
    }
    
    private void pausa(int millisecondi) {
        try {
            Thread.sleep(millisecondi);
        } catch (InterruptedException e) {
            // Ignora eccezioni
        }
    }
}
