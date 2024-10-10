package lab_3.Droids;

import java.util.ArrayList;

import lab_3.effects.Damage;
import lab_3.effects.Effect;

public class AssassinDroid extends Droid {
    public AssassinDroid(String name, int points_in_health, int points_in_damage, int points_in_accuracy) {
        super(name, 1800, 300,
                80, 25,
                20,
                points_in_health, points_in_damage, points_in_accuracy);
    }

    public ArrayList<Effect> powerUp() {
        ArrayList<Effect> return_effects = new ArrayList<>();
        logger.writeLog(this.getColoredName() + " застосовує удари без черги");
        // target.takeDamage(this.damage);
        // target.takeDamage(this.damage);
        return_effects.add(new Damage(1, this.damage));
        return_effects.add(new Damage(1, this.damage));
        return return_effects;

    }
}
