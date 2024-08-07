package ui;

import Main.Game;

import static Utilz.Constants.ButtonsDetails.BUTTON_HEIGHT;
import static Utilz.Constants.ButtonsDetails.BUTTON_WIDTH;

public class QuitButton extends UrmButton {

    public QuitButton(Game game, int xPos, int yPos) {
        super(game, xPos, yPos, BUTTON_WIDTH, BUTTON_HEIGHT, 2);
    }

    protected void buttonFun() {
        System.exit(0);
    }
}
