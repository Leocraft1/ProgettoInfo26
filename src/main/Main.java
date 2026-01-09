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
    }
}
