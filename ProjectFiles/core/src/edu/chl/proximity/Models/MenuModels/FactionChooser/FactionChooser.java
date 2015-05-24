package edu.chl.proximity.Models.MenuModels.FactionChooser;

import edu.chl.proximity.Models.Player.Factions.ConcreteFactions.LadyLuck;
import edu.chl.proximity.Models.Utils.ProximityBatch;
import edu.chl.proximity.Utilities.ProximityVector;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Player.Factions.ConcreteFactions.Filler;
import edu.chl.proximity.Models.Player.Factions.ConcreteFactions.Planes;
import edu.chl.proximity.Models.Player.Factions.Faction;
import edu.chl.proximity.Models.Utils.Image;
import edu.chl.proximity.Models.Utils.ProximityFont;
import edu.chl.proximity.Utilities.Constants;
import edu.chl.proximity.Utilities.PointCalculations;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hanna Romer
 * @date 2015-04-30
 * Respresents the panel for selecting faction
 *
 *01/05 modified by Hanna Romer. Added factionList, name, factionImage, Prev + Next buttons and that you can click buttons.
 */
public class FactionChooser extends BoardObject{
    private static ProximityVector pos = new ProximityVector(0, Constants.GAME_HEIGHT-240);
    private ProximityVector imagePos=new ProximityVector(pos.x+162,pos.y + 100);

    private static Image image=new Image(Constants.FILE_PATH + "Backgrounds/FactionChooser.png");

    private List<Faction> factions= new ArrayList<Faction>();

    private ProximityFont name=new ProximityFont(new ProximityVector(pos.x+180,pos.y+10),null);
    private FactionImage factionImage;
    private NextPrevButton prev;
    private NextPrevButton next;

    private int showing=0;

    public FactionChooser(){
        super(pos, image, 0);
        factionImage=new FactionImage(imagePos);
        prev = new NextPrevButton(new ProximityVector(pos.x+8, pos.y+40), new Image(Constants.FILE_PATH + "Buttons/FactionChooserLeft.png"));
        next = new NextPrevButton(new ProximityVector(pos.x+352, pos.y+40),new Image(Constants.FILE_PATH + "Buttons/FactionChooserRight.png"));
        factions.add(new Planes());
        factions.add(new Filler());
        factions.add(new LadyLuck());

        factionImage.setImage(factions.get(showing).getShowImage());
        name.setText(factions.get(showing).getName());
    }

    /**
     * Called if next-button is pressed
     */
    public void pressedNext(){
        if(showing==factions.size()-1){
            showing=0;
        }else{
            showing++;
        }
        setFactionData();
    }

    /**
     * Called if previous-button is pressed
     */
    public void pressedPrevious(){
        if(showing == 0){
            showing=factions.size()-1;
        }else{
            showing--;
        }
        setFactionData();
    }

    public void pressed(ProximityVector pos){
        if(pos!=null) {
            if (next.containsPoint(pos)) {
                pressedNext();
            } else if (prev.containsPoint(pos)) {
                pressedPrevious();
            }
        }
    }

    /**
     * Set image and description to that of which faction is currently beong shown
     */
    public void setFactionData(){
        name.setText(factions.get(showing).getName());
        factionImage.setImage(factions.get(showing).getShowImage());

    }

    public Faction getCurrentlyShown(){
        return factions.get(showing);
    }

    public void render(ProximityBatch batch){
        super.render(batch);
        factionImage.render(batch);
        next.render(batch);
        prev.render(batch);
        name.draw(batch);
    }


}
