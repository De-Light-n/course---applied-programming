package lab_3.Droids;

import java.util.ArrayList;
import java.util.Random;
import lab_3.Logger;
import lab_3.effects.*;

public abstract class Droid {
    private String name;
    protected int health;
    protected int damage;
    protected int accuracy;

    private int start_health;

    protected int standard_health;
    protected int standard_damage;
    protected int standard_accuracy;
    protected int standard_dodge_chance;

    protected int points_in_health;
    protected int points_in_damage;
    protected int points_in_accuracy;

    protected int health_per_point = 200;
    protected int damage_per_point = 50;
    protected int accuracy_per_point = 7;

    protected ArrayList<Effect> effects = new ArrayList<Effect>();
    protected int power_chance = 10;
    protected int power_recharge;
    protected int stunned = 0;

    protected Logger logger = null;
    private Random random;

    public Droid(String name, int standard_health, int standard_damage, int standard_accuracy,
            int standard_power_recharge, int standard_dodge_chance,
            int points_in_health, int points_in_damage, int points_in_accuracy) {
        this.name = name;
        this.standard_health = standard_health;
        this.standard_damage = standard_damage;
        this.standard_accuracy = standard_accuracy;
        this.power_recharge = standard_power_recharge;
        this.standard_dodge_chance = standard_dodge_chance;

        this.health = this.standard_health + this.health_per_point * points_in_health;
        this.damage = this.standard_damage + this.damage_per_point * points_in_damage;
        this.accuracy = this.standard_accuracy + this.accuracy_per_point * points_in_accuracy;

        this.start_health = this.health;

        this.logger = Logger.getInstance("lab_3/last_battle.txt");
        this.random = new Random();
        this.color = "\u001B[0m";//стандарт
    }

    public ArrayList<Effect> attack() {
        ArrayList<Effect> return_effects = new ArrayList<>();
        boolean isStunned = false;
        ArrayList<Integer> indicesToRemove = new ArrayList<>();
        logger.writeLog("------------------------------");

        if (this.effects != null) {

            for (int i = 0; i < this.effects.size(); i++) {
                Effect effect = this.effects.get(i);

                if (effect instanceof Stunn) {
                    isStunned = true;
                    logger.writeLog(this.getColoredName() + " оглушений");
                } 
                else if (effect instanceof Poison) {
                    this.health -= effect.getDamage();
                    logger.writeLog(this.getColoredName() + " отруєний -" + effect.getDamage());
                }

                effect.reduceDuration(1);

                if (effect.getDuration() <= 0) {
                    indicesToRemove.add(i);
                }
            }

            for (int i = indicesToRemove.size() - 1; i >= 0; i--) {
                int index = indicesToRemove.get(i);
                this.effects.remove(index);
            }
        }

        if (isStunned) {
            logger.writeLog(this.getColoredName() + " пiд шоком i пропускає хiд.");
            return_effects.add(new Damage(1, 0));
            return return_effects;
        }

        int chance = this.random.nextInt(100);
        if (chance <= this.accuracy) {
            return_effects.add(new Damage(1, this.damage));
            logger.writeLog(this.getColoredName() + " наносить удар");
            chance = this.random.nextInt(100);
            if (chance <= this.power_chance){
                return_effects.addAll(this.powerUp());
                this.power_chance = 5;
            }else{
                this.power_chance += this.power_recharge;
            }
        }else{
            logger.writeLog(this.getColoredName() + " промахнувся");
        }
        return return_effects;
    }

    public abstract ArrayList<Effect> powerUp();

    public boolean takeDamage(ArrayList<Effect> incomingEffects) {
        int chance = this.random.nextInt(100);

        if (chance <= this.standard_dodge_chance) {
            logger.writeLog(this.getColoredName() + " ухилився");
            return false;
        }

        for (Effect effect : incomingEffects) {
            if (effect instanceof Damage) {
                int incoming_damage = effect.getDamage();
                this.health -= incoming_damage;
                if(incoming_damage > 0){
                    logger.writeLog(this.getColoredName() + " -" + incoming_damage + " health");
                }
            } else {
                this.effects.add(effect);
            }
        }
        return true;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public String getName() {
        return this.name;
    }

    public Object getDamage() {
        return this.damage;
    }

    public Object getHealth() {
        return this.health;
    }

    public Object getAccuracy() {
        return this.accuracy;
    }

    public Object getDodgeChance() {
        return this.standard_dodge_chance;
    }

    public Object getStunned() {
        return this.stunned;
    }

    public void revive() {
        this.health = this.start_health;
        this.effects.clear();
    }


    private String color; 

    public void setColor(int team) {
        if (team == 1) {
            this.color = "\u001B[32m";
        } else if (team == 2) {
            this.color = "\u001B[31m";
        } else {
            this.color = "\u001B[0m"; // Без кольору, якщо команда не визначена
        }
    }

    // Метод для отримання iменi з кольором
    public String getColoredName() {
        return this.color + this.name + "\u001B[0m"; // Додаємо скидання кольору пiсля iменi
    }
}
