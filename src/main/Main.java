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
        
        //MAIN MENU
        //I SOTTOMENU NON SONO FINITI QUI! DEVI FARE UN ALTRO SWITCH PER OGNI SOTTOMENU CON LE LORO OPZIONI!!!
        switch(cli.mainMenu()) {
        case 1:
        	//MENU STELLE
        	cli.stelleMenu();
        	break;
        case 2: 
        	//MENU PIANETI
        	cli.pianetiMenu();
        	break;
        case 3:
        	//MENU GALASSIE
        	cli.galassieMenu();
        	break;
        case 4:
        	//MENU EVENTI COSIMICI
        	cli.eventicosmiciMenu();
        	break;
        }
    }
}