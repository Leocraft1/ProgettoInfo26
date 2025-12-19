package logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import leolib.iodb.DBConnector;
import leolib.iodb.PropertiesRead;

public class GestUniverso {
	private DBConnector dbc;
	private ArrayList<String> tab_names;
	private ArrayList<ArrayList<String>> tab_attr;
	private String config_path = "config.properties";
	public GestUniverso(DBConnector dbc) {
		this.dbc = dbc;
	}
	public GestUniverso(DBConnector dbc, ArrayList<String> tab_names, ArrayList<ArrayList<String>> tab_attr) {
		this.dbc = dbc;
		this.tab_names = tab_names;
		this.tab_attr = tab_attr;
	}
	public DBConnector getDbc() {
		return dbc;
	}
	public void setDbc(DBConnector dbc) {
		this.dbc = dbc;
	}
	public ArrayList<String> getTabNames() {
		return tab_names;
	}
	public void setTabNames(ArrayList<String> tab_names) {
		this.tab_names = tab_names;
	}
	public ArrayList<ArrayList<String>> getTabAttr() {
		return tab_attr;
	}
	public void setTabAttr(ArrayList<ArrayList<String>> tab_attr) {
		this.tab_attr = tab_attr;
	}
	
	//Fine getter e setter gestione
	
	public void loadTablesProps() throws IOException {
		Properties p = PropertiesRead.createObj(config_path);
		//Carico i titoli delle tabelle
		tab_names.add(p.getProperty("astronauta.name"));
		//Carico gli attributi delle tabelle, splitto e converto ad AL
		
	}
}