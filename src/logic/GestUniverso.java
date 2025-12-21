package logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import leolib.iodb.DBConnector;
import model.Stella;
import model.enums.FaseStella;
import myexceptions.ParsingException;
import utils.EnumParser;

public class GestUniverso {
	private DBConnector dbc;
	private ArrayList<String> tab_names; //Ordine di nomi di config.properties
	private ArrayList<ArrayList<String>> tab_attr;
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
	/**
	 * Gets Stella ArrayList from DB.
	 * @return
	 * @throws SQLException
	 * @throws ParsingException incorrect value for fase attribute in the database
	 */
	public ArrayList<Stella> getStelle() throws SQLException, ParsingException {
		ResultSet rs = dbc.query("SELECT * FROM "+tab_names.get(0));
		ArrayList<Stella> out = new ArrayList<>();
		while(rs.next()) {
			int id = rs.getInt(tab_attr.get(0).get(0));
			String nome = rs.getString(tab_attr.get(0).get(1));
			String sistema = rs.getString(tab_attr.get(0).get(2));
			int temperatura = rs.getInt(tab_attr.get(0).get(3));
			String fase_str = rs.getString(tab_attr.get(0).get(4));
			int idGalassia = rs.getInt(tab_attr.get(0).get(5));
			FaseStella fase = EnumParser.parseStella(fase_str);
			if(idGalassia == 0&&sistema == null) {
				//Constructor without idGalassia and sistema
				out.add(new Stella(id,nome,temperatura,fase));
			}else if(idGalassia == 0) {
				//Constructor without idGalassia
				out.add(new Stella(id,nome,sistema,temperatura,fase));
			}else if(sistema == null) {
				//Constructor without sistema
				out.add(new Stella(id,nome,temperatura,fase,idGalassia));
			}else{
				//Normal constructor
				out.add(new Stella(id,nome,sistema,temperatura,fase,idGalassia));
			}
		}
		return out;
	}
	/**
	 * Saves Stella ArrayList to DB.
	 * @param s
	 * @throws SQLException
	 */
	public void saveStelle(ArrayList<Stella> s) throws SQLException{
		//Deletes everything before saving new copy of data
		dbc.update("DELETE FROM "+tab_names.get(0));
		for(int i=0;i<s.size();i++) {
			if(s.get(i).getIdGalassia() == 0) {
				dbc.update("INSERT INTO "+tab_names.get(0)+" VALUES(?,?,?,?,?,?)", s.get(i).getIdStella(),s.get(i).getNome(),s.get(i).getSistema(),s.get(i).getTemperatura(),s.get(i).getFase().toString(),null);
			}else{
				dbc.update("INSERT INTO "+tab_names.get(0)+" VALUES(?,?,?,?,?,?)", s.get(i).getIdStella(),s.get(i).getNome(),s.get(i).getSistema(),s.get(i).getTemperatura(),s.get(i).getFase().toString(),s.get(i).getIdGalassia());
			}
		}
	}
}