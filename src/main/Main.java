package main;

import leolib.iodb.PropertiesRead;
import userinterface.Cli;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {
        String[] info = PropertiesRead.readBaseInfo("config.properties");

        System.out.println("Benvenuto in: " + info[0]);

        Cli cli = new Cli();
        if(cli.getGest().getDbc().testConnection()) {
        	System.out.println("La connessione funziona!");
        }else{
        	System.out.println("==========================================================");
            System.out.println("ERRORE! Database non connesso! Controlla che: \n"
                    + "- Il server sia acceso\n"
                    + "- Le credenziali siano corrette\n"
                    + "- L'utente abbia accesso al database");
            System.out.println("==========================================================");
        }
    }
}
