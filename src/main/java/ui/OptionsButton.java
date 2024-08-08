package ui;

import Main.Game;
import Main.GameStates;

import static Utilz.Constants.MenuButtonsDetails.BUTTON_HEIGHT;
import static Utilz.Constants.MenuButtonsDetails.BUTTON_WIDTH;

public class OptionsButton extends UrmButton {

    public OptionsButton(Game game, int xPos, int yPos) {
        super(game, xPos, yPos, BUTTON_WIDTH, BUTTON_HEIGHT, 1);
    }

    protected void buttonFun() {
        game.gameState = GameStates.options;
    }
}
