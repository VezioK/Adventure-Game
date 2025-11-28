package com.example.adventuregame;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Game {
    private static final int MAP_SIZE = 10;
    private boolean[][] blocked;
    private Player player;
    private Monster currentMonster;
    private int playerX, playerY;
    private Random random;
    private Set<String> searchedRooms;

    public Game() {
        random = new Random();
        player = new Player();
        blocked = new boolean[MAP_SIZE][MAP_SIZE];
        searchedRooms = new HashSet<>();

        //used AI to make a formula that gives each room a 10% chance of being blocked off
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                blocked[i][j] = random.nextInt(10) == 0;
            }
        }

        playerX = MAP_SIZE / 2;
        playerY = MAP_SIZE / 2;
    }

    public Player getPlayer() {
        return player;
    }

    public Monster getCurrentMonster() {
        return currentMonster;
    }

    public void setCurrentMonster(Monster monster) {
        this.currentMonster = monster;
    }

    public void clearMonster() {
        currentMonster = null;
    }

    public boolean isBlocked(int x, int y) {
        if (x < 0 || y < 0 || x >= MAP_SIZE || y >= MAP_SIZE) return true;
        return blocked[x][y];
    }


    public boolean movePlayer(int dx, int dy) {
        int nx = playerX + dx;
        int ny = playerY + dy;

        if (nx < 0 || ny < 0 || nx >= MAP_SIZE || ny >= MAP_SIZE) {
            return false;
        }

        if (blocked[nx][ny]) {
            return false;
        }

        playerX = nx;
        playerY = ny;

        if (random.nextBoolean()) {
            currentMonster = new Monster();
            return false;
        }

        return true;
    }

    public boolean hasSearched(int x, int y) {
        return searchedRooms.contains(x + "," + y);
    }

    public void markSearched(int x, int y) {
        searchedRooms.add(x + "," + y);
    }

    public String searchRoom() {
        if (hasSearched(playerX, playerY)) {
            return "You have already searched this room.";
        }

        int roll = random.nextInt(20) + 1;
        int gold = random.nextInt(10) + 1;

        markSearched(playerX, playerY);

        //option to replace:if (roll <= player.getIntelligence() + 5) higher chance of finding gold
        //in rooms
        if (roll < player.getIntelligence()) {
            player.addGold(gold);
            return "You found " + gold + " gold!";
        } else {
            return "You found nothing.";
        }
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }
}