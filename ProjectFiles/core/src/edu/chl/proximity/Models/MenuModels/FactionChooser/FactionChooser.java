package edu.chl.proximity.Models.MenuModels.FactionChooser;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
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
 * @author Hanna Römer
 * @date 2015-04-30
 * Respresents the panel for selecting faction
 *
 *01/05 modified by Hanna Römer. Added factionList, name, factionImage, Prev + Next buttons and that you can click buttons.
 */
public class FactionChooser extends BoardObject{
    private static Vector2 pos = new Vector2(0, Gdx.graphics.getHeight()-240);
    private Vector2 imagePos=new Vector2(pos.x+162,pos.y + 100);

    private static Image image=new Image(Constants.filePath + "Backgrounds/factionChooser.png");

    private List<Faction> factions= new ArrayList<Faction>();

    private ProximityFont name=new ProximityFont(new Vector2(pos.x+180,pos.y+10),null);
    private FactionImage factionImage=new FactionImage(imagePos);
    private NextPrevButton prev = new NextPrevButton(new Vector2(pos.x+8, pos.y+40), new Image(Constants.filePath + "Buttons/FactionChooserLeft.png"));
    private NextPrevButton next = new NextPrevButton(new Vector2(pos.x+352, pos.y+40),new Image(Constants.filePath + "Buttons/FactionChooserRight.png"));

    private int showing=0;

    public FactionChooser(){
        super(pos, image, 0);
        factions.add(new Planes());
        factions.add(new Filler());
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

    public void pressed(Vector2 pos){
        if(PointCalculations.isPointInObject(pos,next)){
            pressedNext();
        }else if(PointCalculations.isPointInObject(pos,prev)){
            pressedPrevious();
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

    public void render(SpriteBatch batch){
        super.render(batch);
        factionImage.render(batch);
        next.render(batch);
        prev.render(batch);
        name.draw(batch);
    }


}
