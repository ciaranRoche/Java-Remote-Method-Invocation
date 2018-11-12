package controller;

import model.Calculator;

import java.rmi.Naming;

/*
Classname: CalculatorClient
Filename: CalculatorClient.java
Author: Ciaran Roche
Purpose: The Calculator Client which communicates with the RMI server and passes response back to UI
*/

public class CalculatorClient {

    public CalculatorClient(){}

    private double ans = 0.0;

    /*
    Calculate method which is called from the UI.
     */
    public double calculate(String type, double num1, double num2){
        try{
            Calculator obj = (Calculator) Naming.lookup("//localhost/Calculator");
            switch (type){
                case ("add"):
                    ans = obj.add(num1, num2);
                    break;
                case("subtract"):
                    ans = obj.subtract(num1, num2);
                    break;
                case("divide"):
                    ans = obj.divide(num1, num2);
                    break;
                case("multiply"):
                    ans = obj.multiply(num1, num2);
                    break;
                case("power"):
                    ans = obj.power(num1, num2);
                    break;
            }
        } catch (Exception e) {
            System.out.println("CalculatorClient exception : " + e.getMessage());
            e.printStackTrace();
        }
        return ans;
    }
}
