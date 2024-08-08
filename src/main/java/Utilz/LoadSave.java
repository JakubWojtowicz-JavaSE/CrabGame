package Utilz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class LoadSave {

    public static final String BASKGROUND_IMG = "background.png";
    public static final String MENU_IMG = "menu_background.png";
    public static final String BUTTONS_IMGS = "button_atlas.png";
    public static final String HEALTH_BAR_IMG = "health_power_bar.png";
    public static final String PAUSE_MENU = "pause_menu.png";
    public static final String DEATH_SCREEN = "death_screen.png";
    public static final String CRABBY_SPRITES = "crabby_sprite.png";
    public static final String BALL_SPRITE = "ball.png";
    public static final String POTION_SPRITE = "potions_sprites.png";

    public static final String path = "data.crb";

    public static BufferedImage GetSpriteAtlas(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(LoadSave.class.getResourceAsStream("/" + path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return img;
    }

    public static void SaveData(Data data) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));

            oos.writeObject(data);

            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Data LoadData() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));

            return  (Data) ois.readObject();
        } catch (IOException e) {
            return new Data();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
