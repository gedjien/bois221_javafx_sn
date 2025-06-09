package org.example.senders;

import org.example.model.BankQuestion;
import org.example.model.Question;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class LearnSender implements StudySender {
    private final BankQuestion bank;
    private Question currentQuestion;

    public LearnSender() {
        this.bank = new BankQuestion();
        this.currentQuestion = null;
    }

    @Override
    public void processMessage(Long chatId, String message, TelegramLongPollingBot bot) {
        SendMessage response = new SendMessage();
        response.setChatId(chatId.toString());

        if (currentQuestion == null || message.equals("Следующий вопрос")) {
            currentQuestion = bank.getRandomQuestion();
            response.setText(currentQuestion.getQuestion());
        } else {
            if (currentQuestion.checkAnswer(message)) {
                response.setText("Правильно! Нажмите 'Следующий вопрос' для продолжения.");
            } else {
                response.setText("Неправильно. Правильный ответ: " + currentQuestion.getAnswer() + 
                               "\nНажмите 'Следующий вопрос' для продолжения.");
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
        }

        // Добавляем кнопки управления
        KeyboardRow controlRow = new KeyboardRow();
        controlRow.add("Следующий вопрос");
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