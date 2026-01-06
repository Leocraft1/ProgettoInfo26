package main;

import leolib.iodb.PropertiesRead;
import myexceptions.ConnectionException;
import userinterface.Cli;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {
        try {
            String[] info = PropertiesRead.readBaseInfo("config.properties");

            System.out.println("Benvenuto in: " + info[0]);

            Cli cli = new Cli();
            cli.start();   // ⬅️ QUI PARTE TUTTO LEO TOSCHITOS SUCA SUCA

        } catch (ConnectionException e) {
            System.out.println("==========================================================");
            System.out.println("ERRORE! Database non connesso! Controlla che: \n"
                    + "- Il server sia acceso\n"
                    + "- Le credenziali siano corrette\n"
                    + "- L'utente abbia accesso al database");
            System.out.println("==========================================================");
        }
    }
}
