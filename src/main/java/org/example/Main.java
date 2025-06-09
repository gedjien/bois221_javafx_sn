package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final String BOT_TOKEN = "token";
    private static final String BOT_USERNAME = "BotName";

    public static void main(String[] args) {
        try {
            logger.info("Starting bot...");
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            StudyBot bot = new StudyBot(BOT_USERNAME, BOT_TOKEN);
            botsApi.registerBot(bot);
            logger.info("Bot started successfully!");
        } catch (TelegramApiException e) {
            logger.error("Error starting bot", e);
        }
    }
}