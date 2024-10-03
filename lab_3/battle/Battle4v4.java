package lab_3.battle;

import java.util.Random;
import java.util.ArrayList;

import lab_3.Logger;
import lab_3.Droids.Droid;

public class Battle4v4 {
    private Logger logger;
    private Random random;

    public Battle4v4() {
        this.random = new Random();
        this.logger = Logger.getInstance("lab_3/last_battle.txt");
    }

    public void start(ArrayList<Droid> team1, ArrayList<Droid> team2) {
        logger.clearLog();

        logger.writeLog("Починається командна битва 4 на 4!");
        try {
            Thread.sleep(500);
            logger.writeLog("\nВ правому кутку арени:");
            Thread.sleep(500);
            for (Droid droid : team1) {
                logger.writeLog(droid.getName());
                Thread.sleep(250);
            }
            logger.writeLog("\nВ лiвому кутку арени:");
            Thread.sleep(500);
            for (Droid droid : team2) {
                logger.writeLog(droid.getName());
                Thread.sleep(250);
            }
            logger.writeLog(" ");
            int team1Index = 0; // iндекс дроїда команди 1
            int team2Index = 0; // iндекс дроїда команди 2

            while (teamIsAlive(team1) && teamIsAlive(team2)) {
                // Дроїд з команди 1 атакує
                Droid attacker1 = team1.get(team1Index);
                if (attacker1.isAlive()) {
                    Droid target2 = getRandomAliveDroid(team2, random);
                    if (target2 != null) {
                        Thread.sleep(500);
                        attacker1.makeMove(target2);
                        if (!target2.isAlive()) {
                            logger.writeLog(attacker1.getName() + " добив " + target2.getName());
                        }
                    }
                }

                // Перевiрка, чи вся команда 2 жива
                if (!teamIsAlive(team2)) {
                    logger.writeLog("Команда 1 перемогла!");
                    logger.close();
                    break;
                }

                // Дроїд з команди 2 атакує
                Droid attacker2 = team2.get(team2Index);
                if (attacker2.isAlive()) {
                    Droid target1 = getRandomAliveDroid(team1, random);
                    if (target1 != null) {
                        Thread.sleep(500);
                        attacker2.makeMove(target1);
                        if (!target1.isAlive()) {
                            logger.writeLog(attacker2.getName() + " добив " + target1.getName());
                        }
                    }
                }

                // Перевiрка, чи вся команда 1 жива
                if (!teamIsAlive(team1)) {
                    logger.writeLog("Команда 2 перемогла!");
                    logger.close();
                    break;
                }

                // Чергування iндексiв дроїдiв
                team1Index = (team1Index + 1) % team1.size();
                team2Index = (team2Index + 1) % team2.size();
            }
        }

        catch (InterruptedException e) {
            System.err.println("Потiк був перерваний.");
        }
        this.reviveTems(team1, team2);
    }

    // Метод для перевiрки, чи є живi дроїди в командi
    private boolean teamIsAlive(ArrayList<Droid> team) {
        for (Droid droid : team) {
            if (droid.isAlive()) {
                return true;
            }
        }
        return false;
    }

    // Метод для отримання випадкового живого дроїда з команди
    private Droid getRandomAliveDroid(ArrayList<Droid> team, Random random) {
        Droid target = null;
        while (target == null) {
            int index = random.nextInt(team.size());
            if (team.get(index).isAlive()) {
                target = team.get(index);
            }
        }
        return target;
    }

    private void reviveTems(ArrayList<Droid> team1, ArrayList<Droid> team2) {
        for (Droid droid : team1) {
            droid.revive();
        }
        for (Droid droid : team2) {
            droid.revive();
        }
    }
}