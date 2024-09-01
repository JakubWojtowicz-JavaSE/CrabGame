package ui.menu;

import Main.Game;
import Main.GameStates;
import ui.UrmButton;

import static Utilz.Constants.MenuButtonsDetails.*;

public class OptionsButton extends UrmButton {

    public OptionsButton(Game game, int xPos, int yPos) {
        super(game, xPos, yPos, BUTTON_WIDTH, BUTTON_HEIGHT, 1);
    }

    protected void buttonFun() {
        game.changeGameState(GameStates.options);
    }
}
