package com.example.game_of_bean_bags.handlers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class PositiveHandler extends Handler {
    public static final int WIN = 1;

    public PositiveHandler(Handler processor) {
        super(processor);
    }

    public boolean process(Integer request) {
        if (request == WIN) {
            System.out.println("DEBUG: PositiveHandler обрабатывает выигрыш");
            return true; // Просто возвращаем true при выигрыше
        }
        return super.process(request);
    }
} 