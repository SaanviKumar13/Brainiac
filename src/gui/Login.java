package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Login extends JFrame implements ActionListener {
    Login() {
        setTitle("Welcome to Brainiac!");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        ImageIcon title = new ImageIcon(ClassLoader.getSystemResource("icons/login.png"));
        JLabel img = new JLabel(title);
        img.setBounds(0, 150, 500, 700);
        add(img);
        JLabel heading = new JLabel("Brainiac");
        heading.setBounds(450, 10, 500, 100);
        heading.setFont(new Font("dialog", Font.BOLD, 72));
        heading.setForeground(new Color(124, 136, 108));
        add(heading);
        JLabel nameLabel = new JLabel("What's your name?");
        nameLabel.setBounds(800, 350, 300, 25);
        nameLabel.setFont(new Font("Mongolian Haiti", Font.BOLD, 20));
        nameLabel.setForeground(new Color(195, 213, 170));
        add(nameLabel);
        JTextField nameInput = new JTextField();
        nameInput.setBounds(800, 400, 200, 25);
        nameInput.setFont(new Font("sans", Font.CENTER_BASELINE, 16));
        nameInput.setForeground(new Color(195, 213, 170));
        add(nameInput);
        JButton btn = new JButton("Let's Play");
        btn.setBounds(850, 450, 100, 50);
        btn.setBackground(new Color(195, 213, 170));
        btn.addActionListener(this);
        add(btn);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new CategorySelectorApp();
    }

    public static void main(String[] args) {
        new Login();
    }
}
