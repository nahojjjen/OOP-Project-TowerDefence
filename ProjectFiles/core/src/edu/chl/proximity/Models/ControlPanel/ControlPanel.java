package edu.chl.proximity.Models.ControlPanel;

import edu.chl.proximity.Models.Map.Towers.*;
import edu.chl.proximity.Models.Utils.PercentBar;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Utilities.*;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetingFactory;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.ResourceSystem.Resources;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Linda Evaldsson
 * @date 2015-04-17
 *
 * The class manages the information to the right of the screen
 *
 * Unknown date modified by Johan Swanberg
 * 08/05 modified by Linda Evaldsson. Changed percentBar to use textures (images). Moved some rendering to Image class.
 * 18/05 modified by Simon to add a sniper tower
 * 21/05 modified by Linda Evaldsson. Changed how the Panel looks and how resources are displayed. They are now displayed with a ResourceDisplayerCollection.
 * 22/05 modified by Linda Evaldsson. Moved key bindnings to ControlPanelTower
 */
public class ControlPanel extends BoardObject{

    //The ControlPanel data
    private static int width = 300;
    private static int height = Constants.GAME_WIDTH;

    private static ProximityVector position = new ProximityVector(Constants.GAME_WIDTH - width, 0);
    private static Image background = new Image(Constants.FILE_PATH + "Backgrounds/square.png");

    //The information in the top
    private PercentBar percentBar;
    private static Image HPforeground = new Image(Constants.FILE_PATH + "Backgrounds/health.png");
    private static Image HPbackground = new Image(Constants.FILE_PATH + "Backgrounds/square.png");
    private static String HPtext = "HP";

    private ResourceDisplayerCollection resourceDisplayerCollection;

    //The information about the towers
    private List<ControlPanelTower> controlPanelTowerList = new ArrayList<ControlPanelTower>();
    private List<ResourceDisplayerCollection> towerResourceCollections = new ArrayList<ResourceDisplayerCollection>();

    //The amount of towers that is shown on one row in the ControlPanel
    private int towersPerRow = 2;

    //Other
    private Map map;

    /**
     * Create a new instance of the controll panel
     */
    public ControlPanel(Map map) {
        super(position, background, 0, width, height);

        if(map == null) {
            throw new IllegalArgumentException("ControlPanel: Map can't be null");
        }
        this.map = map;
        initiateStatistics();
        initiateControlPanelTowers();
    }

    public Map getMap() {
        return map;
    }

    /**
     * Initiates all the texts of this ControlPanel
     */
    public void initiateStatistics() {
        percentBar = new PercentBar(new ProximityVector(position.x + 30, position.y + 30), width - 60, 32, HPforeground, HPbackground);
        percentBar.setText(HPtext);

        resourceDisplayerCollection = new ResourceDisplayerCollection(new ProximityVector(getPosition().x + 30, getPosition().y + 80), 90, 18, ResourceDisplayerCollection.Face.Horizontal);
    }

    /**
     * Initiates the towers that are rendered in this controlPanel
     */
    public void initiateControlPanelTowers() {

        TargetingFactory targetFactory = new TargetingFactory();
        controlPanelTowerList.add(new ControlPanelTower(new ProximityVector(0, 0), new BulletTower(new ProximityVector(0, 0), targetFactory.getTargetClosest(), map.getParticleManager())));
        controlPanelTowerList.add(new ControlPanelTower(new ProximityVector(0, 0), new MissileTower(new ProximityVector(0, 0), targetFactory.getTargetClosest(), map.getParticleManager())));
        controlPanelTowerList.add(new ControlPanelTower(new ProximityVector(0, 0), new SlowTower(new ProximityVector(0, 0), targetFactory.getTargetClosest(), map.getParticleManager())));
        controlPanelTowerList.add(new ControlPanelTower(new ProximityVector(0, 0), new SniperTower(new ProximityVector(0, 0), targetFactory.getTargetClosest(), map.getParticleManager())));

        for(int i = 0; i < controlPanelTowerList.size(); i++) {
            ProximityVector towerPosition = new ProximityVector(getPosition().x + 30 + 128 * (i % towersPerRow), 150 + 70 * (i/towersPerRow));

            ControlPanelTower cpTower = controlPanelTowerList.get(i);
            cpTower.setPosition(towerPosition);
            cpTower.setKeyBind(i+1);

            ProximityVector resourcePosition = new ProximityVector(towerPosition.x + 60, towerPosition.y + 7);
            ResourceDisplayerCollection towerResourceCollection = new ResourceDisplayerCollection(resourcePosition, 15, 12, ResourceDisplayerCollection.Face.Vertical);
            towerResourceCollection.updateResources(cpTower.getTower().getCost());
            towerResourceCollections.add(towerResourceCollection);

        }
    }



    public PercentBar getPercentBar() {
        return percentBar;
    }

    public ResourceDisplayerCollection getResourceDisplayerCollection() {
        return resourceDisplayerCollection;
    }

    public int getControlPanelTowerListSize() {
        return controlPanelTowerList.size();
    }

    public void setHealth(int percent){
        if(percent >= 100)
            percent = 100;
        if(percent <= 0)
            percent = 0;
        percentBar.setPercent(percent);
        percentBar.setText(percent + "%");
    }

    public int getHealthPercent() {
        return percentBar.getPercent();
    }

    public void setResources(Resources resources){
        resourceDisplayerCollection.updateResources(resources);
    }

    /**
     * Returns the ControlPanelTower in the ProximityVector-position taken as parameter. If none returns null
     * @param position The position to check for objects on
     * @return The ControlPanelTower in the position taken as parameter. Null if there is none.
     */
    public ControlPanelTower getTowerOnPosition(ProximityVector position) {
        for(ControlPanelTower cpTower : controlPanelTowerList) {
            if(cpTower.containsPoint(position))
                return cpTower;
        }
        return null;
    }

    /**
     * Render the Controlpanel
     * @param batch what batch to render the controlpanel
     */
    public void render(ProximityBatch batch) {

        batch.renderRepeatedly(background, this.getPosition(), width, height);

        resourceDisplayerCollection.render(batch);


        for(ControlPanelTower cpTower : controlPanelTowerList) {
            cpTower.render(batch);
        }
        percentBar.render(batch);

        for(ResourceDisplayerCollection collection : towerResourceCollections) {
            collection.render(batch);
        }

    }

    public Tower getTowerBoundTo(int i) {
        for(ControlPanelTower cpTower : controlPanelTowerList) {
            if(cpTower.getKeyBind() == i)
                return cpTower.getTower();
        }
        return null;
    }

}
