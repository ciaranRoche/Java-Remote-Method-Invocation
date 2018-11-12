package view;

import controller.CalculatorClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import static java.lang.Character.isDigit;

/*
Classname: CalculatorView
Filename: CalculatorView.java
Author: Ciaran Roche
Purpose: The Calculator UI which implements ActionListener and parses calculator inputs and displays RMI response.
*/

public class CalculatorView implements ActionListener {

    private JTextField inputTextField;

    private String output;
    private Stack<Double> memory;
    private Stack<Character>stack;
    private String ans, postfix;
    private CalculatorClient calc;

    /*
    Class constructor to build Swing UI
     */
    public CalculatorView(){
        calc = new CalculatorClient();
        stack = new Stack<>();
        memory = new Stack<>();
        output = "";
        ans = "";
        postfix = "";

        JFrame jFrame = new JFrame("RMI Calculator");

        inputTextField = new JTextField();
        inputTextField.setBounds(30,40,280,30);
        inputTextField.addActionListener(this);
        jFrame.add(inputTextField);

        JButton btn1 = new JButton("1");
        btn1.setBounds(40,240,50,40);
        btn1.addActionListener(this);
        jFrame.add(btn1);

        JButton btn2 = new JButton("2");
        btn2.setBounds(110,240,50,40);
        btn2.addActionListener(this);
        jFrame.add(btn2);

        JButton btn3 = new JButton("3");
        btn3.setBounds(180,240,50,40);
        btn3.addActionListener(this);
        jFrame.add(btn3);

        JButton btn4 = new JButton("4");
        btn4.setBounds(40,170,50,40);
        btn4.addActionListener(this);
        jFrame.add(btn4);

        JButton btn5 = new JButton("5");
        btn5.setBounds(110,170,50,40);
        btn5.addActionListener(this);
        jFrame.add(btn5);

        JButton btn6 = new JButton("6");
        btn6.setBounds(180,170,50,40);
        btn6.addActionListener(this);
        jFrame.add(btn6);

        JButton btn7 = new JButton("7");
        btn7.setBounds(40,100,50,40);
        btn7.addActionListener(this);
        jFrame.add(btn7);

        JButton btn8 = new JButton("8");
        btn8.setBounds(110,100,50,40);
        btn8.addActionListener(this);
        jFrame.add(btn8);

        JButton btn9 = new JButton("9");
        btn9.setBounds(180,100,50,40);
        btn9.addActionListener(this);
        jFrame.add(btn9);

        JButton btn0 = new JButton("0");
        btn0.setBounds(110,310,50,40);
        btn0.addActionListener(this);
        jFrame.add(btn0);

        JButton btnDiv = new JButton("/");
        btnDiv.setBounds(250,100,50,40);
        btnDiv.addActionListener(this);
        jFrame.add(btnDiv);

        JButton btnMul = new JButton("*");
        btnMul.setBounds(250,170,50,40);
        btnMul.addActionListener(this);
        jFrame.add(btnMul);

        JButton btnSub = new JButton("-");
        btnSub.setBounds(250,240,50,40);
        btnSub.addActionListener(this);
        jFrame.add(btnSub);

        JButton btnAdd = new JButton("+");
        btnAdd.setBounds(250,310,50,40);
        btnAdd.addActionListener(this);
        jFrame.add(btnAdd);

        JButton btnDec = new JButton(".");
        btnDec.setBounds(40,310,50,40);
        btnDec.addActionListener(this);
        jFrame.add(btnDec);

        JButton btnPow = new JButton("^");
        btnPow.setBounds(180,380,50,40);
        btnPow.addActionListener(this);
        jFrame.add(btnPow);

        JButton btnClr = new JButton("Clear");
        btnClr.setBounds(250,380,50,40);
        btnClr.addActionListener(this);
        jFrame.add(btnClr);

        JButton btnEql = new JButton("=");
        btnEql.setBounds(180,310,50,40);
        btnEql.addActionListener(this);
        jFrame.add(btnEql);

        JButton btnBra1 = new JButton("(");
        btnBra1.setBounds(40,380,50,40);
        btnBra1.addActionListener(this);
        jFrame.add(btnBra1);

        JButton btnBra2 = new JButton(")");
        btnBra2.setBounds(110,380,50,40);
        btnBra2.addActionListener(this);
        jFrame.add(btnBra2);


        jFrame.setLayout(null);
        jFrame.setVisible(true);
        jFrame.setSize(350,500);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
    }


