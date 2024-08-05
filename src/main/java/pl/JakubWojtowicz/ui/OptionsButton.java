package pl.JakubWojtowicz.ui;

import pl.JakubWojtowicz.Main.Game;

public class OptionsButton extends UrmButton {

    public OptionsButton(Game game, int xPos, int yPos) {
        super(game, xPos, yPos, 1);
    }

    protected void buttonFun() {
        System.out.println("Options"); // to implement
    }
}
