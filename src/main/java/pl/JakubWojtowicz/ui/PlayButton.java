package pl.JakubWojtowicz.ui;

import pl.JakubWojtowicz.Main.Game;
import pl.JakubWojtowicz.Main.GameStates;

public class PlayButton extends UrmButton {

    public PlayButton(Game game, int xPos, int yPos) {
        super(game, xPos, yPos, 0);
    }

    protected void buttonFun() {
        game.ui.resetButtons();
        game.gameState = GameStates.playing;
    }
}
