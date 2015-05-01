package edu.chl.proximity.Models.MenuModels.FactionChooser;

import com.badlogic.gdx.math.Vector2;
import edu.chl.proximity.Models.BoardObject;
import edu.chl.proximity.Models.Player.Factions.ConcreteFactions.Planes;
import edu.chl.proximity.Models.Player.Factions.Faction;
import edu.chl.proximity.Models.Utils.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hanna Römer
 * @date 2015-04-30
 */
public class FactionChooser extends BoardObject{
    private static Vector2 pos = new Vector2(0,0);
    private Vector2 imagePos=new Vector2(0,0);

    private static Image image=null;
    private List<Faction> factions= new ArrayList<Faction>();

    private FactionImage factionImage=new FactionImage(imagePos);
    private int showing=0;

    public FactionChooser(Vector2 position){
        super(pos,image,0);
        factions.add(new Planes());
    }


}
