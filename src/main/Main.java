package main;

import leolib.iodb.PropertiesRead;
import model.Stella;
import myexceptions.ParsingException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import leolib.iodb.DBConnector;

public class Main {

    public static void main(String[] args) throws IOException, ParsingException {
    	try {
    		String[] info = PropertiesRead.readBaseInfo("config.properties");

	        DBConnector dbc = new DBConnector(info[2], info[3], info[4]);

	        System.out.println("Benvenuto in: " + info[0]);
	        System.out.println("Tentativo di connessione al database Universo_DB...");

	        if(dbc.testConnection()){
	            System.out.println("CONNESSA! Il link tra NetBeans e MySQL funziona.");
	        }else{
	        	System.out.println("ERRORE: Qualcosa e' andato storto!");
	            System.out.println("Controlla se:");
	            System.out.println("1. Hai inserito la PASSWORD corretta nel file.");
	            System.out.println("2. Sei connesso a internet (il server e' remoto).");
	            System.out.println("3. Il server e' online.");
	        }
    	}catch(SQLException e) {
    		System.out.println("Errore generale "+e);
    	}
    }
    public static void print(ArrayList<Stella> a) {
    	for(int i=0;i<a.size();i++) {
    		System.out.println(a.get(i));
    	}
    }
}
