package main;

import leolib.iodb.PropertiesRead;
import logic.GestUniverso;
import model.Stella;
import utils.TableFormatter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import leolib.internalexceptions.InvalidSeparatorException;
import leolib.ioconsole.ConsolePrint;
import leolib.iodb.DBConnector;

public class Main {

    public static void main(String[] args) throws IOException {
    	try {
    		String[] info = PropertiesRead.readBaseInfo("config.properties");

	        DBConnector dbc = new DBConnector(info[2], info[3], info[4]);
	        GestUniverso g = new GestUniverso(dbc);
	        g.loadProperties();

	        System.out.println("Benvenuto in: " + info[0]);
	        System.out.println("Tentativo di connessione al database Universo_DB...");

	        if(dbc.testConnection()){
	            System.out.println("Connesso al DB.");
	        }else{
	        	System.out.println("ERRORE: Qualcosa e' andato storto!");
	            System.out.println("Controlla se:");
	            System.out.println("1. Hai inserito la PASSWORD corretta nel file.");
	            System.out.println("2. Sei connesso a internet (il server e' remoto).");
	            System.out.println("3. Il server e' online.");
	        }
	        System.out.println("Lista delle stelle: ");
	    	ConsolePrint.printTable(TableFormatter.parseStelleToTable(g.getStelle(),g.getTabAttr()), "*");
    	}catch(SQLException e) {
    		System.out.println("Errore generale "+e);
    	} catch (InvalidSeparatorException e) {}    	
    }
}