    /*
    Inherited method to handle UI Actions
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        // switch statement to handle different actions.
        switch (e.getActionCommand()) {
            case("Clear"):
                inputTextField.setText(""); //if action clear, clear textfield
                break;
            case("="):
                infixToPostfix(inputTextField.getText()); //if action equals, pass textfield text to parse method
                break;
                default: inputTextField.setText(inputTextField.getText().concat(action)); //default action builds textfield
        }
    }


    /*
    Calculate method which parses the reverse polish notation to a stack
    Based on the top element in the stack it calls the calculator client method which in turn invokes methods in the RMI
     */
    private void calculate(String output){
        memory.clear();
        ans = "";
        double a;
        double b;
        String[] tokens = output.split(" ");
        for (String currentElement : tokens) {
            if ((!currentElement.equals("+")) && (!currentElement.equals("-"))
                    && (!currentElement.equals("*")) && (!currentElement.equals("/"))
                    && (!currentElement.equals("^"))) {
                memory.push(Double.parseDouble(currentElement));
            } else if (memory.size() >= 2) {
                if (currentElement.equals("+")) {
                    b = memory.pop();
                    a = memory.pop();
                    memory.push(calc.calculate("add", a, b));
                }
                if (currentElement.equals("-")) {
                    b = memory.pop();
                    a = memory.pop();
                    memory.push(calc.calculate("minus", a, b));
                }
                if (currentElement.equals("*")) {
                    b = memory.pop();
                    a = memory.pop();
                    memory.push(calc.calculate("multiply", a, b));
                }
                if (currentElement.equals("/")) {
                    b = memory.pop();
                    a = memory.pop();
                    memory.push(calc.calculate("divide", a, b));
                }
                if (currentElement.equals("^")) {
                    b = memory.pop();
                    a = memory.pop();
                    memory.push(calc.calculate("power", a, b));
                }
            }
        }
        inputTextField.setText(ans + memory.pop());

    }

    /*
    Parse textfield string to reverse polish notation for easier handling on the RMI
     */
    private void infixToPostfix(String s){
        for(int i = 0; i< s.length(); i++){
            char in = s.charAt(i);
            if((!isDigit(in)) && (in!='.') && (in!='(') && (in!=')')){
                char filler = ' ';
                output = output + filler;
            }
            switch (in){
                case '+':
                case '-':
                    operatorPriority(in, 1);
                    break;
                case '*':
                case '/':
                    operatorPriority(in, 2);
                    break;
                case '^':
                    while(!stack.isEmpty()){
                        int prec1 = 3;
                        char topStack = stack.pop();
                        if (topStack == '('){
                            stack.push(topStack);
                            break;
                        }else{
                            int priority2;
                            if(topStack == '+' || topStack == '-') {
                                priority2 = 1;
                            }else {
                                priority2 = 2;
                            }
                            if(topStack == '*' || topStack == '/') {
                                priority2 = 1;
                            }else{
                                priority2 = 2;
                            }
                            if(priority2 < prec1){
                                stack.push(topStack);
                                break;
                            }else output = output + topStack + ' ';
                        }
                    }
                    stack.push(in);
                    break;
                case '(':
                    stack.push(in);
                    break;
                case ')':
                    while(!stack.isEmpty()){
                        char per = stack.pop();
                        if(per=='('){
                            break;
                        }else{
                            output = output + " " + per;
                        }
                    }
                    break;
                default:
                    output = output + in;
                    break;
            }
        }
        while(!stack.isEmpty()){
            output = output + ' ' + stack.pop();
        }
        postfix = output;
        System.out.println("Reverse Polish Notation : " + postfix);
        calculate(output);
    }

    /*
    Method to give priority to operators
     */
    private void operatorPriority(char operatorThis, int priority1){
        while(!stack.isEmpty()){
            char topStack = stack.pop();
            if (topStack == '('){
                stack.push(topStack);
                break;
            }else{
                int priority2;
                if(topStack == '+' || topStack == '-') {
                    priority2 = 1;
                }else {
                    priority2 = 2;
                }
                if(priority2 < priority1){
                    stack.push(topStack);
                    break;
                }else output = output + topStack + ' ';
            }
        }
        stack.push(operatorThis);
    }



}
