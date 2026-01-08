package main;

import userinterface.Cli;

public class Main {
    public static void main(String[] args) {
        Cli cli = new Cli();
        if(cli.getGest().getDbc().testConnection()) {
        	System.out.println("La connessione funziona!");
        }else{
        	System.out.println("==========================================================");
            System.out.println("ERRORE! Database non connesso! Controlla che: \n"
                    + "- Il server sia acceso\n"
                    + "- Le credenziali siano corrette\n"
                    + "- L'utente abbia accesso al database");
            System.out.println("==========================================================");
        }
        cli.startMessage();
    }
}
