package model;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
Classname: Calculator
Filename: Calculator.java
Author: Ciaran Roche
Purpose: The Calculator Interface of the RMI server.
*/

public interface Calculator extends Remote {
    double add(double num1, double num2) throws RemoteException;
    double subtract(double num1, double num2) throws RemoteException;
    double multiply(double num1, double num2) throws RemoteException;
    double divide(double num1, double num2) throws RemoteException;
    double power(double num1, double num2) throws RemoteException;
}


