package utils;

import java.util.ArrayList;

import leolib.dtformatters.IT;
import model.EventoCosmico;
import model.Galassia;
import model.Pianeta;
import model.Stella;

public class TableFormatter {

    public static ArrayList<ArrayList<String>> parseStelleToTable(ArrayList<Stella> al, ArrayList<ArrayList<String>> tab_attr) {
        ArrayList<ArrayList<String>> out = new ArrayList<>();
        out.add(tab_attr.get(0));
        ArrayList<String> tmp = new ArrayList<>();

        for (int i = 0; i < al.size(); i++) {
            tmp.clear();
            tmp.add(String.valueOf(al.get(i).getIdStella()));
            tmp.add(al.get(i).getNome());
            if (al.get(i).getSistema() == null) {
                tmp.add("null");
            } else {
                tmp.add(al.get(i).getSistema());
            }
            tmp.add(String.valueOf(al.get(i).getTemperatura()));
            tmp.add(al.get(i).getFase().toString());
            int idGal = al.get(i).getIdGalassia();
            tmp.add(idGal == 0 ? "null" : String.valueOf(idGal));
            out.add((ArrayList<String>) tmp.clone());
        }

        return out;
    }

    public static ArrayList<ArrayList<String>> parseECToTable(ArrayList<EventoCosmico> al, ArrayList<ArrayList<String>> tab_attr) {
        ArrayList<ArrayList<String>> out = new ArrayList<>();
        out.add(tab_attr.get(3));
        ArrayList<String> tmp = new ArrayList<>();

        for (int i = 0; i < al.size(); i++) {
            tmp.clear();
            tmp.add(String.valueOf(al.get(i).getIdEventoCosmico()));
            tmp.add(al.get(i).getNome());
            tmp.add(al.get(i).getTipo().toString());
            tmp.add(al.get(i).getDataEvento().format(IT.DATE));
            tmp.add(al.get(i).getOraEvento().format(IT.TIME));
            int id = al.get(i).getIdEventoCosmico();
            tmp.add(id == 0 ? "null" : String.valueOf(id));
            out.add((ArrayList<String>) tmp.clone());
        }

        return out;
    }

    public static ArrayList<ArrayList<String>> parseGalassieToTable(ArrayList<Galassia> al, ArrayList<ArrayList<String>> tab_attr) {
        ArrayList<ArrayList<String>> out = new ArrayList<>();
        out.add(tab_attr.get(2));
        ArrayList<String> tmp = new ArrayList<>();

        for (int i = 0; i < al.size(); i++) {
            tmp.clear();
            tmp.add(String.valueOf(al.get(i).getIdGalassia()));
            tmp.add(al.get(i).getNome());
            tmp.add(al.get(i).getTipo().toString());
            tmp.add(String.valueOf(al.get(i).getMassa()));
            out.add((ArrayList<String>) tmp.clone());
        }

        return out;
    }

    public static ArrayList<ArrayList<String>> parsePianetiToTable(ArrayList<Pianeta> al, ArrayList<ArrayList<String>> tab_attr) {
        ArrayList<ArrayList<String>> out = new ArrayList<>();
        out.add(tab_attr.get(1));
        ArrayList<String> tmp = new ArrayList<>();

        for (int i = 0; i < al.size(); i++) {
            tmp.clear();
            tmp.add(String.valueOf(al.get(i).getIdPianeta()));
            tmp.add(al.get(i).getNome());
            if (al.get(i).getSistema() == null) {
                tmp.add("null");
            } else {
                tmp.add(al.get(i).getSistema());
            }
            tmp.add(al.get(i).getTipo().toString());
            tmp.add(String.valueOf(al.get(i).getTemperatura()));
            int id = al.get(i).getIdGalassia();
            tmp.add(id == 0 ? "null" : String.valueOf(id));
            out.add((ArrayList<String>) tmp.clone());
        }

        return out;
    }
}
