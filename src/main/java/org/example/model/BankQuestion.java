package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BankQuestion {
    private final List<Question> questions;
    private final Random random;
    private int currentIndex;

    public BankQuestion() {
        this.questions = new ArrayList<>();
        this.random = new Random();
        this.currentIndex = 0;
        initializeQuestions();
    }

    private void initializeQuestions() {
        // Java Core
        Question q1 = new Question("Что такое Java?", "Объектно-ориентированный язык программирования");
        q1.addWrongAnswers("Скриптовый язык", "База данных", "Операционная система", "Веб-сервер");

        Question q2 = new Question("Что такое JVM?", "Java Virtual Machine");
        q2.addWrongAnswers("Java Visual Manager", "Java Version Manager", "Java Vector Machine", "Just Virtual Memory");

        Question q3 = new Question("Какой модификатор доступа самый строгий?", "private");
        q3.addWrongAnswers("public", "protected", "default", "static");

        // ООП
        Question q4 = new Question("Что такое инкапсуляция?", "Сокрытие реализации и данных");
        q4.addWrongAnswers("Наследование классов", "Создание объектов", "Перегрузка методов", "Полиморфизм");

        Question q5 = new Question("Что такое полиморфизм?", "Возможность объекта принимать разные формы");
        q5.addWrongAnswers("Множественное наследование", "Перегрузка операторов", "Сокрытие данных", "Абстракция");

        // Коллекции
        Question q6 = new Question("Какая коллекция использует хеш-таблицу?", "HashMap");
        q6.addWrongAnswers("ArrayList", "LinkedList", "TreeSet", "Vector");

        Question q7 = new Question("Какая коллекция гарантирует порядок элементов?", "LinkedList");
        q7.addWrongAnswers("HashSet", "HashMap", "TreeMap", "WeakHashMap");

        // Многопоточность
        Question q8 = new Question("Как создать поток в Java?", "Наследование от Thread или реализация Runnable");
        q8.addWrongAnswers("Использовать оператор new", "Вызвать метод start()", "Создать объект Process", "Использовать fork()");

        Question q9 = new Question("Что такое синхронизация в Java?", "Механизм контроля доступа к общим ресурсам");
        q9.addWrongAnswers("Копирование данных", "Сериализация объектов", "Параллельное выполнение", "Асинхронные вызовы");

        // Исключения
        Question q10 = new Question("Какое исключение нельзя обработать?", "Error");
        q10.addWrongAnswers("Exception", "RuntimeException", "IOException", "SQLException");

        questions.add(q1);
        questions.add(q2);
        questions.add(q3);
        questions.add(q4);
        questions.add(q5);
        questions.add(q6);
        questions.add(q7);
        questions.add(q8);
        questions.add(q9);
        questions.add(q10);
    }

    public Question getNextQuestion() {
        if (currentIndex >= questions.size()) {
            return null;
        }
        return questions.get(currentIndex++);
    }

    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            return null;
        }
        return questions.get(random.nextInt(questions.size()));
    }

    public List<Question> getQuestions(int count) {
        if (count > questions.size()) {
            count = questions.size();
        }
        List<Question> result = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {
            indices.add(i);
        }
        for (int i = 0; i < count; i++) {
            int index = random.nextInt(indices.size());
            result.add(questions.get(indices.get(index)));
            indices.remove(index);
        }
        return result;
    }
}
