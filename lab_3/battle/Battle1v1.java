package lab_3.battle;

import lab_3.Logger;
import lab_3.Droids.Droid;

public class Battle1v1 {
    public Battle1v1(Droid droid1, Droid droid2){
        Logger logger = Logger.getInstance("lab_3/last_battle.txt");
        logger.clearLog();
        logger.writeLog("Починається битва мiж " + droid1.getName() + " та " + droid2.getName() + "!");
        try{
            Thread.sleep(500);
        
            while (droid1.isAlive() && droid2.isAlive()) {
                // Перший дроїд атакує
                Thread.sleep(500);
                droid1.attack(droid2);
                if (!droid2.isAlive()) {
                    logger.writeLog(droid2.getName() + " був знищений!");
                    Thread.sleep(250);
                    logger.writeLog(droid1.getName() + " перемiг у битвi!");
                    logger.close();
                    break;
                }
                
                // Другий дроїд атакує, якщо вiн ще живий
                Thread.sleep(500);
                droid2.attack(droid1);
                if (!droid1.isAlive()) {
                    logger.writeLog(droid1.getName() + " був знищений!");
                    Thread.sleep(250);
                    logger.writeLog(droid2.getName() + " перемiг у битвi!");
                    logger.close();
                    break;
                }
            } 
        }
        catch (InterruptedException e) {
            System.err.println("Потiк був перерваний.");
        }
    }
}
