package pl.JakubWojtowicz.Main;

import pl.JakubWojtowicz.Entity.BallEnemy;
import pl.JakubWojtowicz.Entity.Entity;
import pl.JakubWojtowicz.Entity.Potion;
import pl.JakubWojtowicz.Entity.Type;
import pl.JakubWojtowicz.Utilz.Constants;

import java.awt.*;
import java.util.ArrayList;

public class EntitySpawner {

    private Game game;
    public ArrayList<Entity> enemies;
    public ArrayList<Potion> potions;

    // gen sett
    private int genCounter, genSpace, enemySpeed, times, maxTimes;
    private int pGenCounter, pGenCounter2, pGenSpace;
    private int defYPos;

    public EntitySpawner(Game game) {
        this.game = game;

        enemies = new ArrayList<>();
        potions = new ArrayList<>();
        setDefVar();
    }

    public void setDefVar() {
        genCounter = 0;
        genSpace = 60;
        enemySpeed = 2;
        times = 0;
        maxTimes = 50;

        pGenCounter = 0;
        pGenSpace = 120;

        defYPos = -Game.TILE_SIZE;

        enemies.clear();
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

    private void genPotions() {
        pGenCounter++;
        if (pGenCounter >= pGenSpace) {
            if (game.random.nextInt(100) > 75)
                potions.add(new Potion(game, Type.potion_g, game.random.nextInt(Game.WINDOW_WIDTH-Constants.PotionDetails.POTION_WIDTH), defYPos, 2.6f));
            else
                potions.add(new Potion(game, Type.potion_b, game.random.nextInt(Game.WINDOW_WIDTH-Constants.PotionDetails.POTION_WIDTH), defYPos, 2.5f));
            pGenCounter = 0;
        }
    }

    public void update() {
        genEnemies();
        genPotions();

        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i) != null) {
                enemies.get(i).update();
            }
        }
        for (int i = 0; i < potions.size(); i++) {
            if (potions.get(i) != null) {
                potions.get(i).update();
            }
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i) != null) {
                enemies.get(i).draw(g);
            }
        }
        for (int i = 0; i < potions.size(); i++) {
            if (potions.get(i) != null) {
                potions.get(i).draw(g);
            }
        }
    }
}
