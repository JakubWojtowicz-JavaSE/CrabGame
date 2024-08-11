package Entity;

import Main.Game;
import Utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Utilz.Constants.BallDetails.*;

public class BallEnemy extends Entity {

    public BallEnemy(Game game, int xPos, int yPos, float speed) {
        super(game, Type.bad_bullet, xPos, yPos, BALL_WIDTH, BALL_HEIGHT, speed, 1, RUNNING);
        name = "Ball";
        dir = Direction.fall;
    }

    protected void loadImgs() {
        imgs = new BufferedImage[1][1];
        imgs[0][0] = LoadSave.GetSpriteAtlas(LoadSave.BALL_SPRITE);
    }

    public void draw(Graphics g) {
        g.drawImage(imgs[0][0], posData.getXPos(), posData.getYPos(), posData.getWidth(), posData.getHeight(), null);
        drawColliders(g);
    }
}
