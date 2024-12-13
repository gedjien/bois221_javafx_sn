package com.schdulealert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.charset.Charset;

public class ScheduleReader {
    public static List<Schedule> readSchedule(String filePath) throws IOException {
        List<Schedule> scheduleList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), Charset.forName("windows-1251")))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim().replace("\uFEFF", "");
                String[] fields = line.split(";");
                if (fields.length == 3) {
                    scheduleList.add(new Schedule(fields[0], fields[1], fields[2]));
                } else if (fields.length == 4) {
                    scheduleList.add(new Schedule(fields[0], fields[1], fields[2], fields[3]));
                }
                // Выводим считанную строку в консоль для проверки
                System.out.println("Считанная строка: " + line);
            }
        }
        return scheduleList;
    }
}
