package edu.chl.proximity.Models.Utils;

import com.badlogic.gdx.audio.Sound;
import edu.chl.proximity.Models.Utils.ProximitySound;
import edu.chl.proximity.Models.Utils.Settings;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Johan Swanberg
 * @date 22/5/2015
 * A static class for playing sound files
 */
public class ProximityAudioPlayer {
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
        rawSound.play(settings.getTranslatedGameVolume(), pitch, pan);
    }

    public static void playGameMusic(){
        System.out.println("Gamemusic: " + gameMusic);
        System.out.println("Volume:" + settings.getGameVolume());
        if (gameMusic != null){

            Sound rawSound = gameMusic.getSound();
            rawSound.stop();
            soundID = rawSound.loop(settings.getTranslatedGameVolume()/3);
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
