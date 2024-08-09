package ui;

import Main.Game;
import Main.GameStates;
import Utilz.LoadSave;

import java.awt.image.BufferedImage;

import static Utilz.Constants.OptionsButtonsDetails.*;

public class RetryButton extends UrmButton {

        public RetryButton(Game game, int xPos, int yPos) {
            super(game, xPos, yPos, NAVIGATE_BUTTON_WIDTH, NAVIGATE_BUTTON_HEIGHT, 1);
        }

        protected void buttonFun() {
            game.reset();
            game.gameState = GameStates.playing;
        }

        protected void loadImgs() {
            imgs = new BufferedImage[3];

            for (int i = 0; i < imgs.length; i++) {
                imgs[i] = LoadSave.GetSpriteAtlas(LoadSave.RESUME_RESTART_HOME_B).getSubimage(i * NAVIGATE_BUTTON_DEFAULT_WIDTH, rowIndex* NAVIGATE_BUTTON_DEFAULT_HEIGHT, NAVIGATE_BUTTON_DEFAULT_WIDTH, NAVIGATE_BUTTON_DEFAULT_HEIGHT);
            }
        }
    }
