package edu.chl.proximity.Models.ControlPanel.TowerPanel;

import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.ControlPanel.ResourceDisplayerCollection;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.MouseOver.MouseOverBox;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetingFactory;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetingMethod;
import edu.chl.proximity.Models.Map.Towers.TargetingTower;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    private static Image background=new Image(Constants.FILE_PATH + "Towers/squareLight.png");
    private Image towerImage;
    private static ProximityVector pos=new ProximityVector(Constants.GAME_WIDTH - 300, 490);
    private static int width=300;
    private static int height=150;
    private TargetingFactory targetingFactory;
    private ProximityFont towerName;
    private ProximityVector towerPosition;

    private MouseOverBox upgradeHoverBox;
    private MouseOverBox sellHoverBox = new MouseOverBox(150, "Click to destroy");


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
        towerName = new ProximityFont(new ProximityVector(pos.x + 30, pos.y + 5),null,16, 1,1,1);
        towerPosition = new ProximityVector(pos.x + 30, pos.y + 30);
    }

    private void initiateUpgrade() {

        upgrade = new UpgradeButton(new ProximityVector(pos.x + 150, pos.y + 40));
        upgradeText = new ProximityFont(new ProximityVector(pos.x + 150, pos.y+25),"Upgrade",12, 1,1,1);

        upgradeCost = new ResourceDisplayerCollection(new ProximityVector(pos.x + 200, pos.y + 30), 15, ResourceDisplayerCollection.Direction.Vertical);

    }
    private void initiateCheckButtons() {
        checkBoxMap.put(new CheckBox(new ProximityVector(pos.x+30, pos.y + 80), map, "Target first"), targetingFactory.getTargetFirst());
        checkBoxMap.put(new CheckBox(new ProximityVector(pos.x+30, pos.y + 105), map, "Target closest"), targetingFactory.getTargetClosest());
        checkBoxMap.put(new CheckBox(new ProximityVector(pos.x+30, pos.y + 130), map, "Target last"), targetingFactory.getTargetLast());

    }

    /**
     * Method only for testing. Returns center position of the upgrade button.
     * @return the center position of the upgrade button
     */
    public ProximityVector getUpgradeCenter() {
        return upgrade.getCenter();
    }

    /**
     * Method only for testing. Returns the center position of the sell button.
     * @return the center position of the sell button
     */
    public ProximityVector getSellCenter() {
        return sell.getCenter();
    }

    /**
     * Method only for testing. Returns the center positions of all the checkboxes.
     * @return the center positions of all the checkboxes.
     */
    public List<ProximityVector> getCheckBoxCenters() {
        List list = new ArrayList();
        for (HashMap.Entry<CheckBox, TargetingMethod> entry : checkBoxMap.entrySet()) {
            list.add(entry.getKey().getCenter());
        }
        return list;
    }

    private void initiateSell() {
        sell=new SellButton(new ProximityVector(pos.x+150,pos.y+115),map);
    }


    public BoardObject pressedPosition(ProximityVector pos){

        if(upgrade.containsPoint(pos)){
            pressedUpgrade();
        }else if(sell.containsPoint(pos)){
            pressedSell();
        }
        for (HashMap.Entry<CheckBox, TargetingMethod> entry : checkBoxMap.entrySet()) {
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

    /**
     * Returns whether the TowerPanel contains the point and is visible
     * @param point The point that is to be checked
     * @return Whether the point is within the TowerPanel and the panel is visible
     */
    public boolean containsPoint(ProximityVector point) {
        return map.getChosenTower() != null && super.containsPoint(point);
    }

    private void pressedUpgrade(){
        Tower tower = map.getChosenTower();
        if(tower != null && afford) {
            Tower upgrade = tower.getUpgrade();
            if(upgrade != null) {
                GameData.getInstance().getPlayer().getResources().removeResources(upgrade.getCost());
                if (upgrade instanceof TargetingTower && tower instanceof TargetingTower) {
                    ((TargetingTower) upgrade).setTargetingMethod(((TargetingTower) tower).getTargetingMethod());
                }

                upgrade.setPosition(tower.getPosition());
                upgrade.setAsPlaced(true);

                tower.remove();
                map.add(upgrade);
                map.setChosenTower(upgrade);
            }
        }
    }

    private void pressedSell(){
        map.getChosenTower().remove();
        map.setChosenTower(null);
    }

    public String getTowerName() {
        return towerName.getText();
    }

    public void setInfo(){
        Tower chosenTower = map.getChosenTower();
        if(chosenTower != null){
            towerName.setText(chosenTower.getName());
            towerImage=chosenTower.getImage();
            if(map.getChosenTower().getUpgrade() != null) {
                upgrade.setImage(chosenTower.getUpgrade().getImage());
                upgradeHoverBox = new MouseOverBox(150, chosenTower.getUpgrade().getHelpInfo());
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

    /**
     * A method that displays the correct HoverBox if the mouse is over the upgrade-button
     * @param position
     */
    public void mouseOverPosition(ProximityVector position) {
        if(map.getChosenTower().getUpgrade()!=null) {
            if (upgrade.containsPoint(position)) {
                upgradeHoverBox.enable();
            } else if (sell.containsPoint(position)) {
                sellHoverBox.enable();
            }
        }
    }

    public void render(ProximityBatch batch){
        if(map.getChosenTower() != null) {
            setInfo();
            batch.renderRepeatedly(background, pos, width, height);

            if(map.getChosenTower() instanceof TargetingTower) {
                for (HashMap.Entry<CheckBox, TargetingMethod> entry : checkBoxMap.entrySet()) {
                    entry.getKey().render(batch);
                }
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
