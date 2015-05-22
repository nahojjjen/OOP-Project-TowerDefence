package edu.chl.proximity.Models.ControlPanel.TowerPanel;

import edu.chl.proximity.Models.ControlPanel.ResourceDisplayerCollection;
import edu.chl.proximity.Models.Map.Towers.TargetingTower;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.*;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Utilities.Constants;

import java.util.HashMap;

/**
 * @author Hanna Romer
 * @date 2015-05-08
 *
 * A panel that handles changing settings and upgradings of towers
 *
 * 22/05 modified by Linda Evaldsson. Fixed bugs. Also redesigned Panel and moved it to ControlPanel (graphically).
 */
public class TowerPanel extends BoardObject{
    private Map map;
    private static Image background=new Image(Constants.FILE_PATH + "Backgrounds/tweed.png");
    private Image towerImage;
    private static ProximityVector pos=new ProximityVector(Constants.GAME_WIDTH - 300,450);
    private static int width=300;
    private static int height=150;
    private TargetingFactory targetingFactory;
    private ProximityFont towerName;
    private ProximityVector towerPosition;


    private HashMap<CheckBox, TargetingMethod> checkBoxMap = new HashMap<CheckBox, TargetingMethod>();

    private UpgradeButton upgrade;
    private SellButton sell;

    private ProximityFont upgradeText;
    private ResourceDisplayerCollection upgradeCost;

    private boolean afford;

    public TowerPanel(Map map){
        super(pos,background,0,width,height);
        this.map=map;
        targetingFactory=new TargetingFactory();
        initiateCheckButtons();
        initiateUpgrade();
        initiateTower();
        initiateSell();


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
        checkBoxMap.put(new CheckBox(new ProximityVector(pos.x+30, pos.y + 80), map, "Target first"), targetingFactory.getTargetFirst());
        checkBoxMap.put(new CheckBox(new ProximityVector(pos.x+30, pos.y + 105), map, "Target closest"), targetingFactory.getTargetClosest());
        checkBoxMap.put(new CheckBox(new ProximityVector(pos.x+30, pos.y + 130), map, "Target last"), targetingFactory.getTargetLast());

    }
    private void initiateSell() {
        sell=new SellButton(new ProximityVector(pos.x+150,pos.y+115),map);
    }
    public BoardObject getButtonOnPosition(ProximityVector pos){

        if(upgrade.containsPoint(pos)){
            pressedUpgrade();
        }else if(sell.containsPoint(pos)){
            pressedSell();
        }
        for (HashMap.Entry<CheckBox, TargetingMethod> entry : checkBoxMap.entrySet())
        {
            if(entry.getKey().containsPoint(pos)) {
                if(map.getChosenTower() instanceof TargetingTower) {
                    ((TargetingTower) map.getChosenTower()).setTargetingMethod(entry.getValue());
                    uncheckAll();
                    entry.getKey().setAsChecked();
                }
            }
        }
        return null;
    }

    private void uncheckAll() {
        for (HashMap.Entry<CheckBox, TargetingMethod> entry : checkBoxMap.entrySet())
        {
            entry.getKey().setAsUnchecked();
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
                uncheckAll();

                for (HashMap.Entry<CheckBox, TargetingMethod> entry : checkBoxMap.entrySet())
                {
                    if(entry.getValue().getClass() == chosenTargetingTower.getTargetingMethod().getClass()) {
                        entry.getKey().setAsChecked();
                    }
                }
            }
        }
    }

    public void render(ProximityBatch batch){
        if(map.getChosenTower() != null) {
            setInfo();
            batch.renderRepeatedly(background, pos, width, height);

            for (HashMap.Entry<CheckBox, TargetingMethod> entry : checkBoxMap.entrySet())
            {
                entry.getKey().render(batch);
            }
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
