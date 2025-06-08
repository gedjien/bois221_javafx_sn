package com.example.game_of_bean_bags.handlers;

import java.util.Random;

public class ActionChain {
    private Handler chain;
    public static final int SUCCESS = 1;
    public static final int CHANCE = 2;
    public static final int LOSS = 3;
    private Random generate;
    private final int NUMHANDLER = 10;

    public ActionChain() {
        generate = new Random();
        buildChain();
    }

    private void buildChain() {
        chain = new NegativeHandler(
            new ChanceHandler(
                new PositiveHandler(null)
            )
        );
    }

    public boolean process() {
        int type = generate.nextInt(NUMHANDLER) + 1;
        System.out.println("DEBUG: Сгенерирован тип события: " + type + 
                         " (1-2=Выигрыш, 3-4=Шанс, 5-10=Проигрыш)");
        
        if (type <= 2) {
            type = SUCCESS;
        } else if (type <= 4) {
            type = CHANCE;
        } else {
            type = LOSS;
        }
        
        return process(type);
    }

    public boolean process(Integer a) {
        System.out.println("DEBUG: Передача события " + a + " в цепочку обработчиков");
        boolean result = chain.process(a);
        System.out.println("DEBUG: Результат обработки: " + (result ? "Выигрыш" : "Проигрыш"));
        return result;
    }
} 