package edu.chl.proximity.Utilities;

import com.badlogic.gdx.audio.Sound;
import edu.chl.proximity.Models.Utils.ProximitySound;
import edu.chl.proximity.Models.Utils.Settings;

/**
 * @author Johan Swanberg
 * @date 22/5/2015
 * A static class for playing sound files
 */
public class ProximityPlayer {
    private static Settings settings = new Settings();
    private static ProximitySound gameMusic = new ProximitySound(Constants.FILE_PATH + "GameMusic/hello.mp3");
    private static long soundID = 0;

    /**
     * change the settings to use another configuration
     * @param inputSettings what settings configuration to load
     */
    public static void setSettings(Settings inputSettings){
        settings = inputSettings;
    }

    public static void playSound(ProximitySound sound, float pitch, float pan){
        Sound rawSound = sound.getSound();
        rawSound.play(settings.getGameVolume(), pitch, pan);
    }

    public static void playGameMusic(){

        if (gameMusic != null){

            Sound rawSound = gameMusic.getSound();
            rawSound.stop();
            soundID = rawSound.loop(settings.getGameVolume()/3);
        }

    }

    public static void pauseGameMusic(){
        if (gameMusic != null){
            Sound rawSound = gameMusic.getSound();
            rawSound.pause();
        }
    }


    public static void setGameMusicVolume(float volume){
        if (gameMusic != null){
            Sound rawSound = gameMusic.getSound();
            rawSound.setVolume(soundID,volume/3);
        }
    }
    public static void resumeGameMusic(){
        if (gameMusic != null){
            Sound rawSound = gameMusic.getSound();
            rawSound.resume();
        }
    }
}
