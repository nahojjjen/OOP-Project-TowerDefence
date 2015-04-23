package edu.chl.proximity.Models.ButtonsPanel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;

/**
 * Created by Hanna on 2015-04-22.
 */
public class ButtonPanel extends BoardObject {
    private static Image background = null;
    private static int width=300;
    private static int height=70;
    private static Vector2 position= new Vector2(Gdx.graphics.getWidth()-width, Gdx.graphics.getHeight()-height);
    private boolean pause=false;
    private int speed=1;
    private PlayPauseButton ppButton=new PlayPauseButton(position.add(0,0));
    private SpeedButton speedButton=new SpeedButton(position.add(50,0));

    public ButtonPanel() {
        super(position, background, 0, width, height);
        GameData.getInstance().setGameSpeed(speed);
    }

    public boolean isPaused(){
        return pause;
    }

    public BoardObject getButtonOnPosition(Vector2 position){
        if(PointCalculations.isPointInObject(position, ppButton)){
            return ppButton;
        }else if(PointCalculations.isPointInObject(position, speedButton)){
            return speedButton;
        }
        return null;
    }

    public void pressedPausePlay(){
        if(pause){
            pause=false;
            ppButton.toggle();
            GameData.getInstance().setGameSpeed(speed);
        }else{
            pause=true;
            ppButton.toggle();
            GameData.getInstance().setGameSpeed(0);
        }
    }

    public void pressedSpeedButton(){
        if(speed==3){
            speed=1;
        }else{
            speed++;
        }
        GameData.getInstance().setGameSpeed(speed);
        speedButton.toggle();
    }

    public void pressedProperiesButton(){
        System.out.print("Properties were pressed");
    }

    public void render(SpriteBatch batch){
        super.render(batch);
        ppButton.render(batch);
        speedButton.render(batch);
    }

}
