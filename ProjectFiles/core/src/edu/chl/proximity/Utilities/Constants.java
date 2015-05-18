package edu.chl.proximity.Utilities;

import com.badlogic.gdx.Gdx;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Johan Swanberg and Linda Evaldsson
 * @date 2015-04-02
 *
 * 07/05 modified by Linda Evaldsson. Added GAME_HEIGHT and GAME_WIDTH.
 */
public class Constants {

    public static final String FILE_PATH = "core/assets/";
    public static final int GAME_HEIGHT = Gdx.graphics.getHeight();
    public static final int GAME_WIDTH = Gdx.graphics.getWidth();
    public static final List<String> SPELL_SHORTS = Collections.unmodifiableList(Arrays.asList("Q", "W", "E", "R"));
}
