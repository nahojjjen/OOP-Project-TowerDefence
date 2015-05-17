package edu.chl.proximity.Models.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Johan
 * @date 2015-05-17.
 *
 * A service class for sound
 */
public class ProximitySound {
    private Sound sound;

    /**
     * create a new sound file from a file
     * @param filePath
     */
    public ProximitySound(String filePath){
        sound = Gdx.audio.newSound(new FileHandle(filePath));
    }

    public void play(float volume, float pitch, float pan){
        sound.play(volume, pitch, pan);
    }
}
