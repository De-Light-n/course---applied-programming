package lab_3.Droids;

import java.util.Random;

public class TankDroid extends Droid {

    public TankDroid(String name, int points_in_health, int points_in_damage, int points_in_accuracy) {
        super(name,
                3700, 300,
                65, 30,
                5,
                points_in_health, points_in_damage, points_in_accuracy);
    }

    public boolean powerUp(Droid target) {
        Random random = new Random();
        int chance = random.nextInt(100);
        if (chance <= this.power_chance) {
            this.health += 700;
            this.damage += 50;
            logger.writeLog(this.getName() + " усиляється");
            return true;
        } else {
            return false;
        }
    }
}
