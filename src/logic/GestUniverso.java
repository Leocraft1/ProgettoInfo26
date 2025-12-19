package logic;

import java.util.ArrayList;

import leolib.iodb.DBConnector;

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
}