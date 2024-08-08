package ui;

import Main.Game;
import Main.GameStates;
import Utilz.LoadSave;

import java.awt.image.BufferedImage;

import static Utilz.Constants.OptionsButtonsDetails.*;

public class RageQuitButton extends UrmButton {

    public RageQuitButton(Game game, int xPos, int yPos) {
        super(game, xPos, yPos, BUTTON_WIDTH, BUTTON_HEIGHT, 2);
    }

    protected void buttonFun() {
        game.reset();
        game.gameState = GameStates.menu;
    }

    protected void loadImgs() {
        imgs = new BufferedImage[3];

        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = LoadSave.GetSpriteAtlas(LoadSave.RESUME_RESTART_HOME_B).getSubimage(i * BUTTON_DEFAULT_WIDTH, rowIndex*BUTTON_DEFAULT_HEIGHT, BUTTON_DEFAULT_WIDTH, BUTTON_DEFAULT_HEIGHT);
        }
    }
}
