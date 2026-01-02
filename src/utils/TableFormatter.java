package utils;

import java.util.ArrayList;

import model.Stella;

public class TableFormatter {
	public static ArrayList<ArrayList<String>> parseToTable(ArrayList<Stella> al){
		ArrayList<ArrayList<String>> out = new ArrayList<>();
		ArrayList<String> tmp = new ArrayList<>();
		
		for(int i=0;i<al.size();i++) {
			tmp.add(String.valueOf(al.get(i).getIdStella()));
			tmp.add(al.get(i).getNome());
			tmp.add(al.get(i).getSistema());
			tmp.add(String.valueOf(al.get(i).getTemperatura()));
			tmp.add(al.get(i).getFase().toString());
			tmp.add(String.valueOf(al.get(i).getIdGalassia()));
		}
		
		return out;
	}
}