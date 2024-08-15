package ui.shop;

import Main.Game;
import ui.UrmButton;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class SkinToBuy extends UrmButton {

    public String name;
    public int width, height, collLeftSpace, collRightSpace, collTopSpace, collBottomSpace;
    public BufferedImage imgs[][];

    public SkinToBuy(Game game, int xPos, int yPos, int width, int height, int rowIndex) {
        super(game, xPos, yPos, width, height, rowIndex);
        setVar();
    }

    private void setVar() {
        width = bounds.width;
        height = bounds.height;
    }

    protected void buttonFun() {
        game.player.changeSkin(this);
    }

    protected void loadImgs() {

    }

    public void update() {

    }

    public void draw(Graphics g) {}
}
