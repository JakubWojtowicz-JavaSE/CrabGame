package pl.JakubWojtowicz.ui;

import pl.JakubWojtowicz.Main.Game;
import pl.JakubWojtowicz.Utilz.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static pl.JakubWojtowicz.Utilz.Constants.ButtonsDetails.*;

public class UrmButton {

    protected Game game;
    protected BufferedImage[] imgs;
    protected Rectangle bounds;

    protected boolean isPressed, isMouseOver, isActiv;
    protected int state, rowIndex;

    public UrmButton(Game game, int xPos, int yPos, int rowIndex) {
        this.game = game;
        this.rowIndex = rowIndex;

        bounds = new Rectangle(xPos, yPos, BUTTON_WIDTH, BUTTON_HEIGHT);

        state = 0;
        loadImgs();
    }

    protected void loadImgs() {
        imgs = new BufferedImage[3];

        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = LoadSave.GetSpriteAtlas(LoadSave.BUTTONS_IMGS).getSubimage(i*BUTTON_DEFAULT_WIDTH, rowIndex*BUTTON_DEFAULT_HEIGHT, BUTTON_DEFAULT_WIDTH, BUTTON_DEFAULT_HEIGHT);
        }
    }

    public void update() {
        if (isActiv) {
            if (isPressed)
                state = 2;
            else if (isMouseOver)
                state = 1;
            else
                state = 0;
        }
    }

    protected void buttonFun() {}

    public void draw(Graphics g) {
        if (isActiv) {
            g.drawImage(imgs[state], bounds.x, bounds.y, bounds.width, bounds.height, null);
        }
    }

    public void mousePressed(MouseEvent e) {
        if (isActiv) {
            if (isIn(e))
                isPressed = true;
            else
                isPressed = false;
        }
    }

    public void mouseRelased(MouseEvent e) {
        if (isActiv) {
            if (isIn(e)) {
                if (isPressed)
                    buttonFun();
                isPressed = false;
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
        if (isActiv) {
            if (isIn(e))
                isMouseOver = true;
            else {
                isMouseOver = false;
                isPressed = false;
            }
        }
    }

    private boolean isIn(MouseEvent e) {
        if (bounds.contains(e.getX(), e.getY()))
            return true;
        else
            return false;
    }

    public void setActiv(boolean activ) {
        isActiv = activ;
    }
}
