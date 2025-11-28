package com.example.adventuregame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import static java.rmi.server.LogStream.log;

public class HelloController {
    @FXML private Label hpLabel, goldLabel;
    @FXML private TextArea gameLog;
    @FXML private Button upBtn, downBtn, leftBtn, rightBtn, fightBtn, runBtn, searchBtn, sleepBtn;

    private Game game;

    //starts game, logs stats
    @FXML
    public void initialize() {
        game = new Game();
        log("Welcome to the Adventure Game!");
        updateStats();

    }
    //character info display
    private void updateStats() {
        hpLabel.setText("HP: " + game.getPlayer().getHp());
        goldLabel.setText("Gold: " + game.getPlayer().getGold());
    }
    //had the idea for multiple 'if/elif' statements but was too lazy to compile them so I asked
    //AI to do it using the idea I had in mind
    private void move(int dx, int dy) {
        if (game.getCurrentMonster() != null) {
            log("You are currently in combat and can't move. Would you like to fight or run?");
            return;
        }

        int targetX = game.getPlayerX() + dx;
        int targetY = game.getPlayerY() + dy;

        if (targetX < 0 || targetY < 0 || targetX >= 10 || targetY >= 10) {
            log("You can't go that way! It's the edge of the map.");
            return;
        }

        if (game.isBlocked(targetX, targetY)) {
            log("That room is blocked. You can't go there.");
            return;
        }

        boolean success = game.movePlayer(dx, dy);

        if (!success && game.getCurrentMonster() != null) {
            log("A monster appears! You must fight or run.");
            fightBtn.setDisable(false);
            runBtn.setDisable(false);
        } else if (success) {
            log("You moved to a new room at (" + game.getPlayerX() + ", " + game.getPlayerY() + ").");
            fightBtn.setDisable(true);
            runBtn.setDisable(true);
        } else {
            log("You can't move there.");
        }

        updateStats();
    }
    @FXML private void moveUp() { move(0, -1); }
    @FXML private void moveDown() { move(0, 1); }
    @FXML private void moveLeft() { move(-1, 0); }
    @FXML private void moveRight() { move(1, 0); }

    //character fight
    @FXML
    private void fight() {
        Monster m = game.getCurrentMonster();
        if (m == null) {
            log("No monster to fight.");
            fightBtn.setDisable(true);
            runBtn.setDisable(true);
            return;
        }

        int playerAttackRoll = (int)(Math.random() * 20) + 1;
        if (playerAttackRoll >= m.getDexterity()) {
            int damage = Math.max(1, game.getPlayer().getStrength() / 3);
            m.takeDamage(damage);
            log("You hit the monster for " + damage + " damage!");
        } else {
            log("You missed!");
        }

        if (m.getHp() <= 0) {
            log("You defeated the monster!");
            game.clearMonster();
            fightBtn.setDisable(true);
            runBtn.setDisable(true);
            updateStats();
            return;
        }

        int monsterAttackRoll = (int)(Math.random() * 20) + 1;
        if (monsterAttackRoll >= game.getPlayer().getDexterity()) {
            int damage = Math.max(1, m.getStrength() / 3);
            game.getPlayer().takeDamage(damage);
            log("The monster hits you for " + damage + " damage!");
        } else {
            log("The monster missed!");
        }

        updateStats();

        if (game.getPlayer().getHp() <= 0) {
            log("You died! Game Over.");
            fightBtn.setDisable(true);
            runBtn.setDisable(true);
            setAllButtonsDisabled(true);
        }
    }

    //character run
    @FXML
    private void run() {
        Monster m = game.getCurrentMonster();
        if (m == null) {
            log("No monster to run from.");
            fightBtn.setDisable(true);
            runBtn.setDisable(true);
            return;
        }

        int monsterSpotRoll = (int)(Math.random() * 20) + 1;
        if (monsterSpotRoll < m.getIntelligence()) {
            int damage = Math.max(1, m.getStrength() / 3);
            game.getPlayer().takeDamage(damage);
            log("The monster saw you and hits you for " + damage + " damage as you run!");
            updateStats();
            if (game.getPlayer().getHp() <= 0) {
                log("You died while running away! Game Over.");
                fightBtn.setDisable(true);
                runBtn.setDisable(true);
                setAllButtonsDisabled(true);
                return;
            }
        } else {
            log("You successfully escaped without getting hit.");
        }

        game.clearMonster();
        fightBtn.setDisable(true);
        runBtn.setDisable(true);
        updateStats();
    }

    //character search for gold
    @FXML
    private void searchRoom() {
        if (game.getCurrentMonster() != null) {
            log("You can't search while fighting a monster!");
            return;
        }

        String result = game.searchRoom();
        log(result);
        updateStats();
    }

    //character sleep
    @FXML
    private void sleep() {
        if (game.getCurrentMonster() != null) {
            log("You can't sleep while a monster is here!");
            return;
        }

        game.getPlayer().heal();
        log("You slept and restored your hit points.");

        //
        if ((int)(Math.random() * 6) == 0) {
            game.clearMonster();
            game.setCurrentMonster(new Monster());
            log("A monster has appeared while you were sleeping!");
            fightBtn.setDisable(false);
            runBtn.setDisable(false);
        }

        updateStats();
    }


    //better method of disable
    private void setAllButtonsDisabled(boolean disabled) {
        upBtn.setDisable(disabled);
        downBtn.setDisable(disabled);
        leftBtn.setDisable(disabled);
        rightBtn.setDisable(disabled);
        fightBtn.setDisable(disabled);
        runBtn.setDisable(disabled);
        searchBtn.setDisable(disabled);
        sleepBtn.setDisable(disabled);
    }

    //displays text related to called action
    private void log(String message) {
        gameLog.appendText(message + "\n");
    }

}

