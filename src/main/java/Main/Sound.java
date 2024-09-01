package Main;

import javax.sound.sampled.*;
import java.net.URL;
import java.util.ArrayList;

public class Sound {

    public static final int DEFAULT_MUSIC = 0;
    public static final int GAME_OVER = 1;

    Clip clip;
    ArrayList<URL> soundURL;

    public Sound() {
        soundURL = new ArrayList<>();

        soundURL.add(getClass().getResource("/sounds/Beach_house.wav"));
        soundURL.add(getClass().getResource("/sounds/GameOver.wav"));
        setFile(0);
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL.get(i));
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {}
    }

    public void setVolume(float volume) {
        final FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float range = control.getMaximum() - control.getMinimum();
        float gain = ((range/100 * volume) + control.getMinimum());
        control.setValue(gain);
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
