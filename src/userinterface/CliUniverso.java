package userinterface;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import leolib.ioconsole.ConsoleRead;
import logic.GestUniverso;
import model.*;
import model.enums.*;
import myexceptions.*;

public class CliUniverso {

    private Scanner sc = new Scanner(System.in);
    private GestUniverso gu;

    public CliUniverso(GestUniverso gu) {
        this.gu = gu;
    }
/*
    public void inserisciStella() {
        try {
            System.out.println("--- Inserimento Stella ---");
            System.out.print("ID Stella: ");
            int id = ConsoleRead.readPositiveInt();
            System.out.print("Nome: ");
            String nome = leggiStringa();
            System.out.print("Sistema (invio se nullo): ");
            String sistema = sc.nextLine();
            if (sistema.isEmpty()) {
                sistema = null;
            }
            System.out.print("Temperatura: ");
            int temperatura = leggiInt();
            System.out.print("Fase (ES: PRINCIPALE): ");
            FaseStella fase = leggiFaseStella();
            System.out.print("ID Galassia: ");
            int idGal = leggiInt();

            Stella s = new Stella(id, nome, sistema, temperatura, fase, idGal);
            gu.addStella(s);
            System.out.println(">> Stella inserita con successo.");
        } catch (DuplicateException | CliInputException e) {
            System.out.println("Errore: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Errore Database (SQLException): " + e.getMessage());
        }
    }

    public void inserisciGalassia() {
        try {
            System.out.println("--- Inserimento Galassia ---");
            System.out.print("ID Galassia: ");
            int id = leggiInt();
            System.out.print("Nome: ");
            String nome = leggiStringa();
            System.out.print("Tipo: ");
            String tipo = leggiStringa();
            System.out.print("Massa: ");
            int massa = leggiInt();

            Galassia g = new Galassia(id, nome, tipo, massa);
            gu.addGalassia(g);
            System.out.println(">> Galassia inserita con successo.");
        } catch (DuplicateException | CliInputException e) {
            System.out.println("Errore: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Errore Database: " + e.getMessage());
        }
    }

    public void inserisciPianeta() {
        try {
            System.out.println("--- Inserimento Pianeta ---");
            System.out.print("ID Pianeta: ");
            int id = leggiInt();
            System.out.print("Nome: ");
            String nome = leggiStringa();
            System.out.print("Sistema: ");
            String sistema = sc.nextLine();
            if (sistema.isEmpty()) {
                sistema = null;
            }
            System.out.print("Tipo (SOLIDO, GASSOSO...): ");
            TipoPianeta tipo = leggiTipoPianeta();
            System.out.print("Temperatura: ");
            int temp = leggiInt();
            System.out.print("ID Galassia: ");
            int idGal = leggiInt();

            Pianeta p = new Pianeta(id, nome, sistema, tipo, temp, idGal);
            gu.addPianeta(p);
            System.out.println(">> Pianeta inserito con successo.");
        } catch (DuplicateException | CliInputException e) {
            System.out.println("Errore: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Errore Database: " + e.getMessage());
        }
    }

    public void inserisciEventoCosmico() {
        try {
            System.out.println("--- Inserimento Evento ---");
            System.out.print("ID Evento: ");
            int id = leggiInt();
            System.out.print("Nome: ");
            String nome = leggiStringa();
            System.out.print("Tipo (SUPERNOVA...): ");
            TipoEventoCosmico tipo = leggiTipoEvento();
            System.out.print("Data (YYYY-MM-DD): ");
            LocalDate data = leggiData();
            System.out.print("Ora (HH:MM): ");
            LocalTime ora = leggiOra();
            System.out.print("ID Stella: ");
            int idStella = leggiInt();

            EventoCosmico ec = new EventoCosmico(id, nome, tipo, data, ora, idStella);
            gu.addEventoCosmico(ec);
            System.out.println(">> Evento inserito con successo.");
        } catch (DuplicateException | CliInputException e) {
            System.out.println("Errore: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Errore Database: " + e.getMessage());
        }
    }
    */
}
