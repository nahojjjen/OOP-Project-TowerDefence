package edu.chl.proximity.Models.ControlPanel.TowerPanel;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Towers.ShootingTower;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.*;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;

/**
 * @author Hanna Römer
 * @date 2015-05-08
 */
public class TowerPanel extends BoardObject{
    private Map map;
    private static Image background=new Image(Constants.FILE_PATH + "Backgrounds/tweed.png");
    private static Vector2 pos=new Vector2(651,550);
    private static int width=324;
    private static int height=300;
    private TargetingFactory targetingFactory;
    private ProximityFont towerName=new ProximityFont(new Vector2(pos.x+5,pos.y+5),null);
    private CheckBox first;
    private CheckBox closest;
    private CheckBox last;

    public TowerPanel(Map map){
        super(map, pos,background,0,width,height);
        this.map=map;
        first=new CheckBox(new Vector2(pos.x+10,pos.y+30), map, "Target first");
        closest=new CheckBox(new Vector2(pos.x+10,pos.y+70), map, "Target closest");
        last=new CheckBox(new Vector2(pos.x+10,pos.y+110), map, "Target last");
        targetingFactory=new TargetingFactory(map);
        setInfo();
    }
    public BoardObject getButtonOnPosition(Vector2 pos){
        if(PointCalculations.isPointInObject(pos,first)){
            pressedFirst();
            return first;
        }else if(PointCalculations.isPointInObject(pos, closest)){
            pressedClosest();
            return closest;
        }else if(PointCalculations.isPointInObject(pos, last)){
            pressedLast();
            return last;
        }
        return null;
    }

    public void pressedFirst(){
        if(map.getChoosenTower() instanceof ShootingTower){
            ((ShootingTower) map.getChoosenTower()).setTargetingMethod(targetingFactory.getTargetFirst());
            first.setAsChecked();
            closest.setAsUnchecked();
            last.setAsUnchecked();
        }
    }

    public void pressedClosest(){
        if(map.getChoosenTower() instanceof ShootingTower){
            ((ShootingTower) map.getChoosenTower()).setTargetingMethod(targetingFactory.getTargetClosest());
            first.setAsUnchecked();
            closest.setAsChecked();
            last.setAsUnchecked();
        }
    }

    public void pressedLast(){
        if(map.getChoosenTower() instanceof ShootingTower){
            ((ShootingTower) map.getChoosenTower()).setTargetingMethod(targetingFactory.getTargetLast());
            first.setAsUnchecked();
            closest.setAsUnchecked();
            last.setAsChecked();
        }
    }

    public void setInfo(){
        if(map.getChoosenTower() != null){
            towerName.setText(map.getChoosenTower().getName());
            if(map.getChoosenTower() instanceof ShootingTower){
                if(((ShootingTower) map.getChoosenTower()).getTargetingMethod() instanceof TargetFirst){
                    pressedFirst();
                }else if(((ShootingTower) map.getChoosenTower()).getTargetingMethod() instanceof TargetClosest){
                    pressedClosest();
                }else if(((ShootingTower) map.getChoosenTower()).getTargetingMethod() instanceof TargetLast){
                    pressedLast();
                }
            }
        }
    }

    public void render(SpriteBatch batch){
        if(map.getChoosenTower() != null) {
            setInfo();
            background.getTexture().setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
            batch.draw(background.getTexture(), pos.x, pos.y, background.getTexture().getWidth(), background.getTexture().getHeight(), width, height);
            super.render(batch);
            first.render(batch);
            closest.render(batch);
            last.render(batch);
            towerName.draw(batch);
        }
    }
}
