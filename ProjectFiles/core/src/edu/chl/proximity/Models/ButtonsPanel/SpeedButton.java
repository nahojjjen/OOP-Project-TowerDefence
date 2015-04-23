package edu.chl.proximity.Models.ButtonsPanel;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna R�mer
 * @date 2015-04-23
 * Represents the visuals for a speed button
 */
public class SpeedButton extends BoardObject{
    private static Image twoArrows=new Image(Constants.filePath + "Buttons/FastPlayButton.png");
    private static Image threeArrows=new Image(Constants.filePath + "Buttons/FastPlayButton2.png");
    private static Image oneArrow=new Image(Constants.filePath + "Buttons/PlayButton.png");
    private static int height=50;
    private static int width=50;

    /**
     * Create a new speed button
     * @param position What position the button shall have
     */
    public SpeedButton(Vector2 position){
        super(position,twoArrows,0,width,height);
    }

    /**
     * Toggles the image on the button between 1xSpeed, 2xSpeed and 3xSpeed-images
     */
    public void toggle(){
        if(super.getImage().equals(twoArrows)){
            super.setImage(threeArrows);
        }else if(super.getImage().equals(threeArrows)){
            super.setImage(oneArrow);
        }else{
            super.setImage(twoArrows);
        }
    }
}
