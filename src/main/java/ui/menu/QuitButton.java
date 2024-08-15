package ui.menu;

import Main.Game;
import Utilz.LoadSave;
import ui.UrmButton;

import static Utilz.Constants.MenuButtonsDetails.*;

public class QuitButton extends UrmButton {

    public QuitButton(Game game, int xPos, int yPos) {
        super(game, xPos, yPos, BUTTON_WIDTH, BUTTON_HEIGHT, 2);
    }

    protected void buttonFun() {
        LoadSave.SaveData(game.data);
        System.exit(0);
    }
}
