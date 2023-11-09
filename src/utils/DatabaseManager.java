package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import models.Question;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/brainiac?user=root";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234";

    public static void insertQuestions(List<Question> questions) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String insertQuestionQuery = "INSERT INTO questions (question, correct_answer, option1, option2, option3, option4) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            for (Question question : questions) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuestionQuery)) {
                    preparedStatement.setString(1, question.getQuestion());
                    preparedStatement.setString(2, question.getCorrectAnswer());

                    List<String> options = question.getOptions();
                    for (int i = 0; i < options.size(); i++) {
                        preparedStatement.setString(i + 3, options.get(i));
                    }

                    preparedStatement.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertScore(String username, int score) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String insertScoreQuery = "INSERT INTO leaderboard (username, score) VALUES (?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertScoreQuery)) {
                preparedStatement.setString(1, username);
                preparedStatement.setInt(2, score);

                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}