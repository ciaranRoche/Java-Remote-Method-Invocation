package controller;

import model.Calculator;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


/*
Classname: CalculatorServer
Filename: CalculatorServer.java
Author: Ciaran Roche
Purpose: The RMI server which handles calculator mathematical methods.
*/

public class CalculatorServer extends UnicastRemoteObject implements Calculator {

    /*
    Class constructor inherits from UnicastRemoteObject
     */
    private CalculatorServer() throws RemoteException {
        super();
    }


    @Override
    public double add(double num1, double num2) throws RemoteException {
        System.out.println("Add Method Called : " + num1 + " + " + num2  );
        return num1 + num2;
    }

    @Override
    public double subtract(double num1, double num2) throws RemoteException {
        System.out.println("Subtract Method Called : "  + num1 + " - " + num2);
        return num1 - num2;
    }

    @Override
    public double multiply(double num1, double num2) throws RemoteException {
        System.out.println("Multiply Method Called : "  + num1 + " * " + num2);
        return num1 * num2;
    }

    @Override
    public double divide(double num1, double num2) throws RemoteException {
        System.out.println("Divide Method Called : "  + num1 + " + " + num2);
        return num1 / num2;
    }

    @Override
    public double power(double num1, double num2) throws RemoteException {
        System.out.println("Power Method Called : "  + num1 + " ^ " + num2);
        return Math.pow(num1, num2);
    }

    /*
    Main method creates a RMI object
     */
    public static void main(String ... args){
        try{
            CalculatorServer obj = new CalculatorServer();

            Registry registry = LocateRegistry.createRegistry(1099);

            registry.rebind("Calculator", obj);

            System.out.println("CalculatorServer bound in registry");

        } catch (Exception e) {
            System.out.println("CalculatorServer error : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
