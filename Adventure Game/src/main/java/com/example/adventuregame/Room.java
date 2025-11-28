package com.example.adventuregame;

public class Room {
    private int x, y;
    private boolean blocked, goldSearched;
    private int goldAmount;
    private Monster monster;

    public Room(int x, int y, boolean blocked, int goldAmount) {
        this.x = x;
        this.y = y;
        this.blocked = blocked;
        this.goldSearched = false;
        this.goldAmount = goldAmount;
        this.monster = null;
    }

    //was struggling to prevent researching of rooms that were already searched for gold
    //so, I used AI to solve that issue
    public boolean isBlocked() { return blocked; }
    public boolean isGoldSearched() { return goldSearched; }
    public void setGoldSearched(boolean searched) { this.goldSearched = searched; }
    public int getGoldAmount() { return goldAmount; }

    public Monster getMonster() { return monster; }
    public void setMonster(Monster monster) { this.monster = monster; }
    public boolean hasMonster() { return monster != null && monster.isAlive(); }}