package main;

import leolib.iodb.PropertiesRead;
import leolib.iodb.DBConnector;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            // 1. Leggiamo il file config.properties
            PropertiesRead pr = new PropertiesRead();
            // Carica app.name, app.version, url, user, password in un array 
            String[] info = pr.readBaseInfo("config.properties");

            // 2. Creiamo l'oggetto per la connessione usando i dati dell'array
            // info[2] è l'URL, info[3] è l'user, info[4] è la password 
            DBConnector db = new DBConnector(info[2], info[3], info[4]);

            // 3. Messaggio di conferma usando il nome dell'app configurato nel file
            System.out.println("Benvenuto in: " + info[0]);
            System.out.println("Tentativo di connessione al database Universo_DB...");

            // Proviamo ad aprire la connessione (se fallisce va nel catch)
            if (db != null) {
                System.out.println("CONNESSA! Il link tra NetBeans e Workbench funziona.");
            }

        } catch (Exception e) {
            System.out.println("ERRORE: Qualcosa è andato storto!");
            System.out.println("Controlla se:");
            System.out.println("1. Hai inserito la PASSWORD corretta nel file.");
            System.out.println("2. Sei connesso a internet (il server è remoto).");
            System.out.println("3. Hai aggiunto il Driver MySQL JAR nelle librerie.");
            e.printStackTrace();
        }
    }
}
