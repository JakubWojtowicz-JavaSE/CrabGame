package Main;

import Entity.BallEnemy;
import Entity.Potion;
import Entity.Money;
import Entity.Type;
import Entity.Entity;
import Utilz.Constants;

import java.awt.*;
import java.util.ArrayList;

public class EntitySpawner {

    private Game game;
    public ArrayList<Entity> enemies;
    public ArrayList<Entity> objects;

    // gen sett
    private int genCounter, genSpace, enemySpeed, times, maxTimes;
    private int oGenCounter, oGenSpace;
    private int defYPos;

    public EntitySpawner(Game game) {
        this.game = game;

        enemies = new ArrayList<>();
        objects = new ArrayList<>();
        setDefVar();
    }

    public void setDefVar() {
        genCounter = 0;
        genSpace = 60;
        enemySpeed = 2;
        times = 0;
        maxTimes = 50;

        oGenCounter = 0;
        oGenSpace = 120;

        defYPos = -Game.TILE_SIZE;

        enemies.clear();
        objects.clear();
    }

    private void genEnemies() {
        genCounter++;
        if (genCounter >= genSpace) {
            enemies.add(new BallEnemy(game, game.random.nextInt(Game.WINDOW_WIDTH- Constants.BallDetails.BALL_WIDTH), defYPos, enemySpeed));
            times++;
            genCounter = 0;

            if (times >= maxTimes) {
                System.out.println("++++++");
                game.player.score++;
                game.player.heal(2);
                maxTimes--;
                genSpace -= 1.5f;
                enemySpeed++;
                times = 0;
            }
        }
    }

    private void genObjects() {
        oGenCounter++;
        if (oGenCounter >= oGenSpace) {
            int i = game.random.nextInt(100);
            if (i <= 25)
                objects.add(new Potion(game, Type.potion_g, game.random.nextInt(Game.WINDOW_WIDTH-Constants.PotionDetails.POTION_WIDTH), defYPos, 2.6f));
            else if (i > 25 && i <= 65)
                objects.add(new Money(game, game.random.nextInt(Game.WINDOW_WIDTH-Constants.PotionDetails.POTION_WIDTH), defYPos, 2.5f));
            else
                objects.add(new Potion(game, Type.potion_b, game.random.nextInt(Game.WINDOW_WIDTH-Constants.PotionDetails.POTION_WIDTH), defYPos, 2.5f));
            oGenCounter = 0;
        }
    }

    public void update() {
        genEnemies();
        genObjects();

        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i) != null) {
                enemies.get(i).update();
            }
        }
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i) != null) {
                objects.get(i).update();
            }
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i) != null) {
                enemies.get(i).draw(g);
            }
        }
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i) != null) {
                objects.get(i).draw(g);
            }
        }
    }
}
