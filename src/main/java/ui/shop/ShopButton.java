package ui.shop;

import Main.Game;
import Main.GameStates;
import Utilz.LoadSave;
import ui.UrmButton;

import java.awt.image.BufferedImage;

import static Utilz.Constants.OptionsButtonsDetails.*;

public class ShopButton extends UrmButton {

    public ShopButton(Game game, int xPos, int yPos) {
        super(game, xPos, yPos, NAVIGATE_BUTTON_WIDTH, NAVIGATE_BUTTON_HEIGHT, 0);
    }

    protected void buttonFun() {
        game.changeGameState(GameStates.shop);
    }

    protected void loadImgs() {
        imgs = new BufferedImage[1];
        imgs[0] = LoadSave.GetSpriteAtlas(LoadSave.SHOP_ICON_IMG);
    }

    public void update() {}
}
