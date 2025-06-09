package org.example.senders;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface Sender {
    void onMessageReceived(String message);
    SendMessage createSendMessage();
}