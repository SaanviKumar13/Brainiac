package utils;

import models.Question;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class APIManager {
    public static List<Question> fetchQuestionsFromAPI() {
        List<Question> fetchedQuestions = new ArrayList<>();
        String apiUrl = "https://opentdb.com/api.php?amount=5&type=multiple"; // Adjust the API URL as needed

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            JSONObject json = new JSONObject(response.toString());
            JSONArray questionsArray = json.getJSONArray("results");

            for (int i = 0; i < questionsArray.length(); i++) {
                JSONObject questionJson = questionsArray.getJSONObject(i);
                String question = questionJson.getString("question");
                String correctAnswer = questionJson.getString("correct_answer");
                JSONArray incorrectAnswersArray = questionJson.getJSONArray("incorrect_answers");
                List<String> incorrectAnswers = new ArrayList<>();

                for (int j = 0; j < incorrectAnswersArray.length(); j++) {
                    incorrectAnswers.add(incorrectAnswersArray.getString(j));
                }

                fetchedQuestions.add(new Question(question, correctAnswer, incorrectAnswers));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fetchedQuestions;
    }
}