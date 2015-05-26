package edu.chl.proximity.Controllers.ScreenChanger;

/**
 * @author Linda Evaldsson
 * @date 2015-05-23
 *
 * An interface that needs to be implemented by the screens that want to listen to
 * the ScreenChanger and get updated when the screen should be changed.
 */
public interface ScreenChangerListener {

    void screenChanged(ScreenChanger.ScreenType newScreen);
}
