package main;

import leolib.iodb.PropertiesRead;
import logic.GestUniverso;
import model.Stella;
import myexceptions.ConnectionException;
import userinterface.Cli;
import utils.TableFormatter;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import leolib.dtformatters.ISOIT;
import leolib.internalexceptions.InvalidSeparatorException;
import leolib.ioconsole.ConsolePrint;
import leolib.iodb.DBConnector;

public class Main {

    public static void main(String[] args) throws IOException {
    	try {
    		String[] info = PropertiesRead.readBaseInfo("config.properties");
    		Cli cli = new Cli();

	        System.out.println("Benvenuto in: " + info[0]);
    	} catch (ConnectionException e) {
    		System.out.println("==========================================================");
			System.out.println("ERRORE! Database non connesso! Controlla che: \n"
					+ "Il server sia acceso \n"
					+ "Le credenziali siano giuste \n"
					+ "Che tu l'utente abbia accesso al database.");
			System.out.println("==========================================================");
		}
    }
}
