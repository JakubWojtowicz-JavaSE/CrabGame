package ui;

import Main.Game;
import Main.GameStates;

import static Utilz.Constants.ButtonsDetails.*;

    public class PlayButton extends UrmButton {

        public PlayButton(Game game, int xPos, int yPos) {
            super(game, xPos, yPos, BUTTON_WIDTH, BUTTON_HEIGHT, 0);
        }

        protected void buttonFun() {
            game.ui.resetButtons();
            game.gameState = GameStates.playing;
        }
    }
