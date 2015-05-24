package edu.chl.proximity.Utilities;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

import static org.mockito.Mockito.mock;


/**
 * GdxTestRunner
 * Credit to: https://bitbucket.org/TomGrill/libgdx-testing-sample
 *
 * Added by Simon Gislen
 * Mocks the OpenGL environment when testing
 */
/* Commencted out since this class is unused
public class GdxTestRunner extends BlockJUnit4ClassRunner implements ApplicationListener{

    private Map<FrameworkMethod, RunNotifier> invokeInRender = new HashMap<FrameworkMethod, RunNotifier>();

    public GdxTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
        final HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        config.renderInterval = 1f/60; // Likely want 1f/60 for 60 fps
        //new HeadlessApplication(new Proximity(), config); //Removed since this class is unused to remove reference to Proximity
        //Gdx.gl = mock(GL20.class); // my improvement //Removed since this class is unused and find bugs doesnt like this.
    }

    @Override
    public void create() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void render() {
        synchronized (invokeInRender) {
            for(Map.Entry<FrameworkMethod, RunNotifier> each : invokeInRender.entrySet()){
                super.runChild(each.getKey(), each.getValue());
            }
            invokeInRender.clear();
        }
    }



    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void dispose() {
    }

    @Override
    protected void runChild(FrameworkMethod method, RunNotifier notifier) {
        synchronized (invokeInRender) {
            //add for invoking in render phase, where gl context is available
            invokeInRender.put(method, notifier);
        }
        //wait until that test was invoked
        waitUntilInvokedInRenderMethod();
    }


    private void waitUntilInvokedInRenderMethod() {
        try {
            while (true){
                Thread.sleep(10);
                synchronized (invokeInRender) {
                    if (invokeInRender.isEmpty()) break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}*/