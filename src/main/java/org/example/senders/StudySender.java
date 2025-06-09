package org.example.senders;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public interface StudySender {
    void processMessage(Long chatId, String message, TelegramLongPollingBot bot);
}