package edu.chl.proximity.Models.ButtonsPanel;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna R�mer
 * @date 2015-04-23
 * Represents the visuals for a speed button
 *
 * 29/04 modified Hanna. Button keeps track of what image it should have
 */
public class SpeedButton extends BoardObject{
    private static Image upImage=new Image(Constants.filePath + "Buttons/FastPlayButton.png");
    private static Image downImage=new Image(Constants.filePath + "Buttons/FastPlayButton2.png");
    private static Image oneArrow=new Image(Constants.filePath + "Buttons/PlayButton.png");
    private static int height=50;
    private static int width=50;

    /**
     * Create a new speed button
     * @param position What position the button shall have
     */
    public SpeedButton(Vector2 position){
        super(position,upImage,0,width,height);
    }

    /**
     * Sets image depending on what the current game-speed is
     */
    public void setRightImage(){
        if(GameData.getInstance().getGameSpeed()==1){
            super.setImage(upImage);
        }else if(GameData.getInstance().getGameSpeed() >1){
            super.setImage(downImage);
        }
    }

    /**
     * Renders the button with what image it should currently have
     * @param batch what batch to render the SpeedButton
     */
    public void render(SpriteBatch batch){
        setRightImage();
        super.render(batch);
    }
}
