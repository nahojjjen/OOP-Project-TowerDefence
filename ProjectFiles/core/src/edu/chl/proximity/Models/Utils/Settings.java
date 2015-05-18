package edu.chl.proximity.Models.Utils;

/**
 * @author Linda Evaldsson
 * @date 2015-05-07
 *
 * This is a class for keeping track of all the settings in the game. Initially used for sound and game speed.
 */
public class Settings {

    private int storedGameSpeed;
    private int gameSpeed;
    private float gameVolume;

    public Settings() {
        gameSpeed = 1;
        gameVolume = 0.1f;
    }

    /**
     * set the speed in which the game logic updates
     * The method does nothing if input speedMultiplier is less than 0.
     * @param speed how fast the game logic should update
     */
    public void setGameSpeed(int speed){
        if(speed >= 0){
            gameSpeed = speed;
        }
    }
    public int getGameSpeed() {
        return gameSpeed;
    }

    public float getGameVolume() {
        return gameVolume;
    }

    public void setGameVolume(float gameVolume) {
        this.gameVolume = gameVolume;
    }

    public void togglePause() {
        if(gameSpeed > 0) {
            storedGameSpeed = gameSpeed;
            setGameSpeed(0);
        } else {
            setGameSpeed(storedGameSpeed);
        }
    }

    public void cloneSettings(Settings settings) {
        this.gameSpeed = settings.getGameSpeed();
        this.gameVolume = settings.getGameVolume();
    }
}
