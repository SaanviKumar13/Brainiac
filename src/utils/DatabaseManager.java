package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import models.Question;
import utils.APIManager;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/brainiac?user=root"; // Modify the URL
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void insertQuestions(List<Question> questions) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load the MySQL JDBC driver

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String insertQuery = "INSERT INTO questions (question, correct_answer, option1, option2, option3, option4) "
                        + "VALUES (?, ?, ?, ?, ?, ?)";

                for (Question question : questions) {
                    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                    preparedStatement.setString(1, question.getQuestion());
                    preparedStatement.setString(2, question.getCorrectAnswer());

                    List<String> options = question.getOptions();
                    for (int i = 0; i < options.size(); i++) {
                        preparedStatement.setString(i + 3, options.get(i));
                    }

                    preparedStatement.executeUpdate();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<Question> fetchedQuestions = APIManager.fetchQuestionsFromAPI();
        insertQuestions(fetchedQuestions);
    }
}
