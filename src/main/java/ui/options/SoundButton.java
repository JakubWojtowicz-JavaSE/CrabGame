package ui.options;

import Entity.Type;
import Main.Game;
import Utilz.LoadSave;
import ui.UrmButton;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Utilz.Constants.OptionsButtonsDetails.*;

public class SoundButton extends UrmButton {

    private Type type;
    private BufferedImage imgs[][];

    public SoundButton(Game game, int xPos, int yPos, Type type) {
        super(game, xPos, yPos, SOUND_BUTTON_WIDTH, SOUND_BUTTON_HEIGHT, 0);
        this.type = type;

        setRowIndex();
    }

    private void setRowIndex() {
        if (type == Type.music)
            if (game.data.isMusicOn)
                rowIndex = 0;
            else
                rowIndex = 1;
        else if (type == Type.sfx)
            if (game.data.isSFXOn)
                rowIndex = 0;
            else
                rowIndex = 1;
    }

    public void updateVar() {
        setRowIndex();
    }

    protected void buttonFun() {
        rowIndex++;

        if (type == Type.music) {
            if (rowIndex > 1) {
                rowIndex = 0;
                game.data.isMusicOn = true;
            } else
                game.data.isMusicOn = false;
            game.resetSound(false);
        } else if (type == Type.sfx) {
            if (rowIndex > 1) {
                rowIndex = 0;
                game.data.isSFXOn = true;
            } else
                game.data.isSFXOn = false;
//            game.resetSound(true);
        }
    }

    protected void loadImgs() {
        imgs = new BufferedImage[2][3];

        for (int j = 0; j < imgs.length; j++)
            for (int i = 0; i < imgs[0].length; i++)
                imgs[j][i] = LoadSave.GetSpriteAtlas(LoadSave.SOUND_B_IMG).getSubimage(i * SOUND_BUTTON_DEFAULT_WIDTH, j * SOUND_BUTTON_DEFAULT_HEIGHT, SOUND_BUTTON_DEFAULT_WIDTH, SOUND_BUTTON_DEFAULT_HEIGHT);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(imgs[rowIndex][index], bounds.x, bounds.y, bounds.width, bounds.height, null);
    }
}
