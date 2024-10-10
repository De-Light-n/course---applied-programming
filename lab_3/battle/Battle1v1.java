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
        droid1.setColor(1);
        droid2.setColor(2);
        logger.writeLog("Починається битва мiж " + droid1.getColoredName() + " та " + droid2.getColoredName() + "!");
        try {
            Thread.sleep(500);

            while (droid1.isAlive() && droid2.isAlive()) {
                // Перший дроїд атакує
                Thread.sleep(500);
                droid2.takeDamage(droid1.attack());
                if (!droid2.isAlive()) {
                    logger.writeLog(droid2.getColoredName() + " був знищений!");
                    Thread.sleep(250);
                    logger.writeLog(droid1.getColoredName() + " перемiг у битвi!");
                    logger.close();
                    break;
                }

                // Другий дроїд атакує, якщо вiн ще живий
                Thread.sleep(500);
                droid1.takeDamage(droid2.attack());
                if (!droid1.isAlive()) {
                    logger.writeLog(droid1.getColoredName() + " був знищений!");
                    Thread.sleep(250);
                    logger.writeLog(droid2.getColoredName() + " перемiг у битвi!");
                    logger.close();
                    break;
                }
            }
        } catch (InterruptedException e) {
            System.err.println("Потiк був перерваний.");
        }
        droid1.setColor(0);
        droid2.setColor(0);
        droid1.revive();
        droid2.revive();
    }
}
