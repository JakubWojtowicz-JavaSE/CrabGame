package Utilz;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {

    public int skinNum, bestScore, budget, volume;
    public ArrayList<Integer> unlockSkins = new ArrayList<>();
    public boolean isMusicOn, isSFXOn;
}
