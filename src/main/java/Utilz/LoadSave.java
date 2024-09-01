package Utilz;

import Entity.Direction;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.Path;

public class LoadSave {

    public static final String BASKGROUND_IMG = "ui/background.png";
    public static final String MENU_IMG = "ui/menu_background.png";
    public static final String BUTTONS_IMGS = "ui/buttons/button_atlas.png";
    public static final String OPTIONS_IMG = "ui/options_background.png";
    public static final String SOUND_B_IMG = "ui/buttons/sound_button.png";
    public static final String VOLUME_B_IMG = "ui/buttons/volume_buttons.png";
    public static final String SHOP_ICON_IMG = "ui/buttons/shop_icon.png";
    public static final String HEALTH_BAR_IMG = "ui/health_power_bar.png";
    public static final String PAUSE_MENU = "ui/pause_menu.png";
    public static final String DEATH_SCREEN = "ui/death_screen.png";
    public static final String RESUME_RESTART_HOME_B = "ui/buttons/urm_buttons.png";
    public static final String CRABBY_SPRITES = "entitySprites/crabby_sprite.png";
    public static final String BALL_SPRITE = "entitySprites/ball.png";
    public static final String POTION_SPRITE = "entitySprites/potions_sprites.png";
    public static final String MONEY_SPRITE = "entitySprites/money.png";

    // skins
    public static final String PINKSTAR_SKIN_ATLAS = "entitySprites/pinkstar_atlas.png";
    public static final String SHARK_SKIN_ATLAS = "entitySprites/shark_atlas.png";

    public static final String path = System.getenv("APPDATA") + "/JakubWojtowicz/CrabGame";
    private static File pathF = new File(path);
    public static final String fileName = "/data.crb";

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
            if (!pathF.exists())
                pathF.mkdirs();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path + fileName));

            oos.writeObject(data);

            oos.close();
            System.out.println("Data saved");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Data LoadData() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path + fileName));

            return  (Data) ois.readObject();
        } catch (IOException e) {
            return new Data();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
