package ui;

import Main.Game;
import Utilz.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static Utilz.Constants.MenuButtonsDetails.*;

public abstract class UrmButton {

    protected Game game;
    protected BufferedImage[] imgs;
    protected Rectangle bounds;

    protected boolean isPressed, isMouseOver;
    protected int index, rowIndex;

    public UrmButton(Game game, int xPos, int yPos, int width, int height, int rowIndex) {
        this.game = game;
        this.rowIndex = rowIndex;

        bounds = new Rectangle(xPos, yPos, width, height);

        index = 0;
        loadImgs();
    }

    protected void loadImgs() {
        imgs = new BufferedImage[3];

        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = LoadSave.GetSpriteAtlas(LoadSave.BUTTONS_IMGS).getSubimage(i*BUTTON_DEFAULT_WIDTH, rowIndex*BUTTON_DEFAULT_HEIGHT, BUTTON_DEFAULT_WIDTH, BUTTON_DEFAULT_HEIGHT);
        }
    }

    public void update() {
        if (isPressed)
            index = 2;
        else if (isMouseOver)
            index = 1;
        else
            index = 0;
    }

    public void updateVar() {}

    protected void buttonFun() {}

    public void draw(Graphics g) {
        g.drawImage(imgs[index], bounds.x, bounds.y, bounds.width, bounds.height, null);
    }

    public void mousePressed(MouseEvent e) {
        if (isIn(e))
            isPressed = true;
        else
            isPressed = false;
    }

    public void mouseRelased(MouseEvent e) {
        if (isIn(e)) {
            if (isPressed)
                buttonFun();
            isPressed = false;
        }
    }

    public void mouseMoved(MouseEvent e) {
        if (isIn(e))
            isMouseOver = true;
        else {
            isMouseOver = false;
            isPressed = false;
        }
    }

    public void mouseDragged(MouseEvent e) {}

    private boolean isIn(MouseEvent e) {
        if (bounds.contains(e.getX(), e.getY()))
            return true;
        else
            return false;
    }
}
