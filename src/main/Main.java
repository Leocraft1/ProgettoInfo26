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
            switch (cli.mainMenu()) {
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
        } while (true);
    }

    // Menu Stelle
    private static void stelleMenu(Cli cli) {
        do {
            switch (cli.stelleMenu()) {
                case 1:
                    cli.listStelle();
                    break;
                case 2:
                    cli.addStella();
                    break;
                case 3:
                    cli.removeStella();
                    break;
                case 4:
                    cli.stellePerFase();
                    break;
                case 5:
                    cli.stellePerMinTemperatura();
                    break;
                case 6:
                    cli.stellaPiuCalda();
                    break;
                case 7:
                    cli.stelleSenzaGalassia();
                    break;
                case 8:
                    cli.countStelleInGalassiaCLI();
                    break;
                case 0:
                    return;
            }
        } while (true);
    }

// Menu Pianeti
    private static void pianetiMenu(Cli cli) {
        do {
            switch (cli.pianetiMenu()) {
                case 1:
                    cli.listPianeti();
                    break;
                case 2:
                    cli.addPianeta();
                    break;
                case 3:
                    cli.removePianeta();
                    break;
                case 4:
                    cli.pianetiPerTipo();
                    break;
                case 5:
                    cli.pianetiPerRangeTemperatura();
                    break;
                case 6:
                    cli.pianetaPiuCaldo();
                    break;
                case 7:
                    cli.pianetiSenzaSistema();
                    break;
                case 8:
                    cli.countPianetiInGalassia();
                    break;
                case 0:
                    return;
            }
        } while (true);
    }

// Menu Galassie
    private static void galassieMenu(Cli cli) {
        do {
            switch (cli.galassieMenu()) {
                case 1:
                    cli.listGalassie();
                    break;
                case 2:
                    cli.addGalassia();
                    break;
                case 3:
                    cli.removeGalassia();
                    break;
                case 4:
                    cli.galassiePerTipo();
                    break;
                case 5:
                    cli.galassiePerMinMassa();
                    break;
                case 6:
                    cli.galassiaPiuPesante();
                    break;
                case 7:
                    cli.galassieSenzaStelle();
                    break;
                case 8:
                    cli.countStelleInGalassiaCLI();
                case 0:
                    return;
            }
        } while (true);
    }

// Menu Eventi Cosmici
    private static void ecMenu(Cli cli) {
        do {
            switch (cli.eventicosmiciMenu()) {
                case 1:
                    cli.listEventiCosmici();
                    break;
                case 2:
                    cli.addEventoCosmico();
                    break;
                case 3:
                    cli.removeEventoCosmico();
                    break;
                case 4:
                    cli.eventiPerTipo();
                    break;
                case 5:
                    cli.eventiDopoData();
                    break;
                case 6:
                    cli.eventiSenzaStella();
                    break;
                case 7:
                    cli.countEventiPerStella();
                    break;
                case 8:
                    cli.ultimoEventoCosmico();
                case 0:
                    return;
            }
        } while (true);
    }
}
