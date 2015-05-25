package edu.chl.proximity.Controllers.ScreenChanger;


import edu.chl.proximity.Models.Utils.DisposableCollector;

/**
 * @author Linda Evaldsson
 * @date 2015-05-23
 */
public class ScreenChanger {

    private static ScreenChangerListener currentListener;
    private static ScreenType newScreen;

    public enum ScreenType {
        MainMenu, Play, GameOver
    }

    public static void changeScreen(ScreenType screen) {
        newScreen = screen;
        DisposableCollector.disposeAll();
        notifyListeners();
    }

    public static void setListener(ScreenChangerListener listener) {
        currentListener = listener;
    }
    public static void removeListener() {
        currentListener = null;
    }

    private static void notifyListeners() {
        if(currentListener != null) {
            currentListener.screenChanged(newScreen);
        }
    }

    public static ScreenType getNewScreen () {
        return newScreen;
    }

}
