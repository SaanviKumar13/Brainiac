package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import controllers.QuizController;
import models.Question;
import utils.DatabaseManager;

public class QuizAppGUI extends JFrame {
    private JLabel questionLabel;
    private JButton[] optionButtons;
    private JButton nextButton;
    private QuizController quizController;
    private int correctAnswerIndex;
    int score;

    public QuizAppGUI(String username) {
        setTitle("Brainiac");
        getContentPane().setBackground(new Color(229, 227, 204));
        quizController = new QuizController();

        JPanel mainPanel = new JPanel(new BorderLayout());

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        mainPanel.add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        optionButtons = new JButton[4];
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JButton();
            optionButtons[i].setFont(new Font("Arial", Font.PLAIN, 20));
            optionButtons[i].setBackground(new Color(229, 227, 204));
            optionsPanel.add(optionButtons[i]);
            optionButtons[i].addActionListener(new OptionButtonListener());
        }
        optionsPanel.setBackground(new Color(229, 227, 204));
        mainPanel.add(optionsPanel, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        nextButton.setBackground(new Color(193, 211, 169));
        nextButton.setFont(new Font("Arial", Font.PLAIN, 20));
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextQuestion(username);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        buttonPanel.add(nextButton);
        buttonPanel.setBackground(new Color(229, 227, 204));
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.setBackground(new Color(229, 227, 204));
        add(mainPanel);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        displayQuestion();
    }

    private void displayQuestion() {
        Question currentQuestion = quizController.getCurrentQuestion();
        questionLabel.setText("<html>" + currentQuestion.getQuestion() + "</html>");

        List<String> options = currentQuestion.getOptions();
        correctAnswerIndex = currentQuestion.getCorrectAnswerIndex();
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(options.get(i));
            optionButtons[i].setBackground(Color.WHITE);
            optionButtons[i].setEnabled(true);
        }
    }

    private void markCorrectAnswer(int correctIndex) {
        optionButtons[correctIndex].setBackground(Color.GREEN);
        score++;
    }

    private void markSelectedAnswer(int selectedIndex) {
        optionButtons[selectedIndex].setBackground(Color.RED);
    }

    private void disableOptionButtons() {
        for (JButton button : optionButtons) {
            button.setEnabled(false);
        }
    }

    private class OptionButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton selectedButton = (JButton) e.getSource();
            disableOptionButtons();

            for (int i = 0; i < 4; i++) {
                if (selectedButton == optionButtons[i]) {
                    markSelectedAnswer(i);

                    if (i == correctAnswerIndex) {
                        markCorrectAnswer(correctAnswerIndex);
                    }
                    break;
                }
            }

            nextButton.setEnabled(true);
        }
    }

    private void nextQuestion(String username) {
        if (quizController.hasNextQuestion()) {
            quizController.moveToNextQuestion();
            displayQuestion();
            nextButton.setEnabled(false);
        } else {
            int finalScore = score; // Get the final score
            DatabaseManager.insertScore(username, finalScore); // Insert the score into the database
            JOptionPane.showMessageDialog(this, "Quiz completed! Your score: " + finalScore);
            setVisible(false);
            new LeaderboardPage();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new QuizAppGUI("");
            }
        });
    }
}