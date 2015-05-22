package edu.chl.proximity.Models.ControlPanel.TowerPanel;

import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Towers.ShootingTower;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.*;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Utilities.Constants;

/**
 * @author Hanna Romer
 * @date 2015-05-08
 *
 * 22/05 modified by Linda Evaldsson. Fixed bug that made game crash when trying to upgrade a tower with no upgrade.
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
    private SellButton sell;

    private ProximityFont cost=new ProximityFont(new ProximityVector(pos.x+5, pos.y+75),"Upgrade");
    private ProximityFont points=new ProximityFont(new ProximityVector(pos.x+60, pos.y+95),"Points:");
    private ProximityFont lines=new ProximityFont(new ProximityVector(pos.x+60, pos.y+110),"Lines:");
    private ProximityFont polygons=new ProximityFont(new ProximityVector(pos.x+60, pos.y+125),"Polygons:");

    private boolean afford;

    public TowerPanel(Map map){
        super(pos,background,0,width,height);
        this.map=map;
        first=new CheckBox(new ProximityVector(pos.x+180,pos.y+30), map, "Target first");
        closest=new CheckBox(new ProximityVector(pos.x+180,pos.y+60), map, "Target closest");
        last=new CheckBox(new ProximityVector(pos.x+180,pos.y+90), map, "Target last");
        upgrade=new UpgradeButton(new ProximityVector(pos.x+5, pos.y+95));
        sell=new SellButton(new ProximityVector(pos.x+150,pos.y+115),map);
        targetingFactory=new TargetingFactory();
        setInfo();
    }
    public BoardObject getButtonOnPosition(ProximityVector pos){
        if(first.containsPoint(pos)){
            pressedFirst();
            return first;
        }else if(closest.containsPoint(pos)){
            pressedClosest();
            return closest;
        }else if(last.containsPoint(pos)){
            pressedLast();
            return last;
        }else if(upgrade.containsPoint(pos)){
            pressedUpgrade();
        }else if(sell.containsPoint(pos)){
            pressedSell();
        }
        return null;
    }

    public void pressedFirst(){
        if(map.getChosenTower() instanceof ShootingTower){
            ((ShootingTower) map.getChosenTower()).setTargetingMethod(targetingFactory.getTargetFirst());
            first.setAsChecked();
            closest.setAsUnchecked();
            last.setAsUnchecked();
        }
    }

    public void pressedClosest(){
        if(map.getChosenTower() instanceof ShootingTower){
            ((ShootingTower) map.getChosenTower()).setTargetingMethod(targetingFactory.getTargetClosest());
            first.setAsUnchecked();
            closest.setAsChecked();
            last.setAsUnchecked();
        }
    }

    public void pressedLast(){
        if(map.getChosenTower() instanceof ShootingTower){
            ((ShootingTower) map.getChosenTower()).setTargetingMethod(targetingFactory.getTargetLast());
            first.setAsUnchecked();
            closest.setAsUnchecked();
            last.setAsChecked();
        }
    }

    public void pressedUpgrade(){
        System.out.println("Pressed upgrade");
        if(map.getChosenTower() != null && afford) {
            Tower upgrade=map.getChosenTower().getUpgrade();
            if(upgrade != null) {
                upgrade.setPosition(map.getChosenTower().getPosition());
                upgrade.setAsPlaced(true);
                if (upgrade instanceof ShootingTower && map.getChosenTower() instanceof ShootingTower) {
                    ((ShootingTower) upgrade).setTargetingMethod(((ShootingTower) map.getChosenTower()).getTargetingMethod());
                }
                map.getChosenTower().remove();
                map.add(upgrade);
                map.setChosenTower(upgrade);
            }
        }
    }

    public void pressedSell(){
        Double p=new Double(map.getChosenTower().getCost().getPoints()/2);
        Double l=new Double(map.getChosenTower().getCost().getLines()/2);
        Double poly=new Double(map.getChosenTower().getCost().getPolygons()/2);
        GameData.getInstance().getPlayer().getResources().addResources(new Resources(p.intValue(),l.intValue(),poly.intValue()));
        map.getChosenTower().remove();
        map.setChosenTower(null);
    }

    public void setInfo(){
        if(map.getChosenTower() != null){
            Tower chosenTower = map.getChosenTower();
            towerName.setText(chosenTower.getName());
            towerImage=chosenTower.getImage();
            if(map.getChosenTower().getUpgrade() != null) {
                upgrade.setImage(map.getChosenTower().getUpgrade().getImage());
                Resources r = chosenTower.getUpgradeCost();

                Resources p = GameData.getInstance().getPlayer().getResources();
                if (r.getPolygons() > p.getPolygons() || r.getLines() > p.getLines() || r.getPoints() > p.getPoints()) {
                    afford = false;
                    cost.setText("Upgrade (Can't afford)");
                } else {
                    afford = true;
                    cost.setText("Upgrade");
                }
                points.setText("Points: " + r.getPoints());
                lines.setText("Lines: " + r.getLines());
                polygons.setText("Polygons: " + r.getPolygons());

            }
            if(map.getChosenTower() instanceof ShootingTower){
                if(((ShootingTower) map.getChosenTower()).getTargetingMethod() instanceof TargetFirst){
                    pressedFirst();
                }else if(((ShootingTower) map.getChosenTower()).getTargetingMethod() instanceof TargetClosest){
                    pressedClosest();
                }else if(((ShootingTower) map.getChosenTower()).getTargetingMethod() instanceof TargetLast){
                    pressedLast();
                }
            }
        }
    }

    public void render(ProximityBatch batch){
        if(map.getChosenTower() != null) {
            setInfo();
            batch.renderRepeatedly(background, pos, width, height);
            super.render(batch);
            first.render(batch);
            closest.render(batch);
            last.render(batch);
            towerName.draw(batch);
            batch.render(towerImage, new ProximityVector(pos.x + 5, pos.y + 5), 0);
            sell.render(batch);
            if(map.getChosenTower().getUpgrade() != null) {
                upgrade.render(batch);
                cost.draw(batch);
                points.draw(batch);
                lines.draw(batch);
                polygons.draw(batch);
            }
        }
    }
}
