package org.example;

import org.example.senders.ExamSender;
import org.example.senders.LearnSender;
import org.example.senders.StudySender;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudyBot extends TelegramLongPollingBot {
    private final Map<Long, StudySender> sessions = new HashMap<>();
    private final String botUsername;
    private final String botToken;

    public StudyBot(String botUsername, String botToken) {
        this.botUsername = botUsername;
        this.botToken = botToken;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage()) return;
        
        var chatId = update.getMessage().getChatId();
        var text = update.getMessage().getText();

        if (text == null) return;

        if (text.equals("/start")) {
            showMainMenu(chatId);
            return;
        }

        StudySender session = sessions.get(chatId);
        if (session == null) {
            switch (text) {
                case "Режим обучения":
                    session = new LearnSender();
                    break;
                case "Режим экзамена":
                    session = new ExamSender();
                    break;
                default:
                    showMainMenu(chatId);
                    return;
            }
            sessions.put(chatId, session);
        }

        try {
            if (text.equals("Назад")) {
                sessions.remove(chatId);
                showMainMenu(chatId);
            } else {
                session.processMessage(chatId, text, this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showMainMenu(Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText("Выберите режим работы:");

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setSelective(true);

        List<KeyboardRow> keyboard = new ArrayList<>();
        
        KeyboardRow row = new KeyboardRow();
        row.add("Режим обучения");
        row.add("Режим экзамена");
        keyboard.add(row);

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
} 