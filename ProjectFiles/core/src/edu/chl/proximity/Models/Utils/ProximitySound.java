package edu.chl.proximity.Models.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityPlayer;
import edu.chl.proximity.Utilities.TestChecker;

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
     * @param filePath where the sound file is saved
     */
    public ProximitySound(String filePath){
        if(TestChecker.isJUnitTest())
            return;
        sound = Gdx.audio.newSound(Gdx.files.internal(filePath));
    }
    public Sound getSound(){
        return sound;
    }

    public void play(float pitch, float pan){
        ProximityPlayer.playSound(this, pitch, pan);
    }
}
