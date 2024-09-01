package Entity;

import Main.Game;
import Utilz.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    protected Game game;
    protected Direction dir;
    protected String name;
    protected float speed, maxSpeed;
    protected int health, maxHealth;
    protected PositionData posData;
    protected BufferedImage imgs[][];
    protected int collLeftSpace, collRightSpace, collTopSpace, collBottomSpace;
    protected Type entityType;
    // anim var
    protected int animIndex;
    protected int animCounter;

    protected int state;
    public static final int IDLE = 0;
    public static final int RUNNING = 1;
    public static final int ATTACK = 2;
    public static final int HIT = 3;
    public static final int DEAD = 4;

    protected boolean isAttacking;

    public Entity(Game game, Type eType, int xPos, int yPos, int width, int height, float speed, int health, int state) {
        this.game = game;
        this.entityType = eType;
        this.speed = (int) (speed * Game.SCALE);
        this.maxSpeed = this.speed;
        this.health = health;
        this.maxHealth = health;
        this.state = state;

        initCollSpaces();

        posData = new PositionData(xPos, yPos, width, height, collLeftSpace, collRightSpace, collTopSpace, collBottomSpace);

        loadImgs();
        dir = Direction.idle;
    }

    protected void initCollSpaces() {}

    protected void loadImgs() {}

    public void update() {
        if (game.collisionCh.checkPlayer(this)) {
            contactPlayer();
        } else {
            int y = posData.getColY();
            switch (dir) {
                case fall -> {
                    y += speed;
                    if (y >= Game.WINDOW_HEIGHT)
                        game.eSpawner.enemies.remove(this);
                }
            }

            if (y != posData.getColY())
                posData.setColY(y);
        }
        updateAnim();
    }

    protected int dieCounter;

    protected void contactPlayer() {
        state = ATTACK;
        if (dieCounter == 0)
            game.player.giveDamage(1);
        dieCounter++;
        if (dieCounter >= 35) {
            game.eSpawner.enemies.remove(this);
        }
    }

    protected void updateAnim() {
        animCounter++;
        if (animCounter >= 10) {
            animIndex++;
            if (animIndex >= Constants.GetHowMSprInRow(name, state))
                animIndex = 0;
            animCounter = 0;
        }
    }

    public void giveDamage(int damage) {}

    public void heal(int howM) {
        health += howM;
        if (health > maxHealth) {
            health = maxHealth;
        }
    }

    public void increaseSpeed(int i) {
        speed += i;
        if (speed > maxSpeed)
            speed = maxSpeed;
    }

    public void draw(Graphics g) {
        int x = posData.getXPos();
        int width = posData.getWidth();
        if (dir == Direction.right) {
            x += width;
            width = -width;
        }
        g.drawImage(imgs[state][animIndex], x,  posData.getYPos(), width, posData.getHeight(), null);
        drawColliders(g);
    }

    protected void drawColliders(Graphics g) {
        if (game.listeners.debbugging) {
            g.setColor(new Color(255, 0, 0, 120));
            g.fillRect(posData.getColX(), posData.getColY(), posData.getColWidth(), posData.getColHeigth());
        }
    }

    public void reset(){}

    public boolean isAttacking() {
        return isAttacking;
    }

    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public float getSpeed() { // to set "increaseSpeed()"
        return speed;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Rectangle getCollider() {
        return posData.getCollider();
    }
}
