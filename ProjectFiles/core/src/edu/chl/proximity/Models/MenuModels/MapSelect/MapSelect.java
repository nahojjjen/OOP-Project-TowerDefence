package edu.chl.proximity.Models.MenuModels.MapSelect;

import com.badlogic.gdx.Game;
import edu.chl.proximity.Models.Map.Maps.*;
import edu.chl.proximity.Models.Map.Particles.ParticleManager;
import edu.chl.proximity.Models.Player.Players.GameData;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hanna Romer
 * @date 2015-05-03
 *
 * A class that manages the group of MapSelectIcons on the main menu.
 *
 * 08/05 modified by Hanna Romer. Removed mapName.
 * 24/05 modified by Hanna Romer. Added nullcheckers
 */
public class MapSelect extends BoardObject{
    private static ProximityVector pos=new ProximityVector(0,0);
    private List<MapSelectIcon> maps = new ArrayList<MapSelectIcon>();
    private int selected=0;

    public MapSelect(ParticleManager particleManager){
        super(pos, null, 0);
        maps.add(new MapSelectIcon(new StandardMap(particleManager), new ProximityVector(200, 200)));
        maps.add(new MapSelectIcon(new FillerMap(particleManager), new ProximityVector(300,210)));
        maps.add(new MapSelectIcon(new DifficultJuggMap(particleManager),new ProximityVector(660,150)));
        maps.add(new MapSelectIcon(new SmallSpiralMap(particleManager),new ProximityVector(420,170)));
        maps.add(new MapSelectIcon(new SnakeMap(particleManager),new ProximityVector(560,140)));
        maps.add(new MapSelectIcon(new ZigZagMap(particleManager),new ProximityVector(750,200)));

        initiateMapIcons();
        initiateMapTexts();

        maps.get(selected).setAsSelected();
    }


    private void initiateMapTexts(){
        for(MapSelectIcon mapIcon:maps){
            double completion = GameData.getInstance().getPlayer().howManyWavesHasPlayerReached(mapIcon.getMap());
            String completionString = String.valueOf(completion);
            ProximityVector textPosition = new ProximityVector(mapIcon.getPosition().x,mapIcon.getPosition().y+40);
            String fullText;

            if (completion > GameData.getInstance().getPlayer().winCondition){
                fullText = completionString + " - won";}

                else if(completion > 0){
                    fullText = completionString;
                }else if (!GameData.getInstance().getPlayer().hasPlayerWonPreviousMap(mapIcon.getMap())){
                fullText = "Locked";
            } else{
                fullText = "New!";
            }


            ProximityFont text = new ProximityFont(textPosition, fullText);
            mapIcon.setCompletionText(text);
        }
    }
    /**
     * Cycles through all the icons and sets their imagend completion text
     */
    private void initiateMapIcons(){
        // initiate the images to show if they are selectable
        for(MapSelectIcon m:maps){
            if (GameData.getInstance().getPlayer().hasPlayerWonPreviousMap(m.getMap())){
                m.setAsSelectable();
            }else{
                m.setAsNotSelectable();
            }
        }
    }
    public Map getSelected(){
        return maps.get(selected).getMap();
    }

    public void pressed(ProximityVector pos){
        if(pos!=null) {
            for (int i = 0; i < maps.size(); i++) {
                if (maps.get(i).containsPoint(pos)) {
                    mapClicked(i);
                }
            }
        }
    }

    public void mapClicked(int index){
        if(index>=0 && index<maps.size()) {
            MapSelectIcon map = maps.get(index);
            if (GameData.getInstance().getPlayer().hasPlayerWonPreviousMap(map.getMap())) {
                maps.get(selected).setAsSelectable();
                maps.get(index).setAsSelected();
                selected = index;
            }
        }
    }

    public void render(ProximityBatch batch){
        for(MapSelectIcon map:maps){
            map.render(batch);
        }
    }
}
