package edu.chl.proximity.Models.ControlPanel.TowerPanel;

import edu.chl.proximity.Models.ControlPanel.ResourceDisplayerCollection;
import edu.chl.proximity.Models.Map.Towers.TargetingTower;
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
 * 22/05 modified by Linda Evaldsson. Fixed bugs. Also redesigned Panel and moved it to ControlPanel (graphically).
 */
public class TowerPanel extends BoardObject{
    private Map map;
    private static Image background=new Image(Constants.FILE_PATH + "Backgrounds/tweed.png");
    private Image towerImage;
    private static ProximityVector pos=new ProximityVector(Constants.GAME_WIDTH - 300,450); //651, 550
    private static int width=300;
    private static int height=150;
    private TargetingFactory targetingFactory;
    private ProximityFont towerName;
    private ProximityVector towerPosition;

    private CheckBox first;
    private CheckBox closest;
    private CheckBox last;
    private UpgradeButton upgrade;
    private SellButton sell;

    private ProximityFont upgradeText;
    private ProximityFont points;
    private ProximityFont lines;
    private ProximityFont polygons;
    private ResourceDisplayerCollection upgradeCost;

    private boolean afford;

    public TowerPanel(Map map){
        super(pos,background,0,width,height);
        this.map=map;
        initiateCheckButtons();
        initiateUpgrade();
        initiateTower();
        initiateSell();
        targetingFactory=new TargetingFactory();


        setInfo();
    }

    private void initiateTower() {
        towerName = new ProximityFont(new ProximityVector(pos.x + 30, pos.y + 5),null);
        towerName.setSize(16);
        towerPosition = new ProximityVector(pos.x + 30, pos.y + 30);
    }

    private void initiateUpgrade() {

        upgrade = new UpgradeButton(new ProximityVector(pos.x + 150, pos.y + 30));
        upgradeText = new ProximityFont(new ProximityVector(pos.x + 150, pos.y+15),"Upgrade");
        upgradeText.setSize(12);

        upgradeCost = new ResourceDisplayerCollection(new ProximityVector(pos.x + 200, pos.y + 30), 15, 12, ResourceDisplayerCollection.Face.Vertical);

    }
    private void initiateCheckButtons() {
        first=new CheckBox(new ProximityVector(pos.x+30,pos.y + 80), map, "Target first");
        closest=new CheckBox(new ProximityVector(pos.x+30,pos.y + 105), map, "Target closest");
        last=new CheckBox(new ProximityVector(pos.x+30,pos.y + 130), map, "Target last");
    }
    private void initiateSell() {
        sell=new SellButton(new ProximityVector(pos.x+150,pos.y+115),map);
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
        if(map.getChosenTower() instanceof TargetingTower){
            ((TargetingTower) map.getChosenTower()).setTargetingMethod(targetingFactory.getTargetFirst());
            first.setAsChecked();
            closest.setAsUnchecked();
            last.setAsUnchecked();
        }
    }

    public void pressedClosest(){
        if(map.getChosenTower() instanceof TargetingTower){
            ((TargetingTower) map.getChosenTower()).setTargetingMethod(targetingFactory.getTargetClosest());
            first.setAsUnchecked();
            closest.setAsChecked();
            last.setAsUnchecked();
        }
    }

    public void pressedLast(){
        if(map.getChosenTower() instanceof TargetingTower){
            ((TargetingTower) map.getChosenTower()).setTargetingMethod(targetingFactory.getTargetLast());
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
                if (upgrade instanceof TargetingTower && map.getChosenTower() instanceof TargetingTower) {
                    ((TargetingTower) upgrade).setTargetingMethod(((TargetingTower) map.getChosenTower()).getTargetingMethod());
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
        Tower chosenTower = map.getChosenTower();
        if(chosenTower != null){
            towerName.setText(chosenTower.getName());
            towerImage=chosenTower.getImage();
            if(map.getChosenTower().getUpgrade() != null) {
                upgrade.setImage(chosenTower.getUpgrade().getImage());
                Resources r = chosenTower.getUpgradeCost();


                Resources p = GameData.getInstance().getPlayer().getResources();
                if (r.getPolygons() > p.getPolygons() || r.getLines() > p.getLines() || r.getPoints() > p.getPoints()) {
                    afford = false;
                    upgradeText.setText("Upgrade (Can't afford)");
                } else {
                    afford = true;
                    upgradeText.setText("Upgrade");
                }
                upgradeCost.updateResources(r);

            }
            if(map.getChosenTower() instanceof TargetingTower){
                TargetingTower chosenTargetingTower = (TargetingTower)chosenTower;
                if(chosenTargetingTower.getTargetingMethod() instanceof TargetFirst){
                    pressedFirst();
                }else if(chosenTargetingTower.getTargetingMethod() instanceof TargetClosest){
                    pressedClosest();
                }else if(chosenTargetingTower.getTargetingMethod() instanceof TargetLast){
                    pressedLast();
                }
            }
        }
    }

    public void render(ProximityBatch batch){
        if(map.getChosenTower() != null) {
            setInfo();
            batch.renderRepeatedly(background, pos, width, height);
            first.render(batch);
            closest.render(batch);
            last.render(batch);
            towerName.draw(batch);
            batch.render(towerImage, towerPosition, 0);
            sell.render(batch);
            if(map.getChosenTower().getUpgrade() != null) {
                upgrade.render(batch);
                upgradeText.draw(batch);
                upgradeCost.render(batch);
            }
        }
    }
}
