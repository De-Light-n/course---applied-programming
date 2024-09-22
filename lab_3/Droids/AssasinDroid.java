package lab_3.Droids;

import java.util.Random;

public class AssasinDroid extends Droid{
    public AssasinDroid(String name, int points_in_health, int points_in_damage, int points_in_accuracy) {
        super(name, 1800, 300, 
                80, 25, 
                20,
                points_in_health, points_in_damage, points_in_accuracy);
    }

    public boolean powerUp(Droid target){
                Random random = new Random();
        int chance = random.nextInt(100);
        if (chance <= this.power_chance) {
            logger.writeLog(this.getName() + " застосовує удари без черги");
            target.takeDamage(this.damage);
            target.takeDamage(this.damage);
            return true;
        } else {
            return false;
        }
    }
}
