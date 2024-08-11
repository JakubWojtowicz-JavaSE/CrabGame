package Entity;

import Main.Game;
import Utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Utilz.Constants.MoneyDetails.*;

public class Money extends Entity {

    public Money(Game game, int xPos, int yPos, float speed) {
        super(game, Type.money, xPos, yPos, MONEY_WIDTH, MONEY_HEIGHT, speed, 1, RUNNING);
        name = "Money";
        dir = Direction.fall;
    }

    protected void loadImgs() {
        imgs = new BufferedImage[1][1];
        imgs[0][0] = LoadSave.GetSpriteAtlas(LoadSave.MONEY_SPRITE);
    }

    protected void contactPlayer() {
        if (dieCounter == 0)
            game.player.increaseBudget(1);
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
