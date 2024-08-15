package Entity;

import Main.Game;
import Main.GameStates;
import Utilz.Data;
import Utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Utilz.Constants.CrabbyDetails.*;

public class Player extends Entity {

    public int score, bestScore, budget;

    public Player(Game game, int xPos) {
        super(game, Type.player,  xPos, 10*Game.TILE_SIZE, CRABBY_WIDTH, CRABBY_HEIGHT, 2.5f, 4, IDLE);
        name = "Crabby";

        score = 0;
        bestScore = game.data.bestScore;
        budget = game.data.budget;
    }

    protected void initCollSpaces() {
        collLeftSpace = (int) (17 * Game.SCALE);
        collRightSpace = (int) (15 * Game.SCALE);
        collTopSpace = (int) (5 * Game.SCALE);
        collBottomSpace = (int) (3 * Game.SCALE);
    }

    protected void loadImgs() {
        imgs = new BufferedImage[SPRITES_IN_ROW][SPRITES_IN_COL];

        for (int i = 0; i < imgs.length; i++) {
            for (int j = 0; j < imgs[0].length; j++) {
                imgs[i][j] = LoadSave.GetSpriteAtlas(LoadSave.CRABBY_SPRITES).getSubimage(j*CRABBY_DEFAULT_WIDTH, i*CRABBY_DEFAULT_HEIGHT, CRABBY_DEFAULT_WIDTH, CRABBY_DEFAULT_HEIGHT);
            }
        }
    }

    public void update() {
        int x = posData.getColX();
        int width = posData.getColWidth();

        if (!game.collisionCh.checkEnemy(this)) {

            if (isAttacking) {
                state = ATTACK;
            } else {
                if (game.listeners.leftPressed) {
                    dir = Direction.left;
                    state = RUNNING;
                } else if (game.listeners.rightPressed) {
                    dir = Direction.right;
                    state = RUNNING;
                } else {
                    dir = Direction.idle;
                    state = IDLE;
                }

                switch (dir) {
                    case left -> {
                        x -= speed;
                        if (x < 0)
                            x = 0;
                    }
                    case right -> {
                        x += speed;
                        if (x > Game.WINDOW_WIDTH - width) {
                            x = Game.WINDOW_WIDTH - width;
                        }
                    }
                }
            }
            recovery();

            if (x != posData.getColX())
                posData.setColX(x);
        }
        updateAnim();
    }
    private int recoveryCounter;

    private void recovery() {
        if (speed < maxSpeed) {
            recoveryCounter++;
            if (recoveryCounter >= 900) {
                speed++;
                if (speed > maxSpeed)
                    speed = maxSpeed;
                recoveryCounter = 0;
            }
        }
    }

    public void giveDamage(int damage) {
        state = HIT;
        health -= damage;
        if (health <= 0) {
            if (chcekChanges())
                LoadSave.SaveData(game.data);
            game.gameState = GameStates.deathScreen;
        }
    }

    public boolean chcekChanges() {
        boolean toSave = false;
        if (score > game.data.bestScore) {
            game.data.bestScore = score;
            toSave = true;
        }
        if (budget > game.data.budget) {
            game.data.budget = budget;
            toSave = true;
        }
        return toSave;
    }

    public void increaseBudget(int i) {
        budget += i;
    }

    public void updateBestScore() {
        bestScore = game.data.bestScore;
    }

    public void draw(Graphics g) {
        int x = posData.getXPos();
        int width = posData.getWidth();
        if (dir == Direction.right) {
            x += width;
            width = -width;
        }
        g.drawImage(imgs[state][animIndex], x, posData.getYPos(), width, posData.getHeight(), null);
        drawColliders(g);
    }

    public void reset() {
        score = 0;
        bestScore = game.data.bestScore;

        dir = Direction.idle;
        state = IDLE;
        health = 4;
        maxHealth = health;
        speed = 2.5f;
        maxSpeed = speed;
        posData.changeData(Game.WINDOW_WIDTH/2-Game.TILE_SIZE/2, 10*Game.TILE_SIZE, CRABBY_WIDTH, CRABBY_HEIGHT, collLeftSpace, collRightSpace, collTopSpace, collBottomSpace);
        animIndex = 0;
        animCounter = 0;
        isAttacking = false;
    }
}
