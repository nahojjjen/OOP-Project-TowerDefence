package edu.chl.proximity.Controllers.ScreenChanger;


import edu.chl.proximity.Models.Utils.DisposableCollector;

/**
 * @author Linda Evaldsson
 * @date 2015-05-23
 *
 * A class that the SubController can access to change the screen. The
 * GameScreens listen to this class and gets informed if the screen should be changed.
 */
public class ScreenChanger {

    private static ScreenChangerListener currentListener;
    private static ScreenType newScreen;

    public enum ScreenType {
        MainMenu, Play, GameOver
    }

    /**
     * This method is called by subcontrollers when they want to change the screen
     * @param screen The new screen that should be changed to
     */
    public static void changeScreen(ScreenType screen) {
        newScreen = screen;
        DisposableCollector.disposeAll();
        notifyListener();
    }

    /**
     * Sets the listener currently listening for screen changes.
     * Since there can only be only active screen there can only be one active listener
     * @param listener
     */
    public static void setListener(ScreenChangerListener listener) {
        currentListener = listener;
    }

    /**
     * Notifies the current listener that the screen has been changed
     */
    private static void notifyListener() {
        if(currentListener != null) {
            currentListener.screenChanged(newScreen);
        }
    }

}
