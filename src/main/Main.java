package main;

import controller.CalculatorServer;
import view.CalculatorView;

import java.rmi.RemoteException;

/*
Classname: Main
Filename: Main.java
Author: Ciaran Roche
Purpose: Main Class that launches RMI Server and Calculator Application.
*/

public class Main {

    public static void main(String args[]) throws RemoteException {
        CalculatorServer.main();
        new CalculatorView();
    }
}
