package edu.chl.proximity.Models.ControlPanel;

import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.proximity.Models.Map.Towers.*;
import edu.chl.proximity.Models.Utils.PercentBar;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Proximity;
import edu.chl.proximity.Utilities.*;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Towers.TargetingMethods.TargetingFactory;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Models.ResourceSystem.Resources;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Linda Evaldsson
 * @date 2015-04-17
 *
 * The class managing the information to the right of the screen
 *
 * Unknown date modified by Johan Swanberg
 * 08/05 modified by Linda Evaldsson. Changed percentBar to use textures (images). Moved some rendering to Image class.
 * 18/05 modified by Simon to add a sniper tower
 */
public class ControlPanel extends BoardObject{

    //The texts that are displayed
    private ProximityFont lineText;
    private ProximityFont polygonText;

    public ProximityFont getLineText() {
        return lineText;
    }

    public ProximityFont getPolygonText() {
        return polygonText;
    }

    public ProximityFont getPointText() {
        return pointText;
    }

    public PercentBar getPercentBar() {
        return percentBar;
    }

    private ProximityFont pointText;

    private PercentBar percentBar;

    //Width and heigh of the ControlPanel when it is initiated
    private static int width = 300;
    private static int height = Constants.GAME_WIDTH;


    private static Image HPforeground = new Image(Constants.FILE_PATH + "Backgrounds/health.png");
    private static Image HPbackground = new Image(Constants.FILE_PATH + "Backgrounds/square.png");
    private static String HPtext = "HP";

    private static ProximityVector position = new ProximityVector(Constants.GAME_WIDTH - width, 0);

    //The towers that are rendered on the ControlPanel
    private List<ControlPanelTower> controlPanelTowerList = new ArrayList<ControlPanelTower>();

    //The amount of towers that is shown on one row in the ControlPanel
    private int towersPerRow = 1;

    //The background of the ControlPanel
    private static Image background = new Image(Constants.FILE_PATH + "Backgrounds/tweed.png");

    private Map map;
    private Viewport viewport;

    /**
     * Create a new instance of the controll panel
     */
    public ControlPanel(Map map, Viewport viewport) {
        super(position, background, 0, width, height);

        if(map == null) {
            throw new IllegalArgumentException("ControlPanel: Map can't be null");
        }
        this.map = map;
        this.viewport=viewport;
        initiateText();
        initiateControlPanelTowers();
    }

    public Map getMap() {
        return map;
    }

    /**
     * Initiates all the texts of this ControlPanel
     */
    public void initiateText() {
        percentBar = new PercentBar(new ProximityVector(position.x + 30, position.y + 30), width - 60, 32, HPforeground, HPbackground);
        percentBar.setText(HPtext);

        lineText = createFont(30, 80, "null");
        polygonText = createFont(30, 100, "null");
        pointText = createFont(30, 120, "null");
    }

    public int getControlPanelTowerListSize() {
        return controlPanelTowerList.size();
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
            controlPanelTowerList.get(i).setPosition(new ProximityVector(getPosition().x + 30 + 50 * (i % towersPerRow), 150 + 50 * (i/towersPerRow)));
        }
    }


    public void setHealth(int percent){
        if(percent >= 100)
            percent = 100;
        if(percent <= 0)
            percent = 0;
        percentBar.setPercent(percent);
        percentBar.setText(percent + "%");
        /*
        if(percent<=0){
            game.changeScreen(Proximity.State.GAME_OVER,map, GameData.getInstance().getPlayer(),viewport);
        }
        */
    }

    public int getHealthPercent() {
        return percentBar.getPercent();
    }

    public void setResources(Resources resources){
        if(resources != null) {
            lineText.setText("Lines: " + resources.getLines());
            polygonText.setText("Polygons: " + resources.getPolygons());
            pointText.setText("Points: " + resources.getPoints());
        }

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
     * Create a new text within the controlpanel
     * @param x x coordinate within the control panel
     * @param y y coordinate within the control panel
     * @param s what the string should say
     * @return a ProximityFont object corresponding to this information
     */
    private ProximityFont createFont(float x, float y, String s){
        return new ProximityFont(new ProximityVector(getPosition().x + x, y), s);
    }

    /**
     * Render the Controlpanel
     * @param batch what batch to render the controlpanel
     */
    public void render(ProximityBatch batch) {

        batch.renderRepeatedly(background, this.getPosition(), width, height);

        lineText.draw(batch);
        pointText.draw(batch);
        polygonText.draw(batch);
        for(ControlPanelTower cpTower : controlPanelTowerList) {
            cpTower.render(batch);
        }
        percentBar.render(batch);
    }

    public Tower getTowerBoundTo(int i) {
        if(i <= controlPanelTowerList.size() && i > 0) {
            return controlPanelTowerList.get(i - 1).getTower();
        }
        return null;
    }


}
