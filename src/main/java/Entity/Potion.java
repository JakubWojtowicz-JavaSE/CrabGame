package Entity;

import Main.Game;
import Utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Utilz.Constants.PotionDetails.*;

public class Potion extends Entity {

    public Potion(Game game, Type eType, int xPos, int yPos, float speed) {
        super(game, eType, xPos, yPos, POTION_WIDTH, POTION_HEIGHT, speed, 1, RUNNING);
        name = "Potion";
        dir = Direction.fall;
    }

    protected void initCollSpaces() {
        if (entityType == Type.potion_g) {
            collLeftSpace = 4;
            collRightSpace = 0;
        } else if (entityType == Type.potion_b) {
            collLeftSpace = 6;
            collRightSpace = 4;
        }
        collTopSpace = 4;
        collBottomSpace = 0;
    }

    protected void loadImgs() {
        imgs = new BufferedImage[SPRITES_IN_ROW][SPRITES_IN_COL];

        int j = 0;
        if (entityType == Type.potion_g)
            j = 1;


        for (int i = 0; i < imgs[0].length; i++) {
            imgs[0][i] = LoadSave.GetSpriteAtlas(LoadSave.POTION_SPRITE).getSubimage(i*POTION_DEFAULT_WIDTH, j*POTION_DEFAULT_HEIGHT, POTION_DEFAULT_WIDTH, POTION_DEFAULT_HEIGHT);
        }
    }

    protected void contactPlayer() {
        if (entityType == Type.potion_g) {
            if (dieCounter == 0) {
                state = ATTACK;
                game.player.setMaxHealth(game.player.getMaxHealth()+1);;
                game.player.heal(1);
                game.player.setMaxSpeed(game.player.getMaxSpeed()+1);
                game.player.increaseSpeed(1);
            }
        } else if (entityType == Type.potion_b) {
            if (dieCounter == 0) {
                state = ATTACK;
                game.player.giveDamage(1);
                game.player.increaseSpeed(-1);
            }
        }
        dieCounter++;
        if (dieCounter >= 35) {
            game.eSpawner.objects.remove(this);
        }
    }

    public void draw(Graphics g) {
        g.drawImage(imgs[0][animIndex], posData.getXPos(), posData.getYPos(), posData.getWidth(), posData.getHeight(), null);
        drawColliders(g);
    }
}
