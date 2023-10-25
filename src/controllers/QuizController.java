package controllers;

import models.Question;
import utils.APIManager;

import java.util.List;

public class QuizController {
    private List<Question> questions;
    private int currentQuestionIndex = 0;

    public QuizController() {
        questions = APIManager.fetchQuestionsFromAPI();
    }

    public Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    public boolean hasNextQuestion() {
        return currentQuestionIndex < questions.size() - 1;
    }

    public void moveToNextQuestion() {
        if (hasNextQuestion()) {
            currentQuestionIndex++;
        }
    }
}
