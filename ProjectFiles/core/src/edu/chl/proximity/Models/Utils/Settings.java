package edu.chl.proximity.Models.Utils;

import java.util.HashMap;

/**
 * @author Linda Evaldsson
 * @date 2015-05-07
 *
 * This is a class for keeping track of all the settings in the game. Initially used for sound and game speed.
 */
public class Settings {

    private int storedGameSpeed;
    private int gameSpeed;
    private int gameVolume;
    private HashMap<Integer, Float> volumeTranslator = new HashMap<Integer, Float>();

    public Settings() {
        gameSpeed = 1;
        gameVolume = 4;

        volumeTranslator.put(0, 0f);
        volumeTranslator.put(1, 0.0125f);
        volumeTranslator.put(2, 0.025f);
        volumeTranslator.put(3, 0.05f);
        volumeTranslator.put(4, 0.1f);
        volumeTranslator.put(5, 0.2f);
        volumeTranslator.put(6, 0.4f);
        volumeTranslator.put(7, 0.8f);
        volumeTranslator.put(8, 1.6f);
    }

    /**
     * set the speed in which the game logic updates
     * The method does nothing if input speedMultiplier is less than 0.
     * @param speed how fast the game logic should update
     */
    public void setGameSpeed(int speed){
        if(speed >= 0){
            gameSpeed = speed;
        }
    }
    public int getGameSpeed() {
        return gameSpeed;
    }

    public int getGameVolume() {
        return gameVolume;
    }

    public float getTranslatedGameVolume() { return volumeTranslator.get(gameVolume); }

    public void setGameVolume(int gameVolume) {
        this.gameVolume = gameVolume;
        ProximityAudioPlayer.setGameMusicVolume(getTranslatedGameVolume());
    }

    public void togglePause() {
        if(gameSpeed > 0) {
            storedGameSpeed = gameSpeed;
            setGameSpeed(0);
        } else {
            setGameSpeed(storedGameSpeed);
        }
    }

    public void cloneSettings(Settings settings) {
        this.gameSpeed = settings.getGameSpeed();
        this.gameVolume = settings.getGameVolume();
    }
}
