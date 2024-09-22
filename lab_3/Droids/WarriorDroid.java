package lab_3.Droids;

import java.util.Random;

public class WarriorDroid extends Droid{
    public WarriorDroid(String name, int points_in_health, int points_in_damage, int points_in_accuracy) {
        super(name, 2500, 200, 
                75, 30, 
                10,
                points_in_health, points_in_damage, points_in_accuracy);
    }

    public boolean powerUp(Droid target){
        Random random = new Random();
        int chance = random.nextInt(100);
        if (chance <= this.power_chance) {
            logger.writeLog(this.getName() + " оглушає " + target.getName());
            target.stunned = 3;
            return true;
        } else {
            return false;
        }
    }
}