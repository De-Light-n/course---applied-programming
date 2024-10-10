package lab_3.effects;

public class Effect {
    protected int duration; // тривалість
    protected int damage;   // урон

    // Конструктор абстрактного класу
    public Effect(int duration, int damage) {
        this.duration = duration;
        this.damage = damage;
    }

    public void reduceDuration(int duration){
        this.duration -= duration;
    }

    public int getDuration(){
        return this.duration;
    }
    public int getDamage(){
        return this.damage;
    }
}
