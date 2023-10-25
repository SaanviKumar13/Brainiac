package gui;

import java.awt.*;
import javax.swing.*;

public class Quiz extends JFrame {

    Quiz() {
        setTitle("Welcome to Brainiac!");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Quiz();
    }
}
