package ui;

import Main.Game;
import Main.GameStates;
import Utilz.Constants;

import java.awt.*;

    public class RetryButton extends UrmButton {

        public RetryButton(Game game, int xPos, int yPos, int rowIndex) {
            super(game, xPos, yPos, Constants.DeathScreenDetails.DEATH_S_RETRY_B_WIDTH, Constants.DeathScreenDetails.DEATH_S_RETRY_B_HEIGHT, rowIndex);
        }

        protected void buttonFun() {
            game.reset();
            game.gameState = GameStates.playing;
        }

        protected void loadImgs() {}

        public void draw(Graphics g) {}
    }
