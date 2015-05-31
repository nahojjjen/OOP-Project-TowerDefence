package edu.chl.proximity.Utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Johan Swanberg and Linda Evaldsson
 * @date 2015-04-02
 *
 * A class for handling constant data that should not change in the game.
 *
 * ---
 * 07/05 modified by Linda Evaldsson. Added GAME_HEIGHT and GAME_WIDTH.
 */
public class Constants {

    public static final String FILE_PATH = "assets/";
    public static final int GAME_HEIGHT = 720; //Gdx.graphics.getHeight()
    public static final int GAME_WIDTH = 1280; //Gdx.graphics.getWidth();
    public static final List<String> SPELL_SHORTS = Collections.unmodifiableList(Arrays.asList("Q", "W", "E", "R"));

    public static FileHandle getFile(String s){
        if (TestChecker.isJUnitTest()) {
            return null;
        }
        return Gdx.files.internal(s);
    }
}
