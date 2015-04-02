package edu.chl.proximity.Models.Spells;

/**
 * Created by Johan on 2015-04-02.
 */
public abstract class Spell {

    private int level;

    public Spell(int level){
        this.level = level;
    }



    /**
     * fill this with other effects for each concrete class.
     * @param i what level of the spell is activated
     */
    public abstract void activate(int i);

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
