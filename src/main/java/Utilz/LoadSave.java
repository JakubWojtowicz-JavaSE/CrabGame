package Utilz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LoadSave {

    public static final String BASKGROUND_IMG = "background.png";
    public static final String MENU_IMG = "menu_background.png";
    public static final String BUTTONS_IMGS = "button_atlas.png";
    public static final String HEALTH_BAR_IMG = "health_power_bar.png";
    public static final String DEATH_SCREEN = "death_screen.png";
    public static final String CRABBY_SPRITES = "crabby_sprite.png";
    public static final String BALL_SPRITE = "ball.png";
    public static final String POTION_SPRITE = "potions_sprites.png";

    public static BufferedImage GetSpriteAtlas(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(LoadSave.class.getResourceAsStream("/" + path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return img;
    }
}
