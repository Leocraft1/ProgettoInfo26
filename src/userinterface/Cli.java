package userinterface;

import java.io.IOException;
import java.sql.SQLException;

import leolib.iodb.DBConnector;
import leolib.iodb.PropertiesRead;
import logic.GestUniverso;
import myexceptions.ConnectionException;

public class Cli {
	GestUniverso g = new GestUniverso();
	public Cli() throws ConnectionException {
		try {
			String[] info = PropertiesRead.readBaseInfo(g.getCfg_path());
			DBConnector dbc = new DBConnector(info[2], info[3], info[4]);
			if(!dbc.testConnection()) throw new ConnectionException();
			g.setDbc(dbc);
		} catch (IOException e) {
			System.out.println("Errore di lettura del file config.properties: manca il file o l'applicazione non ha i permessi di lettura.");
		} catch (SQLException e) {
			System.out.println("Errore di creazione di DBConnector.");
		}
	}
}