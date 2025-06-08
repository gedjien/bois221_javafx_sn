package com.example.game_of_bean_bags.handlers;

public class ChanceHandler extends Handler {
    public static final int CHANCE = 2;

    public ChanceHandler(Handler processor) {
        super(processor);
    }

    public boolean process(Integer request) {
        if (request == CHANCE) {
            System.out.println("DEBUG: ChanceHandler обрабатывает шанс");
            return true; // При шансе всегда даем возможность сыграть еще раз
        }
        return super.process(request);
    }
} 