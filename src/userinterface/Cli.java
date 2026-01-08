package userinterface;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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

public class Cli {

    private GestUniverso g = new GestUniverso();

    public Cli() {
        try {
            String[] info = PropertiesRead.readBaseInfo(g.getCfg_path());
            g = new GestUniverso(new DBConnector(info[2],info[3],info[4]));
        } catch (IOException e) {
            System.out.println("Errore di lettura del file config.properties.");
        } catch (SQLException e) {
            System.out.println("Errore di creazione DBConnector.");
        }
    }
    
    public GestUniverso getGest() {
		return g;
	}


	// ================= INSERIMENTIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII =================
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

        g.addStella(new Stella(id, nome, sistema, temp, fase, idGal));
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

        g.addPianeta(new Pianeta(id, nome, sistema, tipo, temp, idGal));
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

        g.addGalassia(new Galassia(id, nome, tipo, massa));
        System.out.println("Galassia inserita");
    }

    private void inserisciEventoCosmico() throws SQLException, DuplicateException {

        System.out.print("ID Evento: ");
        int id = ConsoleRead.readPositiveInt();

        System.out.print("Nome: ");
        String nome = ConsoleRead.readNotBlankString();

        stampaTipiEventoCosmico();
        System.out.print("Tipo: ");
        TipoEventoCosmico tipo
                = TipoEventoCosmico.valueOf(ConsoleRead.readNotBlankString());

        System.out.print("Data (YYYY-MM-DD): ");
        LocalDate data = LocalDate.parse(ConsoleRead.readNotBlankString());

        System.out.print("Ora (HH:MM): ");
        LocalTime ora = LocalTime.parse(ConsoleRead.readNotBlankString());

        System.out.print("ID Stella: ");
        int idStella = ConsoleRead.readPositiveInt();

        g.addEventoCosmico(new EventoCosmico(id, nome, tipo, data, ora, idStella));
        System.out.println("Evento cosmico inserito");
    }

    // ================= RIMOZIONIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII =================
    private boolean confermaEliminazione() {
        System.out.print("Sei sicuro di voler eliminare? (S/N): ");
        String s = ConsoleRead.readNotBlankString();
        return s.equalsIgnoreCase("S");
    }

    private void rimuoviStella() throws SQLException {

        System.out.print("ID Stella da rimuovere: ");
        int id = ConsoleRead.readPositiveInt();

        if (!confermaEliminazione()) {
            System.out.println("Operazione annullata");
            return;
        }

        int righe = g.deleteStella(id);
        System.out.println(righe > 0 ? "Stella rimossa" : "Stella non trovata");
    }

    private void rimuoviPianeta() throws SQLException {

        System.out.print("ID Pianeta da rimuovere: ");
        int id = ConsoleRead.readPositiveInt();

        if (!confermaEliminazione()) {
            System.out.println("Operazione annullata");
            return;
        }

        int righe = g.deletePianeta(id);
        System.out.println(righe > 0 ? "Pianeta rimosso" : "Pianeta non trovato");
    }

    private void rimuoviGalassia() throws SQLException {

        System.out.print("ID Galassia da rimuovere: ");
        int id = ConsoleRead.readPositiveInt();

        if (!confermaEliminazione()) {
            System.out.println("Operazione annullata");
            return;
        }

        int righe = g.deleteGalassia(id);
        System.out.println(righe > 0 ? "Galassia rimossa" : "Galassia non trovata");
    }

    private void rimuoviEventoCosmico() throws SQLException {

        System.out.print("ID Evento Cosmico da rimuovere: ");
        int id = ConsoleRead.readPositiveInt();

        if (!confermaEliminazione()) {
            System.out.println("Operazione annullata");
            return;
        }

        int righe = g.deleteEventoCosmico(id);
        System.out.println(righe > 0 ? "Evento rimosso" : "Evento non trovato");
    }

    // ================= VISUALIZZAZIONEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE =================
    private void mostraStelle() throws SQLException {
        ArrayList<Stella> stelle = g.getStelle();
        if (stelle.isEmpty()) {
            System.out.println("Nessuna stella presente");
            return;
        }
        for (Stella s : stelle) {
            System.out.println(s);
        }
    }

    private void mostraPianeti() throws SQLException {
        ArrayList<Pianeta> pianeti = g.getPianeti();
        if (pianeti.isEmpty()) {
            System.out.println("Nessun pianeta presente");
            return;
        }
        for (Pianeta p : pianeti) {
            System.out.println(p);
        }
    }

    private void mostraGalassie() throws SQLException {
        ArrayList<Galassia> galassie = g.getGalassie();
        if (galassie.isEmpty()) {
            System.out.println("Nessuna galassia presente");
            return;
        }
        for (Galassia g : galassie) {
            System.out.println(g);
        }
    }

    private void mostraEventiCosmici() throws SQLException {
        ArrayList<EventoCosmico> eventi = g.getEventiCosmici();
        if (eventi.isEmpty()) {
            System.out.println("Nessun evento presente");
            return;
        }
        for (EventoCosmico e : eventi) {
            System.out.println(e);
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
}
