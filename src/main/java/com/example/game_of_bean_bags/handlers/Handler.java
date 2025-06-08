package com.example.game_of_bean_bags.handlers;

public abstract class Handler {
    private Handler processor;

    public Handler(Handler processor) {
        this.processor = processor;
    }

    public boolean process(Integer request) {
        System.out.println("DEBUG: Обработчик " + this.getClass().getSimpleName() + 
                         " получил запрос " + request);
        
        if (processor != null) {
            System.out.println("DEBUG: Передача запроса следующему обработчику: " + 
                             processor.getClass().getSimpleName());
            return processor.process(request);
        }
        
        System.out.println("DEBUG: Нет следующего обработчика, возвращаем проигрыш");
        return false;
    }
} 