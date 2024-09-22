package lab_3;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

import lab_3.Droids.*;
import lab_3.battle.*;

public class Menu {

    private ArrayList<Droid> droidList;  // Список створених дроїдiв
    private Logger logger;               // Екземпляр класу logger
    private Scanner scanner;             // Сканер для введення даних

    public Menu() {
        this.droidList = new ArrayList<>();  // iнiцiалiзацiя списку дроїдiв
        this.logger = Logger.getInstance("lab_3/last_battle.txt");  // Отримуємо logger
        this.scanner = new Scanner(System.in);  // iнiцiалiзацiя Scanner

        // Базовi дроїди
        droidList.add(new TankDroid("Гора", 3, 0, 2));
        droidList.add(new WarriorDroid("Берсерк", 4, 0, 1));
        droidList.add(new AssasinDroid("Блеск", 0, 5, 0));
        droidList.add(new TankDroid("Амбал", 1, 0, 4));
        droidList.add(new WarriorDroid("Ракутан", 1, 1, 3));
        droidList.add(new AssasinDroid("UX-95", 0, 3, 3));
        droidList.add(new TankDroid("камаз", 0, 5, 0));
        droidList.add(new WarriorDroid("Майк", 2, 2, 1));
        droidList.add(new AssasinDroid("Мухамед", 2, 2, 1));
    }

    // Головне меню
    public void displayMainMenu() {
        while (true) {
            System.out.println("\n=== Меню ===");
            System.out.println("1. Створити дроїда");
            System.out.println("2. Показати список дроїдiв");
            System.out.println("3. Бiй 1 на 1");
            System.out.println("4. Командний бiй");
            System.out.println("5. Вiдтворити бiй з файлу");
            System.out.println("6. Вийти");

            int choice = getValidIntInput("Виберiть опцiю: ");

            switch (choice) {
                case 1:
                    createDroid();
                    break;
                case 2:
                    showDroids();
                    break;
                case 3:
                    startBattle1v1();
                    break;
                case 4:
                    startTeamBattle();
                    break;
                case 5:
                    loadBattleLog();
                    break;
                case 6:
                    System.out.println("Вихiд...");
                    return;
                default:
                    System.out.println("Неправильний вибiр. Спробуйте ще раз.");
            }
        }
    }

    // Метод для створення дроїда
    private void createDroid() {
        System.out.println("Виберiть тип дроїда:");
        System.out.println("1. Танк Дроїд");
        System.out.println("2. Воїн Дроїд");
        System.out.println("3. Ассасiн Дроїд");
        int type = scanner.nextInt();

        System.out.print("Введiть iм'я дроїда: ");
        String name = scanner.next();

        int total_points = 0;
        int healthPoints = 0;
        int damagePoints = 0;
        int accuracyPoints = 0;
        System.out.println("Розпердiлiть 5 очок:");
        
        while(total_points!=5){
            total_points = 0;
            System.out.print("Введiть кiлькiсть очок в здоров'я: ");
            healthPoints = scanner.nextInt();

            System.out.print("Введiть кiлькiсть очок в атаку: ");
            damagePoints = scanner.nextInt();

            System.out.print("Введiть кiлькiсть очок в точнiсть: ");
            accuracyPoints = scanner.nextInt();

            total_points = healthPoints + damagePoints + accuracyPoints;
            if (total_points!=5){
                System.out.println("Неправильне розподiлення очок");
            }
        }

        Droid newDroid = null;
        switch (type) {
            case 1:
                newDroid = new TankDroid(name, healthPoints, damagePoints, accuracyPoints);
                break;
            case 2:
                newDroid = new WarriorDroid(name, healthPoints, damagePoints, accuracyPoints);
                break;
            case 3:
                newDroid = new AssasinDroid(name, healthPoints, damagePoints, accuracyPoints);
                break;    
            default:
                System.out.println("Неправильний вибiр.");
                return;
        }

        droidList.add(newDroid);
        System.out.println("Дроїд " + newDroid.getName() + " успiшно створений!");
    }

