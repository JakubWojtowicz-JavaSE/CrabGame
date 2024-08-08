package ui;

import Main.Game;
import Main.GameStates;
import Utilz.Constants;

import java.awt.*;

import static Utilz.Constants.DeathScreenDetails.*;

public class RetryButton extends UrmButton {

        public RetryButton(Game game, int xPos, int yPos, int rowIndex) {
            super(game, xPos, yPos, DEATH_S_RETRY_B_WIDTH, DEATH_S_RETRY_B_HEIGHT, rowIndex);
        }

        protected void buttonFun() {
            game.reset();
            game.gameState = GameStates.playing;
        }

        protected void loadImgs() {}

        public void draw(Graphics g) {}
    }
