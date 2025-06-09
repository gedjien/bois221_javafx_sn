package org.example.senders;

import org.example.model.BankQuestion;
import org.example.model.Question;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamSender implements StudySender {
    private final BankQuestion bank;
    private Question currentQuestion;
    private final Map<Long, Integer> scores;
    private final Map<Long, Integer> questionCount;
    private static final int QUESTIONS_PER_EXAM = 5;

    public ExamSender() {
        this.bank = new BankQuestion();
        this.currentQuestion = null;
        this.scores = new HashMap<>();
        this.questionCount = new HashMap<>();
    }

    @Override
    public void processMessage(Long chatId, String message, TelegramLongPollingBot bot) {
        SendMessage response = new SendMessage();
        response.setChatId(chatId.toString());

        // Инициализация счетчиков для нового пользователя
        scores.putIfAbsent(chatId, 0);
        questionCount.putIfAbsent(chatId, 0);

        if (currentQuestion == null || message.equals("Начать заново")) {
            // Начало нового экзамена
            scores.put(chatId, 0);
            questionCount.put(chatId, 0);
            currentQuestion = bank.getRandomQuestion();
            response.setText("Экзамен начат!\n\nВопрос 1/" + QUESTIONS_PER_EXAM + ":\n" + currentQuestion.getQuestion());
        } else {
            int currentScore = scores.get(chatId);
            int currentQuestionNum = questionCount.get(chatId);

            if (currentQuestion.checkAnswer(message)) {
                currentScore++;
                scores.put(chatId, currentScore);
            }

            currentQuestionNum++;
            questionCount.put(chatId, currentQuestionNum);

            if (currentQuestionNum >= QUESTIONS_PER_EXAM) {
                // Экзамен завершен
                double percentage = (currentScore * 100.0) / QUESTIONS_PER_EXAM;
                response.setText(String.format(
                    "Экзамен завершен!\nВаш результат: %d из %d (%.1f%%)\n\nНажмите 'Начать заново' для повторной попытки.",
                    currentScore, QUESTIONS_PER_EXAM, percentage
                ));
                currentQuestion = null;
            } else {
                // Следующий вопрос
                currentQuestion = bank.getRandomQuestion();
                response.setText(String.format(
                    "Вопрос %d/%d:\n%s",
                    currentQuestionNum + 1, QUESTIONS_PER_EXAM, currentQuestion.getQuestion()
                ));
            }
        }

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setSelective(true);

        List<KeyboardRow> keyboard = new ArrayList<>();

        if (currentQuestion != null) {
            // Добавляем варианты ответов
            List<String> options = currentQuestion.getAnswerOptions();
            for (String option : options) {
                KeyboardRow row = new KeyboardRow();
                row.add(option);
                keyboard.add(row);
            }
        } else {
            // Если экзамен завершен, показываем только кнопки управления
            KeyboardRow row = new KeyboardRow();
            row.add("Начать заново");
            keyboard.add(row);
        }

        // Добавляем кнопку "Назад"
        KeyboardRow controlRow = new KeyboardRow();
        controlRow.add("Назад");
        keyboard.add(controlRow);

        keyboardMarkup.setKeyboard(keyboard);
        response.setReplyMarkup(keyboardMarkup);

        try {
            bot.execute(response);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}