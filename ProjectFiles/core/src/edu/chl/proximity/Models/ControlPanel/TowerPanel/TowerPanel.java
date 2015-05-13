package edu.chl.proximity.Models.ControlPanel.TowerPanel;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Towers.ShootingTower;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.*;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.Player.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.GameData;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;

/**
 * @author Hanna R�mer
 * @date 2015-05-08
 */
public class TowerPanel extends BoardObject{
    private Map map;
    private static Image background=new Image(Constants.FILE_PATH + "Backgrounds/tweed.png");
    private Image towerImage;
    private static ProximityVector pos=new ProximityVector(651,550);
    private static int width=324;
    private static int height=300;
    private TargetingFactory targetingFactory;
    private ProximityFont towerName=new ProximityFont(new ProximityVector(pos.x+115,pos.y+5),null);

    private CheckBox first;
    private CheckBox closest;
    private CheckBox last;
    private UpgradeButton upgrade;

    private ProximityFont cost=new ProximityFont(new ProximityVector(pos.x+5, pos.y+75),"Upgrade");
    private ProximityFont points=new ProximityFont(new ProximityVector(pos.x+60, pos.y+95),"Points:");
    private ProximityFont lines=new ProximityFont(new ProximityVector(pos.x+60, pos.y+110),"Lines:");
    private ProximityFont polygons=new ProximityFont(new ProximityVector(pos.x+60, pos.y+125),"Polygons:");

    private boolean afford;

    public TowerPanel(Map map){
        super(pos,background,0,width,height);
        this.map=map;
        first=new CheckBox(new ProximityVector(pos.x+160,pos.y+30), map, "Target first");
        closest=new CheckBox(new ProximityVector(pos.x+160,pos.y+70), map, "Target closest");
        last=new CheckBox(new ProximityVector(pos.x+160,pos.y+110), map, "Target last");
        upgrade=new UpgradeButton(new ProximityVector(pos.x+5, pos.y+95));
        targetingFactory=new TargetingFactory(map);
        setInfo();
    }
    public BoardObject getButtonOnPosition(ProximityVector pos){
        if(PointCalculations.isPointInObject(pos,first)){
            pressedFirst();
            return first;
        }else if(PointCalculations.isPointInObject(pos, closest)){
            pressedClosest();
            return closest;
        }else if(PointCalculations.isPointInObject(pos, last)){
            pressedLast();
            return last;
        }else if(PointCalculations.isPointInObject(pos, upgrade)){
            pressedUpgrade();
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

    public void pressedUpgrade(){
        if(map.getChoosenTower() != null && afford) {
            Tower upgrade=map.getChoosenTower().getUpgrade();
            upgrade.setPosition(map.getChoosenTower().getPosition());
            upgrade.setAsPlaced(true);
            if(upgrade instanceof ShootingTower && map.getChoosenTower() instanceof ShootingTower){
                ((ShootingTower) upgrade).setTargetingMethod(((ShootingTower) map.getChoosenTower()).getTargetingMethod());
            }
            map.getChoosenTower().remove();
            map.add(upgrade);
            map.setChoosenTower(upgrade);
        }
    }
    public void setInfo(){
        if(map.getChoosenTower() != null){
            Tower chosenTower = map.getChoosenTower();
            towerName.setText(chosenTower.getName());
            towerImage=chosenTower.getImage();
            upgrade.setImage(map.getChoosenTower().getUpgrade().getImage());
            Resources r=chosenTower.getUpgradeCost();

            Resources p= GameData.getInstance().getPlayer().getResources();
            if(r.getPolygons()>p.getPolygons() || r.getLines()>p.getLines() || r.getPoints()>p.getPoints()){
                afford=false;
                cost.setText("Upgrade (Can't afford)");
            }else{
                afford=true;
                cost.setText("Upgrade");
            }
            points.setText("Points: " + r.getPoints());
            lines.setText("Lines: " + r.getLines());
            polygons.setText("Polygons: " + r.getPolygons());

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
            towerImage.render(batch, new ProximityVector(pos.x + 5, pos.y + 5), 0);
            upgrade.render(batch);
            cost.draw(batch);
            points.draw(batch);
            lines.draw(batch);
            polygons.draw(batch);
        }
    }
}
