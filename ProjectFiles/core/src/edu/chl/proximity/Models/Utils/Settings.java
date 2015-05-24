package edu.chl.proximity.Models.Utils;

import java.util.HashMap;

/**
 * @author Linda Evaldsson
 * @date 2015-05-07
 *
 * This is a class for keeping track of all the settings in the game. Initially used for sound and game speed.
 *
 * 24/05 modified by Linda Evaldsson. Added the functionality of toggling music.
 */
public class Settings {

    private int storedGameSpeed;
    private int storedVolume;
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
     * Set the speed in which the game logic updates
     * @param speed how fast the game logic should update
     */
    public void setGameSpeed(int speed){
        if(speed > 0){
            gameSpeed = speed;
        } else {
            togglePause();
        }
    }
    public int getGameSpeed() {
        return gameSpeed;
    }

    public int getGameVolume() {
        return gameVolume;
    }

    /**
     * A method that uses the HashMap volumeTranslator to translate the volume in
     * int to an actual volume level used by the music player
     * @return the translate volume
     */
    public float getTranslatedGameVolume() { return volumeTranslator.get(gameVolume); }

    /**
     * Sets the level of game volume
     * @param volume the new volume
     */
    public void setGameVolume(int volume) {
        if(volume > 0){
            gameVolume = volume;
            ProximityAudioPlayer.setGameMusicVolume(getTranslatedGameVolume());
        } else {
            toggleSound();
        }

    }

    /**
     * Toggles the game speed
     */
    public void togglePause() {
        if(gameSpeed > 0) {
            storedGameSpeed = gameSpeed;
            gameSpeed = 0;
        } else {
            setGameSpeed(storedGameSpeed);
        }
    }

    /**
     * Toggles the game volume
     */
    public void toggleSound() {
        if(gameVolume > 0) {
            storedVolume = gameVolume;
            gameVolume = 0;
            ProximityAudioPlayer.setGameMusicVolume(getTranslatedGameVolume());
        } else {
            setGameVolume(storedVolume);
        }
    }

    /**
     * Takes an instance of Settings and copies the settings from it to this instance of Settings.
     * @param settings the Settings-instance with the settings wanted
     */
    public void cloneSettings(Settings settings) {
        this.gameSpeed = settings.getGameSpeed();
        this.gameVolume = settings.getGameVolume();
    }
}
