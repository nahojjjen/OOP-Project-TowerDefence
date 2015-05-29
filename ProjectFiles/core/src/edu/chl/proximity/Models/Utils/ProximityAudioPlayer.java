package edu.chl.proximity.Models.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import edu.chl.proximity.Utilities.Constants;

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
    private static Music gameMusic;

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
            rawSound.play(settings.getTranslatedGameVolume(settings.getEffectsVolume()), pitch, pan);
        }

    }

    /**
     * starts a new random song from the game music folder
     */
    public static void playGameMusic(){

        if (gameMusic != null){
            gameMusic.dispose();
        }

        List<String> musicFiles = getAllMusicFiles();
        gameMusic = getRandomSong(musicFiles);

        if (gameMusic != null){
            gameMusic.stop();
            gameMusic.play();
            gameMusic.setLooping(true);
            gameMusic.setVolume(settings.getTranslatedGameVolume(settings.getMusicVolume())/3);
        }

    }

    /**
     * gets a random string out of a list of strings
     * @param musicFiles the list containing the music names
     * @return one of the music files, with random distributed chance
     */
    private static Music getRandomSong(List<String> musicFiles){

        double randomMusic = ProximityRandom.getRandomDoubleBetween(0, musicFiles.size() - 0.0000000001);
        int randomSelected = (int)(randomMusic);
        FileHandle handle =  new FileHandle(Constants.FILE_PATH + "GameMusic/" +musicFiles.get(randomSelected));
        return Gdx.audio.newMusic(handle);

    }

    /**
     * get all files with the file type .mp3 within the GameMusic folder
     * @return a list of all the file names of mp3 files in the gamemusic folder
     */
    private static List<String> getAllMusicFiles(){
        List<String> musicFiles = new ArrayList<String>();

        //get all mp3 files
        File folder = new File(Constants.FILE_PATH + "/GameMusic");
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile() && listOfFile.getName().contains(".mp3")) {
                    musicFiles.add(listOfFile.getName());
                }
            }
        }
        return musicFiles;
    }

    /**
     * pauses the game music
     */
    public static void pauseGameMusic(){
        if (gameMusic != null){
            gameMusic.pause();
        }
    }


    /**
     * changes the music volume
     * @param volume volume to be set
     */
    public static void setGameMusicVolume(float volume){
        if (gameMusic != null){
            gameMusic.setVolume(volume/3);
        }
    }

    /**
     * changes the effects volume
     * @param volume volume to be set
     */
    public static void setEffectsVolume(float volume){
        //Todo: Write this function
    }

    /**
     * resumes the game music without chaning the song
     */
    public static void resumeGameMusic(){
        if (gameMusic != null){
            gameMusic.play();
        }
    }
}
