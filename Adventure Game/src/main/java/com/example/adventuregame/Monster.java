package com.example.adventuregame;

public class Monster {
    private int hp;
    private int strength;
    private int dexterity;
    private int intelligence;


    public Monster() {
        //used AI to double-check that this equation would offer number choices within 1-6
        int base = (int)(Math.random() * 6) + 1;
        this.hp = base;
        this.strength = base * 2;
        this.dexterity = base * 2;
        this.intelligence = base * 2;
    }

    public int getHp() { return hp; }
    public void takeDamage(int dmg) { hp -= dmg; }
    public int getStrength() { return strength; }
    public int getDexterity() { return dexterity; }
    public int getIntelligence() { return intelligence; }

    public boolean isAlive() {
        return hp > 0;
    }
}