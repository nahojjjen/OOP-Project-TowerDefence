package edu.chl.fohProximity;

import org.newdawn.slick.*;

import java.util.logging.Level;
import java.util.logging.Logger;
/*
  Application entry class (if using standard java and Swing)
*/
public class Main extends BasicGame
{
    public Main(String gamename)
    {
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {}

    @Override
    public void update(GameContainer gc, int i) throws SlickException {}

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        g.drawString("Howdy!", 100, 100);

    }

    public static void main(String[] args)
    {
        try
        {
            System.setProperty("org.lwjgl.opengl.Display.enableHighDPI", "true");
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Main("Simple Slick Game"));
            appgc.setDisplayMode(640, 480, false);
            appgc.setTargetFrameRate(60);
            appgc.setVSync(true);
            appgc.start();

        }
        catch (SlickException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}