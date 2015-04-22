package edu.chl.proximity.Controllers.SubControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Controllers.ClickHandler;
import edu.chl.proximity.Models.Backgrounds.Background;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.GameData;
import edu.chl.proximity.Models.Towers.BulletTower;
import edu.chl.proximity.Models.Towers.MissileTower;
import edu.chl.proximity.Models.Towers.SlowTower;


/**
 * @author Linda Evaldsson
 * @date 2015-04-22
 */
public class MapController implements ClickHandler {

    private Background model = new Background(null);
    private int tempCounter = 0;

    public MapController() {
        model.setPosition(new Vector2(0,0));
        model.setWidth(Gdx.graphics.getWidth()-300);
        model.setHeight(Gdx.graphics.getHeight());
    }

    @Override
    public void touchDown(Vector2 clickedPoint, int pointer, int button) {
        tempCounter++;
        if (tempCounter % 3== 0){
            GameData.getInstance().getMap().addTower(new SlowTower(clickedPoint));
        }else if(tempCounter%3 ==1){

            GameData.getInstance().getMap().addTower(new MissileTower(clickedPoint));
        }else{
            GameData.getInstance().getMap().addTower(new BulletTower(clickedPoint));
        }

    }

    @Override
    public BoardObject getModel() {
        return model;
    }
}
