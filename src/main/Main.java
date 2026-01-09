package main;

import userinterface.Cli;

public class Main {

    public static void main(String[] args) {
        //Create Cli object
        Cli cli = new Cli();

        //Test connection to the database
        cli.testDBConnection();

        //Displays start message
        cli.startMessage();
        
        do {
            //MAIN MENU
        	switch(cli.mainMenu()) {
            case 1:
            	//MENU STELLE
            	stelleMenu(cli);
            	break;
            case 2: 
            	//MENU PIANETI
            	pianetiMenu(cli);
            	break;
            case 3:
            	//MENU GALASSIE
            	galassieMenu(cli);
            	break;
            case 4:
            	//MENU EVENTI COSIMICI
            	ecMenu(cli);
            	break;
            case 0:
            	System.out.println("Applicazione terminata.");
            	return;
            }
        }while(true);
    }
    
    //Funzioni usate solo per poter tornare indietro con return
    
    private static void stelleMenu(Cli cli) {
    	do {
    		switch(cli.stelleMenu()) {
        	case 1:
        		cli.listStelle();
        		break;
        	case 2:
        		cli.addStella();
        		break;
        	case 3:
        		cli.removeStella();
        		break;
        	case 0:
        		return;
        	}
    	}while(true);
    }
    private static void pianetiMenu(Cli cli) {
    	do {
    		switch(cli.pianetiMenu()) {
        	case 1:
        		cli.listPianeti();
        		break;
        	case 2:
        		cli.addPianeta();
        		break;
        	case 3:
        		cli.removePianeta();
        		break;
        	case 0:
        		return;
    		}
    	}while(true);
    }
    private static void galassieMenu(Cli cli) {
    	do {
    		switch(cli.galassieMenu()) {
        	case 1:
        		cli.listGalassie();
        		break;
        	case 2:
        		cli.addGalassia();
        		break;
        	case 3:
        		cli.removeGalassia();
        		break;
        	case 0:
        		return;
        	}
    	}while(true);
    }
    private static void ecMenu(Cli cli) {
    	do {
    		switch(cli.eventicosmiciMenu()) {
        	case 1:
        		cli.listEventiCosmici();
        		break;
        	case 2:
        		cli.addEventoCosmico();
        		break;
        	case 3:
        		cli.removeEventoCosmico();
        		break;
        	case 0:
        		return;
        	}
    	}while(true);
    }
}