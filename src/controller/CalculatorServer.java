package controller;

import com.sun.security.ntlm.Server;
import model.Calculator;
import view.CalculatorView;
import view.ServerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;


/*
Classname: CalculatorServer
Filename: CalculatorServer.java
Author: Ciaran Roche
Purpose: The RMI server which handles calculator mathematical methods.
*/

public class CalculatorServer extends UnicastRemoteObject implements Calculator {

    static ServerView view = new ServerView();

    /*
    Class constructor inherits from UnicastRemoteObject
     */
    private CalculatorServer() throws RemoteException {
        super();
    }


    @Override
    public double add(double num1, double num2) throws RemoteException {
        handleClientConnectionDetails();
        view.handleAction("Add Method Called : " + num1 + " + " + num2 + "\nData Passed Back to Client : " + (num1 + num2) );
        return num1 + num2;
    }

    @Override
    public double subtract(double num1, double num2) throws RemoteException {
        handleClientConnectionDetails();
        view.handleAction("Subtract Method Called : "  + num1 + " - " + num2 + "\nData Passed Back to Client : " + (num1 - num2));
        return num1 - num2;
    }

    @Override
    public double multiply(double num1, double num2) throws RemoteException {
        handleClientConnectionDetails();
        view.handleAction("Multiply Method Called : "  + num1 + " * " + num2 + "\nData Passed Back to Client : " + (num1 * num2));
        return num1 * num2;
    }

    @Override
    public double divide(double num1, double num2) throws RemoteException {
        handleClientConnectionDetails();
        view.handleAction("Divide Method Called : "  + num1 + " + " + num2 + "\nData Passed Back to Client : " + (num1 / num2));
        return num1 / num2;
    }

    @Override
    public double power(double num1, double num2) throws RemoteException {
        handleClientConnectionDetails();
        view.handleAction("Power Method Called : "  + num1 + " ^ " + num2 + "\nData Passed Back to Client : " + (Math.pow(num1,num2)));
        return Math.pow(num1, num2);
    }

    /*
    Prints
     */
    public void handleClientConnectionDetails(){
        try {
            view.handleAction("Client Connected at : " + getClientHost());
        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }
    }

    /*
    Main method creates a RMI object
     */
    public static void main(String ... args){
        try{
            CalculatorServer obj = new CalculatorServer();

            Registry registry = LocateRegistry.createRegistry(1099);

            registry.rebind("Calculator", obj);

            view.handleAction("CalculatorServer bound in registry");

        } catch (Exception e) {
            System.out.println("CalculatorServer error : " + e.getMessage());
            e.printStackTrace();
        }
    }

}
