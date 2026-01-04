package cli;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import logic.GestUniverso;
import model.*;
import model.enums.*;
import myexceptions.*;

public class CliUniverso {

    private Scanner sc;
    private GestUniverso gu;

    public CliUniverso(GestUniverso gu) {
        this.gu = gu;
        this.sc = new Scanner(System.in);
    }

    public void inserisciStella() throws CliInputException, DuplicateException, SQLException {
        System.out.print("ID Stella: ");
        int id = leggiInt();

        System.out.print("Nome: ");
        String nome = leggiStringa();

        System.out.print("Sistema (invio se nullo): ");
        String sistema = sc.nextLine();
        if (sistema.isEmpty()) {
            sistema = null;
        }

        System.out.print("Temperatura: ");
        int temperatura = leggiInt();

        System.out.print("Fase (NEBULOSA, PRINCIPALE, SUPERNOVA...): ");
        FaseStella fase = leggiFaseStella();

        System.out.print("ID Galassia (0 se nessuna): ");
        int idGal = leggiInt();

        Stella s = new Stella(id, nome, sistema, temperatura, fase, idGal);

        ArrayList<Stella> stelle = gu.getStelle();
        for (Stella st : stelle) {
            if (st.getIdStella() == id) {
                throw new DuplicateException("Stella con ID già esistente");
            }
        }

        stelle.add(s);
        gu.saveStelle(stelle);
    }

    public void inserisciGalassia() throws CliInputException, DuplicateException, SQLException {
        System.out.print("ID Galassia: ");
        int id = leggiInt();

        System.out.print("Nome: ");
        String nome = leggiStringa();

        System.out.print("Tipo: ");
        String tipo = leggiStringa();

        System.out.print("Massa: ");
        int massa = leggiInt();

        Galassia g = new Galassia(id, nome, tipo, massa);

        ArrayList<Galassia> galassie = gu.getGalassie();
        for (Galassia gal : galassie) {
            if (gal.getIdGalassia() == id) {
                throw new DuplicateException("Galassia con ID già esistente");
            }
        }

        galassie.add(g);
        gu.saveGalassie(galassie);
    }

    public void inserisciPianeta() throws CliInputException, SQLException {
        System.out.print("ID Pianeta: ");
        int id = leggiInt();

        System.out.print("Nome: ");
        String nome = leggiStringa();

        System.out.print("Sistema (invio se nullo): ");
        String sistema = sc.nextLine();
        if (sistema.isEmpty()) {
            sistema = null;
        }

        System.out.print("Tipo (SOLIDO, GASSOSO, GHIACCIATO): ");
        TipoPianeta tipo = leggiTipoPianeta();

        System.out.print("Temperatura: ");
        int temp = leggiInt();

        System.out.print("ID Galassia: ");
        int idGal = leggiInt();

        Pianeta p = new Pianeta(id, nome, sistema, tipo, temp, idGal);

        ArrayList<Pianeta> pianeti = gu.getPianeti();
        pianeti.add(p);
        gu.savePianeti(pianeti);
    }

    public void inserisciEventoCosmico() throws CliInputException, SQLException {
        System.out.print("ID Evento: ");
        int id = leggiInt();

        System.out.print("Nome: ");
        String nome = leggiStringa();

        System.out.print("Tipo (SUPERNOVA, FUSIONE_BH, ESPLOSIONE_GRB, ALTRO): ");
        TipoEventoCosmico tipo = leggiTipoEvento();

        System.out.print("Data (YYYY-MM-DD): ");
        LocalDate data = leggiData();

        System.out.print("Ora (HH:MM): ");
        LocalTime ora = leggiOra();

        System.out.print("ID Stella: ");
        int idStella = leggiInt();

        EventoCosmico e = new EventoCosmico(id, nome, tipo, data, ora, idStella);

        ArrayList<EventoCosmico> eventi = gu.getEventiCosmici();
        eventi.add(e);
        gu.saveEventiCosmici(eventi);
    }

    private int leggiInt() throws CliInputException {
        try {
            int val = sc.nextInt();
            sc.nextLine();
            return val;
        } catch (Exception e) {
            sc.nextLine();
            throw new CliInputException("Input numerico non valido");
        }
    }

    private String leggiStringa() throws CliInputException {
        String s = sc.nextLine();
        if (s.isEmpty()) {
            throw new CliInputException("Stringa vuota");
        }
        return s;
    }

    private FaseStella leggiFaseStella() throws CliInputException {
        try {
            return FaseStella.valueOf(sc.nextLine().toUpperCase());
        } catch (Exception e) {
            throw new CliInputException("Fase stella non valida");
        }
    }

    private TipoPianeta leggiTipoPianeta() throws CliInputException {
        try {
            return TipoPianeta.valueOf(sc.nextLine().toUpperCase());
        } catch (Exception e) {
            throw new CliInputException("Tipo pianeta non valido");
        }
    }

    private TipoEventoCosmico leggiTipoEvento() throws CliInputException {
        try {
            return TipoEventoCosmico.valueOf(sc.nextLine().toUpperCase());
        } catch (Exception e) {
            throw new CliInputException("Tipo evento non valido");
        }
    }

    private LocalDate leggiData() throws CliInputException {
        try {
            return LocalDate.parse(sc.nextLine());
        } catch (Exception e) {
            throw new CliInputException("Formato data non valido");
        }
    }

    private LocalTime leggiOra() throws CliInputException {
        try {
            return LocalTime.parse(sc.nextLine());
        } catch (Exception e) {
            throw new CliInputException("Formato ora non valido");
        }
    }
}