    // Метод для показу списку дроїдiв
    private void showDroids() {
        if (droidList.isEmpty()) {
            System.out.println("Немає створених дроїдiв.");
        } else {
             // Заголовок таблицi
            System.out.printf("%-5s | %-15s | %-10s | %-10s | %-10s | %-10s | %-10s%n", "Номер", "iм'я", 
                                    "HP", "Damage", "Accuracy", "Dodge", "Type");

            // Роздiльник
            System.out.println("----------------------------------------------------------------------------------------------------");
            int i = 1;
            // Виведення дроїдiв
            for (Droid droid : droidList) {
                System.out.printf("%-5d | %-15s | %-10d | %-10d | %-10d | %-10d | %-10s%n",
                        i++,
                        droid.getName(),
                        droid.getHealth(),
                        droid.getDamage(),
                        droid.getAccuracy(),
                        droid.getDodgeChance(),
                        droid.getClass().getSimpleName()); // Тип дроїда
                System.out.println("----------------------------------------------------------------------------------------------------");
            }
        }
    }

    // Метод для запуску бою 1 на 1
    private void startBattle1v1() {
        if (droidList.size() < 2) {
            System.out.println("Недостатньо дроїдiв для бою.");
            return;
        }

        System.out.println("Виберiть двох дроїдiв для бою:");
        showDroids();
        int firstIndex = getValidDroidIndex("Введiть номер першого дроїда: ", droidList.size()) - 1;
        int secondIndex;

        do {
            secondIndex = getValidDroidIndex("Введiть номер другого дроїда: ", droidList.size()) - 1;
            if (firstIndex == secondIndex) {
                System.out.println("Не можна вибрати одного i того ж дроїда.");
            }
        } while (firstIndex == secondIndex);

        Droid droid1 = droidList.get(firstIndex);
        Droid droid2 = droidList.get(secondIndex);
        new Battle1v1(droid1, droid2);
    }

    private void startTeamBattle() {
        if (droidList.size() < 8) {
            System.out.println("Недостатньо дроїдiв для командного бою.");
            return;
        }
        showDroids();
        System.out.println("Виберiть 4 дроїдiв для першої команди:");
        ArrayList<Droid> team1 = new ArrayList<>();
        ArrayList<Droid> team2 = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            int index;

            while (true) {
                index = getValidDroidIndex("Введiть номер дроїда для команди 1: ", droidList.size()) - 1;
                if (team1.contains(droidList.get(index)) || team2.contains(droidList.get(index))){
                    System.out.println("Дроїд вже знаходиться в однiй з команд");
                } else {
                    break;
                }
            }

            team1.add(droidList.get(index));
        }

        System.out.println("Виберiть 4 дроїдiв для другої команди:");
        for (int i = 0; i < 4; i++) {
            int index;
            while (true) {
                index = getValidDroidIndex("Введiть номер дроїда для команди 2: ", droidList.size()) - 1;
                if (team1.contains(droidList.get(index)) || team2.contains(droidList.get(index))){
                    System.out.println("Дроїд вже знаходиться в однiй з команд");
                } else {
                    break;
                }
            }

            team2.add(droidList.get(index));
        }

        new Battle4v4(team1, team2);
    }

    // Метод для вiдтворення бою з файлу
    private void loadBattleLog() {
        logger.readLog();
    }

    private int getValidIntInput(String prompt) {
        int value;
        while (true) {
            try {
                System.out.print(prompt);
                value = scanner.nextInt();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Неправильний ввiд! Введiть цiле число.");
                scanner.next();  // Очистити введений неправильний ввiд
            }
        }
    }

    private int getValidDroidIndex(String prompt, int size) {
        int index;
        while (true) {
            index = getValidIntInput(prompt);
            if (index > 0 && index <= size) {
                return index;
            } else {
                System.out.println("Невiрний номер дроїда. Спробуйте ще раз.");
            }
        }
    }
}

