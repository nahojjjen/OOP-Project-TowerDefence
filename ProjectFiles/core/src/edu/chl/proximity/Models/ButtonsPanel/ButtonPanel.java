package edu.chl.proximity.Models.ButtonsPanel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * Created by Hanna on 2015-04-22.
 */
public class ButtonPanel extends BoardObject {
    private static Image background = new Image(Constants.filePath + "Backgrounds/temporaryControlPanelBackground.png");
    private static int width=300;
    private static int height=200;
    private static Vector2 position= new Vector2(0,0);
    private boolean pause=false;
    private int speed=1;
    private PlayPauseButton ppButton=new PlayPauseButton(position.add(10,10));
    private SpeedButton speedButton=new SpeedButton(position.add(70,10));

    public ButtonPanel() {
        super(position, background, 0, width, height);
        GameData.getInstance().setGameSpeed(speed);
    }

    public boolean isPaused(){
        return pause;
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

    public void speedButtonPressed(){
        if(speed==3){
            speed=1;
        }else{
            speed++;
        }
        GameData.getInstance().setGameSpeed(speed);
    }

    public void propertiesButtonPressed(){
        System.out.print("Properties were pressed");
    }

}
