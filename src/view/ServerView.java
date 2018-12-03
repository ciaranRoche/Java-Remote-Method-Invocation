package view;

import javax.swing.*;
import java.awt.*;

public class ServerView {

    final JTextArea area = new JTextArea();

    public ServerView(){
        JFrame frame = new JFrame("RMI Server");
        frame.add(new JScrollPane(area), BorderLayout.CENTER);
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    public void handleAction(String s){
        area.append(s + '\n');
    }
}
