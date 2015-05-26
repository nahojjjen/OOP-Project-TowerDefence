package edu.chl.proximity.Models.Utils;

import com.badlogic.gdx.audio.Sound;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityRandom;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Johan Swanberg
 * @date 22/5/2015
 * A static class for playing sound files
 */
public class ProximityAudioPlayer {
    private static Settings settings = new Settings();
    private static ProximitySound gameMusic;
    private static long soundID = 0;

    /**
     * change the settings to use another configuration
     * @param inputSettings what settings configuration to load
     */
    public static void setSettings(Settings inputSettings){
        settings = inputSettings;
    }

    /**
     * play a sound on the computer speakers
     * @param sound what sound to play
     * @param pitch what pitch the sound should play
     * @param pan what pan the sound should play (position, left / right)
     */
    public static void playSound(ProximitySound sound, float pitch, float pan){
        Sound rawSound = sound.getSound();
        if (rawSound != null){
            rawSound.play(settings.getTranslatedGameVolume(), pitch, pan);
        }

    }

    /**
     * starts a new random song from the game music folder
     */
    public static void playGameMusic(){

        List<String> musicFiles = getAllMusicFiles();
        gameMusic = getRandomSong(musicFiles);

        if (gameMusic != null){
            Sound rawSound = gameMusic.getSound();
            rawSound.stop();
            soundID = rawSound.loop(settings.getTranslatedGameVolume()/3);
        }

    }

    /**
     * gets a random string out of a list of strings
     * @param musicFiles the list containing the music names
     * @return one of the music files, with random distributed chance
     */
    private static ProximitySound getRandomSong(List<String> musicFiles){
        double randomMusic = ProximityRandom.getRandomDoubleBetween(0, musicFiles.size() - 0.0000000001);
        int randomSelected = (int)(randomMusic);
        return new ProximitySound(Constants.FILE_PATH + "GameMusic/" +musicFiles.get(randomSelected));

    }

    /**
     * get all files with the file type .mp3 within the GameMusic folder
     * @return a list of all the file names of mp3 files in the gamemusic folder
     */
    private static List<String> getAllMusicFiles(){
        List<String> musicFiles = new ArrayList<>();

        //get all mp3 files
        File folder = new File(Constants.FILE_PATH + "/GameMusic");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].getName().contains(".mp3")) {
                musicFiles.add(listOfFiles[i].getName());
            }
        }
        return musicFiles;
    }

    /**
     * pauses the game music
     */
    public static void pauseGameMusic(){
        if (gameMusic != null){
            Sound rawSound = gameMusic.getSound();
            rawSound.pause();
        }
    }


    /**
     * changes the music volume
     * @param volume
     */
    public static void setGameMusicVolume(float volume){
        if (gameMusic != null){
            Sound rawSound = gameMusic.getSound();
            rawSound.setVolume(soundID,volume/3);
        }
    }

    /**
     * resumes the game music without chaning the song
     */
    public static void resumeGameMusic(){
        if (gameMusic != null){
            Sound rawSound = gameMusic.getSound();
            rawSound.resume();
        }
    }
}
