package pl.JakubWojtowicz.ui;

import pl.JakubWojtowicz.Main.Game;
import pl.JakubWojtowicz.Utilz.LoadSave;

import java.awt.image.BufferedImage;

import static pl.JakubWojtowicz.Utilz.Constants.ButtonsDetails.*;

public class PlayButton extends UrmButton {

    public PlayButton(Game game, int xPos, int yPos) {
        super(game, xPos, yPos, 0);
    }
}
