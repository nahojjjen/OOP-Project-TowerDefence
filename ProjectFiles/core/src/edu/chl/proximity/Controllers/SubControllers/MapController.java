package edu.chl.proximity.Controllers.SubControllers;


import edu.chl.proximity.Controllers.ScreenChanger.ScreenChanger;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Holdables.Holdable;
import edu.chl.proximity.Models.Map.Background;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.Map.Paths.Path;
import edu.chl.proximity.Models.Map.Towers.Tower;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.ProximityVector;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Linda Evaldsson
 * @date 2015-04-22
 *
 * A controller that controls the map in the game
 *
 * 03-05-2015 Modified by Simon Gislen. Introducing: *Towers are not free*
 *
 */

public class MapController implements ClickHandler, UpdateHandler {

    private Background model;
    private List<BoardObject> models = new ArrayList<BoardObject>();
    private Map map;

    public MapController(Map map) {
        this.map = map;
        model = new Background(null);
        model.setPosition(new ProximityVector(0, 0));
        model.setWidth(Constants.GAME_WIDTH - 300);
        model.setHeight(Constants.GAME_HEIGHT);
        models.add(model);
    }


    public void update() {
        map.update();
        GameData.getInstance().getPlayer().addResources(map.getCollectedResources());
        GameData.getInstance().getPlayer().addExperiencePoints(map.getCollectedExperience());
        map.clearCollectedResources();
        map.clearCollectedExperience();
        if(map.getBase().getLife() <= 0)
            ScreenChanger.changeScreen(ScreenChanger.ScreenType.GameOver);

        map.clearAddStack();
        map.clearRemoveStack();
    }

    @Override
    public void touchDown(ProximityVector clickedPoint, int pointer, int button) {
        //Checks if the click is within the map
        if (model.containsPoint(clickedPoint)) {

            //Checks what item is currently picked up
            Holdable heldItem = map.getHand().getItem();

            //Checks if anything was clicked on the board (Ex towers)
            BoardObject clickedObject = map.getObjectOnPosition(clickedPoint);

            if(clickedObject instanceof Tower) {
                map.setChosenTower((Tower) clickedObject);
            }


            //If there is something in the hand and no tower was clicked on the map
            if (heldItem != null && !(clickedObject instanceof Tower)) {
                if(heldItem instanceof Tower) {
                    if(heldItem.isPlaced()) {
                        map.setChosenTower(null);
                    }else if(!isTowerOnLine()){
                        placeHandObject(heldItem,clickedPoint);
                    }
                }else {
                    placeHandObject(heldItem, clickedPoint);
                }
            }
        }
    }

    private void placeHandObject(Holdable item, ProximityVector clickedPoint){
        if (map.getHand().canPlayerAffordTheHand()) {
            GameData.getInstance().getPlayer().getResources().removeResources(item.getCost());
            item.preparePlacing(clickedPoint);

            map.add((BoardObject)item);

            map.getHand().setItem(null);
            if(item instanceof Tower){
                ((Tower) item).setAsPlaced(true);
                map.setChosenTower((Tower) item);

            }
        } else {
            //TODO: display error?
        }
    }
    private boolean isTowerOnLine(){
        Holdable currentItem=map.getHand().getItem();
        Path path=map.getPath();
        if(currentItem instanceof Tower){
            Tower t=(Tower) currentItem;
            System.out.println("Width: " + t.getWidth() + " Height: " + t.getHeight());
            ProximityVector pos=new ProximityVector(map.getHand().getPosition().x-t.getWidth()/2, map.getHand().getPosition().y-t.getHeight()/2);
            for(int x=0; x<t.getWidth();x++){
                if(path.isPointOnPath(new ProximityVector(pos.x+x,pos.y))){
                    return true;
                }
                if(path.isPointOnPath(new ProximityVector(pos.x+x,pos.y+t.getHeight()))){
                    return true;
                }
            }for(int y=0;y<t.getHeight();y++){
                if(path.isPointOnPath(new ProximityVector(pos.x,pos.y+y))){
                    return true;
                }
                if(path.isPointOnPath(new ProximityVector(pos.x+t.getWidth(),pos.y+y))){
                    return true;
                }
            }
        }
        return false;
    }



    @Override
    public void mouseMoved(ProximityVector newPosition) {

    }
}
