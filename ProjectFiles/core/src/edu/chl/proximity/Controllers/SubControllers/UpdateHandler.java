package edu.chl.proximity.Controllers.SubControllers;

/**
 * @author Linda Evaldsson
 * @date 2015-05-24
 *
 * An interface that can be implemented by controllers that needs to update things
 * 60 times per second.
 */
public interface UpdateHandler {

    void update();
}
