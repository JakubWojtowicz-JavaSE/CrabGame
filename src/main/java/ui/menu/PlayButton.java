package ui.menu;

import Main.Game;
import Main.GameStates;
import ui.UrmButton;

import static Utilz.Constants.MenuButtonsDetails.*;

    public class PlayButton extends UrmButton {

        public PlayButton(Game game, int xPos, int yPos) {
            super(game, xPos, yPos, BUTTON_WIDTH, BUTTON_HEIGHT, 0);
        }

        protected void buttonFun() {
            game.player.reset(false);
            game.ui.resetButtons();
            game.changeGameState(GameStates.playing);
        }
    }
