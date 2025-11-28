package com.example.adventuregame;

public class Player {
    private int hp;
    private int strength;
    private int dexterity;
    private int intelligence;
    private int gold;

    public Player() {
        this.hp = 20;
        this.strength = rollStat();
        this.dexterity = rollStat();
        this.intelligence = rollStat();
        this.gold = 0;
    }

    //Initially was concerned about generating the same number for all stats, so I made
    //3 separate equations, one for each stat, and when double-checking my work with AI
    //it informed me of this more efficient method of calling it separately for each stat
    //and only having one equation

    private int rollStat() {
        int total = 0;
        for (int i = 0; i < 3; i++) {
            total += (int)(Math.random() * 6) + 1;
        }
        return total;
    }

    public int getHp() { return hp; }
    public void takeDamage(int dmg) { hp -= Math.max(1, dmg); }
    public void heal() { hp = 20; }

    public int getStrength() { return strength; }
    public int getDexterity() { return dexterity; }
    public int getIntelligence() { return intelligence; }
    public int getGold() { return gold; }
    public void addGold(int g) { gold += g; }
}