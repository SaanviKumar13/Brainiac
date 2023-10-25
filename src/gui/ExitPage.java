package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class ExitPage extends JFrame {
    private JLabel messageLabel;
    private JLabel scoreLabel;

    public ExitPage(String message, int score) {
        setTitle("Your Results!");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        messageLabel = new JLabel(message);
        messageLabel.setFont(new Font("Serif", Font.BOLD, 48));
        messageLabel.setBounds(400, 100, 500, 100);
        messageLabel.setForeground(new Color(124, 136, 108));
        scoreLabel = new JLabel("You scored " + score + " out of 5");
        scoreLabel.setFont(new Font("Serif", Font.BOLD, 20));
        scoreLabel.setBounds(480, 200, 500, 100);
        add(messageLabel);
        add(scoreLabel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ExitPage("Congratulations!", 4);
        });
    }
}