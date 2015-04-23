package edu.chl.proximity.Models.PopertiesPanel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Image;
import edu.chl.proximity.Utilities.Constants;

import javax.sound.sampled.*;

/**
 * @author Hanna Römer
 * @date 2015-04-23
 */
public class PropertiesPanel extends BoardObject{
    private static Image background = new Image(Constants.filePath + "Backgrounds/temporaryControlPanelBackground.png");
    private static int height= Gdx.graphics.getHeight()-400;
    private static int width = 300;
    private static Vector2 position=new Vector2(200,200);
    private boolean soundOff=false;

    public PropertiesPanel(){
        super(position,background,0,height,width);
    }


    public void pressedSoundButton(){
        if(soundOff){
            soundOff=true;
        }else{
            soundOff=false;
        }
        setSound();
    }


    public void setSound(){
        Mixer.Info[] infos = AudioSystem.getMixerInfo();
        for (Mixer.Info info: infos) {
            Mixer mixer = AudioSystem.getMixer(info);
            for(Line line: mixer.getSourceLines()){
                BooleanControl bc = (BooleanControl) line.getControl(BooleanControl.Type.MUTE);
                if (bc != null) {
                    bc.setValue(soundOff); // true to mute the line, false to unmute
                }
            }
        }
    }


}
