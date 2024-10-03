package lab_3.battle;

import lab_3.Logger;
import lab_3.Droids.Droid;

public class Battle1v1 {
    private Logger logger;

    public Battle1v1() {
        this.logger = Logger.getInstance("lab_3/last_battle.txt");
    }

    public void start(Droid droid1, Droid droid2) {
        logger.clearLog();
        logger.writeLog("Починається битва мiж " + droid1.getName() + " та " + droid2.getName() + "!");
        try {
            Thread.sleep(500);

            while (droid1.isAlive() && droid2.isAlive()) {
                // Перший дроїд атакує
                Thread.sleep(500);
                droid1.makeMove(droid2);
                if (!droid2.isAlive()) {
                    logger.writeLog(droid2.getName() + " був знищений!");
                    Thread.sleep(250);
                    logger.writeLog(droid1.getName() + " перемiг у битвi!");
                    logger.close();
                    break;
                }

                // Другий дроїд атакує, якщо вiн ще живий
                Thread.sleep(500);
                droid2.makeMove(droid1);
                if (!droid1.isAlive()) {
                    logger.writeLog(droid1.getName() + " був знищений!");
                    Thread.sleep(250);
                    logger.writeLog(droid2.getName() + " перемiг у битвi!");
                    logger.close();
                    break;
                }
            }
        } catch (InterruptedException e) {
            System.err.println("Потiк був перерваний.");
        }
        droid1.revive();
        droid2.revive();
    }
}
