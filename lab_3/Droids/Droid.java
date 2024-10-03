package lab_3.Droids;

import java.util.Random;
import lab_3.Logger;

public abstract class Droid {
    private String name;
    protected int health;
    protected int damage;
    protected int accuracy;

    private int end_health;

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

    protected int power_chance = 10;
    protected int power_recharge;
    protected int stunned = 0;

    protected Logger logger = null;

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

        this.end_health = this.health;

        this.logger = Logger.getInstance("lab_3/last_battle.txt");
    }

    public boolean makeMove(Droid target) {
        Random random = new Random();
        int chance = random.nextInt(100);
        logger.writeLog("--------------------------------------");
        if (this.stunned > 0) {
            logger.writeLog(this.name + " оглушений");
            this.stunned -= 1;
            return false;
        } else if (chance <= this.accuracy) {
            logger.writeLog(this.name + " успiшно атакував " + target.name);
            this.attack(target);
            if (this.powerUp(target)) {
                this.power_chance = 5;
            } else {
                this.power_chance += this.power_recharge;
            }
            return true;
        } else {
            logger.writeLog(this.name + " промахнувся по " + target.name);
            return false;
        }
    }

    public void attack(Droid target) {
        target.takeDamage(this.damage);
    }

    public abstract boolean powerUp(Droid target);

    public boolean takeDamage(int damage_in) {
        Random random = new Random();
        int chance = random.nextInt(100);
        if (chance >= this.standard_dodge_chance) {
            this.health -= damage_in;
            logger.writeLog(this.name + " -" + damage_in + " health");
            return true;
        } else {
            logger.writeLog(this.name + " ухилився");
            return false;
        }
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
        this.health = this.end_health;
    }
}
