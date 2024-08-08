package ui;

import Main.Game;
import Main.GameStates;
import Utilz.Constants;

import java.awt.*;

import static Utilz.Constants.DeathScreenDetails.*;

public class RageQuitButton extends UrmButton {

    public RageQuitButton(Game game, int xPos, int yPos, int rowIndex) {
        super(game, xPos, yPos, DEATH_S_RAGE_Q_B_WIDTH, DEATH_S_RAGE_Q_B_HEIGHT, rowIndex);
    }

    protected void buttonFun() {
        game.reset();
        game.gameState = GameStates.menu;
    }

    protected void loadImgs() {}

    public void draw(Graphics g) {}
}
