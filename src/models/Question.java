package models;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String question;
    private String correctAnswer;
    private List<String> incorrectAnswers;

    public Question(String question, String correctAnswer, List<String> incorrectAnswers) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> getOptions() {
        List<String> options = new ArrayList<>();

        for (String incorrectAnswer : incorrectAnswers) {
            options.add(incorrectAnswer);
        }
        options.add(correctAnswer);

        return options;
    }

    public int getCorrectAnswerIndex() {
        return getOptions().indexOf(correctAnswer);
    }
}