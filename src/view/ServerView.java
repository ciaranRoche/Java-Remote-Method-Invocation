package view;

import controller.CalculatorServer;
import model.Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/*
Classname: ServerView
Filename: ServerView.java
Author: Ciaran Roche
Purpose: Server UI to provide a text output for server
*/
public class ServerView implements ActionListener {

    final JTextArea area = new JTextArea();
    final JButton calcBtn;

    public ServerView(){
        JFrame frame = new JFrame("RMI Server");
        calcBtn = new JButton("Calculator");

        calcBtn.addActionListener(this);

        frame.add(new JScrollPane(area), BorderLayout.CENTER);
        frame.add(calcBtn, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    public void handleAction(String s){
        area.append(s + '\n');
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Calculator")){
            new CalculatorView();
        }
    }
}

