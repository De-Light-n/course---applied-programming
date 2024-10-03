package lab_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    private static Logger instance = null;
    private String fileName;
    private FileWriter writer;

    private Logger(String fileName) {
        this.fileName = fileName;
        try {
            this.writer = new FileWriter(fileName, true);
        } catch (IOException e) {
            System.err.println("Помилка вiдкриття файлу: " + e.getMessage());
        }
    }

    public static Logger getInstance(String fileName) {
        if (instance == null) {
            instance = new Logger(fileName);
        }
        return instance;
    }

    public void writeLog(String log) {
        try {
            writer.write(log);
            writer.write("\n");
            System.out.println(log);
        } catch (IOException e) {
            System.err.println("Помилка запису у файл: " + e.getMessage());
        }
    }

    public void clearLog() {
        try {
            this.writer = new FileWriter(fileName, false);
        } catch (IOException e) {
            System.err.println("Помилка очищення файлу: " + e.getMessage());
        }
    }

    public void readLog() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("Читання логу бою з файлу:");
            while ((line = reader.readLine()) != null) {
                Thread.sleep(200);
                System.out.println(line);
            }
        } catch (InterruptedException e) {
            System.err.println("Потiк був перерваний.");
        } catch (IOException e) {
            System.err.println("Помилка читання з файлу: " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
