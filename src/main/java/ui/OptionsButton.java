package ui;

import Main.Game;

import static Utilz.Constants.ButtonsDetails.BUTTON_HEIGHT;
import static Utilz.Constants.ButtonsDetails.BUTTON_WIDTH;

public class OptionsButton extends UrmButton {

    public OptionsButton(Game game, int xPos, int yPos) {
        super(game, xPos, yPos, BUTTON_WIDTH, BUTTON_HEIGHT, 1);
    }

    protected void buttonFun() {
        System.out.println("Options"); // to implement
    }
}
