package pl.JakubWojtowicz.ui;

import pl.JakubWojtowicz.Main.Game;
import pl.JakubWojtowicz.Utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static pl.JakubWojtowicz.Utilz.Constants.ButtonsDetails.*;

public class UrmButton {

    protected Game game;
    protected BufferedImage[] imgs;

    protected int xPos, yPos, state, rowIndex;

    public UrmButton(Game game, int xPos, int yPos, int rowIndex) {
        this.game = game;
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;

        state = 0;
        loadImgs();
    }

    protected void loadImgs() {
        imgs = new BufferedImage[3];

        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = LoadSave.GetSpriteAtlas(LoadSave.BUTTONS_IMGS).getSubimage(i*BUTTON_DEFAULT_WIDTH, rowIndex*BUTTON_DEFAULT_HEIGHT, BUTTON_DEFAULT_WIDTH, BUTTON_DEFAULT_HEIGHT);
        }
    }

    public void draw(Graphics g) {
        g.drawImage(imgs[state], xPos, yPos, BUTTON_WIDTH, BUTTON_HEIGHT, null);
    }
}
