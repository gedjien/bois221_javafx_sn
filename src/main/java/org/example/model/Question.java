package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Question {
    private final String question;
    private final String answer;
    private String explanation;
    private final List<String> correctAnswers = new ArrayList<>();
    private final List<String> wrongAnswers = new ArrayList<>();

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.correctAnswers.add(answer);
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getExplanation() {
        return explanation;
    }

    public void addWrongAnswers(String... answers) {
        Collections.addAll(wrongAnswers, answers);
    }

    public List<String> getAnswerOptions() {
        List<String> options = new ArrayList<>();
        // Добавляем все правильные ответы
        options.addAll(correctAnswers);
        
        // Добавляем случайные неправильные ответы
        List<String> shuffledWrong = new ArrayList<>(wrongAnswers);
        Collections.shuffle(shuffledWrong);
        // Добавляем до 4 неправильных ответов
        int wrongToAdd = Math.min(4, shuffledWrong.size());
        options.addAll(shuffledWrong.subList(0, wrongToAdd));

        Collections.shuffle(options);
        return options;
    }

    public boolean checkAnswer(String answer) {
        return correctAnswers.contains(answer);
    }

    public String getCorrectAnswers() {
        return String.join(", ", correctAnswers);
    }
}