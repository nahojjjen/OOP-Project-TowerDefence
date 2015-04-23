package edu.chl.proximity.Controllers.SubControllers;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Controllers.ClickHandler;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.PopertiesPanel.PropertiesPanel;

/**
 * @author Hanna Römer
 * @date 2015-04-23
 */
public class PropertiesPanelController implements ClickHandler{
    private PropertiesPanel propertiesPanel=new PropertiesPanel();

    public BoardObject getModel(){
        return propertiesPanel;
    }

    public void setPropertiesPanel(PropertiesPanel propertiesPanel){
        this.propertiesPanel=propertiesPanel;
    }

    public void mouseMoved(Vector2 pos){

    }

    public void touchDown(Vector2 clickedPoint, int pointer, int button){
        if(propertiesPanel.getIfVisible()){

        }
    }

    public void show(){ propertiesPanel.setVisability(true);}

    public void hide(){ propertiesPanel.setVisability(false);}
}
