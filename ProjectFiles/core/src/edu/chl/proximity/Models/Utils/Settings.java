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

    //Game speed
    private int storedGameSpeed;
    private int gameSpeed;

    //Music volume
    private int storedMusicVolume;
    private int musicVolume;

    //Effects volume
    private int storedEffectsVolume;
    private int effectsVolume;

    private HashMap<Integer, Float> volumeTranslator = new HashMap<Integer, Float>();

    public Settings() {
        gameSpeed = 1;
        musicVolume = 4;
        effectsVolume = 4;

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
        if(speed > 0 || gameSpeed == 0){
            gameSpeed = speed;
            storedGameSpeed = speed;
        } else {
            togglePause();
        }
    }
    public int getGameSpeed() {
        return gameSpeed;
    }

    public int getMusicVolume() {
        return musicVolume;
    }
    public int getEffectsVolume() {
        return effectsVolume;
    }

    /**
     * A method that uses the HashMap volumeTranslator to translate the volume in
     * int to an actual volume level used by the music player
     * @return the translate volume
     */
    public float getTranslatedGameVolume(int volume) { return volumeTranslator.get(volume); }

    /**
     * Sets the level of game music volume
     * @param volume the new volume
     */
    public void setMusicVolume(int volume) {
        if(volume > 0){
            if(volumeTranslator.get(volume) != null) {
                musicVolume = volume;
                ProximityAudioPlayer.setGameMusicVolume(getTranslatedGameVolume(musicVolume));
            }
        } else {
            toggleMusicVolume();
        }
    }

    /**
     * Sets the level of game music volume
     * @param volume the new volume
     */
    public void setEffectsVolume(int volume) {
        if(volume > 0){
            if(volumeTranslator.get(volume) != null) {
                effectsVolume = volume;
                ProximityAudioPlayer.setEffectsVolume(getTranslatedGameVolume(effectsVolume));
            }
        } else {
            toggleEffectVolume();
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
    public void toggleMusicVolume() {
        if(musicVolume > 0) {
            storedMusicVolume = musicVolume;
            musicVolume = 0;
            ProximityAudioPlayer.setGameMusicVolume(getTranslatedGameVolume(musicVolume));
        } else {
            setMusicVolume(storedMusicVolume);
        }
    }

    /**
     * Toggles the game volume
     */
    public void toggleEffectVolume() {
        if(effectsVolume > 0) {
            storedEffectsVolume = effectsVolume;
            effectsVolume = 0;
            ProximityAudioPlayer.setEffectsVolume(getTranslatedGameVolume(effectsVolume));
        } else {
            setEffectsVolume(storedEffectsVolume);
        }
    }
    /**
     * Takes an instance of Settings and copies the settings from it to this instance of Settings.
     * @param settings the Settings-instance with the settings wanted
     */
    public void cloneSettings(Settings settings) {
        this.gameSpeed = settings.getGameSpeed();
        this.musicVolume = settings.getMusicVolume();
        this.effectsVolume = settings.getEffectsVolume();
    }
}
