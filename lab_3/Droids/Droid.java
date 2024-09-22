package lab_3.Droids;

import java.util.Random;

import lab_3.Logger;

public abstract class Droid {
    private String name;
    protected int health;
    protected int damage;
    protected int accuracy;

    protected int standart_health;
    protected int standart_damage;
    protected int standart_accuracy;
    protected int standart_dodge_chance;

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

    public Droid(String name, int standart_health, int standart_damage, int standart_accuracy,
                 int standart_power_recharge, int standart_dodge_chance,
                 int points_in_health, int points_in_damage, int points_in_accuracy) {
        this.name = name;
        this.standart_health = standart_health;
        this.standart_damage = standart_damage;
        this.standart_accuracy = standart_accuracy;
        this.power_recharge = standart_power_recharge;
        this.standart_dodge_chance = standart_dodge_chance;

        this.health = this.standart_health + this.health_per_point * points_in_health;
        this.damage = this.standart_damage + this.damage_per_point * points_in_damage;
        this.accuracy = this.standart_accuracy + this.accuracy_per_point * points_in_accuracy;

        this.logger = Logger.getInstance("lab_3/last_battle.txt");
    }

    public boolean attack(Droid target) {
        Random random = new Random();
        int chance = random.nextInt(100);
        logger.writeLog("--------------------------------------");
        if (this.stunned > 0){
            logger.writeLog(this.name + " оглушений");
            this.stunned -= 1;
            return false;
        }else if (chance <= this.accuracy) {
            logger.writeLog(this.name + " успiшно атакував " + target.name);
            target.takeDamage(this.damage);
            if (this.powerUp(target)){
                this.power_chance = 5;
            }else{
                this.power_chance += this.power_recharge;
            }
            return true;
        } else {
            logger.writeLog(this.name + " промахнувся по " + target.name);
            return false;
        }
    }

    public abstract boolean powerUp(Droid target);

    public boolean takeDamage(int damage_in) {
        Random random = new Random();
        int chance = random.nextInt(100);
        if (chance >= this.standart_dodge_chance) {
            this.health -= damage_in;
            logger.writeLog(this.name + " -" + damage_in + " health");
            return true;
        } else {
            logger.writeLog(this.name + " ухилився");
            return false;
        }
    }

    public boolean isAlive(){
        return this.health > 0;
    }

    public String getName(){
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
        return this.standart_dodge_chance;
    }
}
