package edu.chl.proximity.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.ControlPanel.ControlPanel;
import edu.chl.proximity.Models.ControlPanel.ControlPanelTower;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Towers.BulletTower;
import edu.chl.proximity.Models.Towers.MissileTower;
import edu.chl.proximity.Models.Towers.SlowTower;
import edu.chl.proximity.Utilities.PointCalculations;

/**
 * Created by Linda on 2015-04-09.
 */
public class BoardInputProcessor implements InputProcessor {

    Viewport viewport;
    ControlPanel controlPanel;
    private int tempCounter=0;

    public BoardInputProcessor(Viewport v) {
        viewport = v;
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }
    @Override
    public boolean keyDown (int keycode) {
        return false;
    }

    @Override
    public boolean keyUp (int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped (char character) {
        return false;
    }

    @Override
    public boolean touchDown (int x, int y, int pointer, int button) {


        //Calculates the real coordinates from the scaled coordinates
        Vector2 tmp = viewport.unproject(new Vector2(x, y));
        //System.out.println("Mouse x: " + (int)tmp.x + " Mouse y: " + (int)tmp.y);
        PointCalculations.createPathTool((int) tmp.x, (int) tmp.y);


        //Todo: Move this handling to separate class
        //Checks if the Control Panel is clicked
        if(tmp.x > Gdx.graphics.getWidth() - controlPanel.getWidth()){
            System.out.println("BoardInputProcessor: ControlPanel is clicked");

            //Checks if a tower on the ControlPanel is clicked
            ControlPanelTower cpTower = controlPanel.getTowerOnPosition(tmp);
            if(cpTower != null) {
                System.out.println("BoardInputProcessor: Clicked on Tower");
                cpTower.getTower();
            }

        }

        tempCounter++;
        if (tempCounter % 3== 0){
            GameData.getInstance().getMap().addTower(new SlowTower(tmp));
        }else if(tempCounter%3 ==1){

            GameData.getInstance().getMap().addTower(new MissileTower(tmp));
        }else{
            GameData.getInstance().getMap().addTower(new BulletTower(tmp));
        }

        return true;
    }

    @Override
    public boolean touchUp (int x, int y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged (int x, int y, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved (int x, int y) {
       //ystem.out.println("Test");
        return true;
    }

    @Override
    public boolean scrolled (int amount) {
        return false;
    }
}