package com.example.game_of_bean_bags.handlers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class NegativeHandler extends Handler {
    public static final int LOSS = 3;

    public NegativeHandler(Handler processor) {
        super(processor);
    }

    public boolean process(Integer request) {
        if (request == LOSS) {
            System.out.println("DEBUG: NegativeHandler обрабатывает проигрыш");
            return false; // Просто возвращаем false при проигрыше
        }
        return super.process(request);
    }
} 