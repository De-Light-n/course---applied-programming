package lab_3.Droids;

import java.util.ArrayList;

import lab_3.effects.Damage;
import lab_3.effects.Effect;

public class TankDroid extends Droid {

    public TankDroid(String name, int points_in_health, int points_in_damage, int points_in_accuracy) {
        super(name,
                3700, 300,
                65, 30,
                5,
                points_in_health, points_in_damage, points_in_accuracy);
    }

    public ArrayList<Effect> powerUp() {
        ArrayList<Effect> return_effects = new ArrayList<>();
        this.health += 700;
        this.damage += 50;
        logger.writeLog(this.getColoredName() + " усиляється");
        return_effects.add(new Damage(1, 0));
        return return_effects;
    }
}
