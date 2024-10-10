package lab_3.Droids;

import java.util.ArrayList;

import lab_3.effects.Effect;
import lab_3.effects.Stunn;

public class WarriorDroid extends Droid {
    public WarriorDroid(String name, int points_in_health, int points_in_damage, int points_in_accuracy) {
        super(name, 2500, 200,
                75, 30,
                10,
                points_in_health, points_in_damage, points_in_accuracy);
    }

    public ArrayList<Effect> powerUp() {
        ArrayList<Effect> return_effects = new ArrayList<>();
        logger.writeLog(this.getColoredName() + " застосовує оглушення");
        return_effects.add(new Stunn(3, 0));
        return return_effects;
    }
}