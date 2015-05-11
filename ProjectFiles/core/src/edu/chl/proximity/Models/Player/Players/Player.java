package edu.chl.proximity.Models.Player.Players;

import edu.chl.proximity.Models.Player.Factions.Faction;
import edu.chl.proximity.Models.Player.PersistentSave.SaveManager;
import edu.chl.proximity.Models.Player.ResourceSystem.Resources;
import edu.chl.proximity.Models.Utils.Settings;

/**
 * @author Hanna R�mer
 * @date 2015-04-15
 * A class epresenting the player.
 *
 * 23/04 Modified by Simon. Adding depth to leveling up
 * 28/04 Modified by Simon. Adding level
 * 11/5 Modified by Johan, enables saving information to harddrive && autosave on close
 */
public class Player {
    private Resources resources;
    private Faction faction;
    private int experiencePoints;
    private double level;
    private Settings settings;
    private SaveManager saveManager;
    /**
     * Create a new player with starting resources of 300 points,
     * 300 lines and 0 polygons
     * @param faction the players faction
     */
    public Player(Faction faction){
        resources = new Resources(500, 500, 0);
        this.faction=faction;
        //Standard settings are set
        this.settings = new Settings();

        this.saveManager = new SaveManager();

        loadSaveData();

        createSaveHook();
    }

    private void loadSaveData(){
        saveManager.loadSave(1);

        this.experiencePoints = Math.round(saveManager.get("exp"));
        System.out.println("exp loaded: " + this.experiencePoints);

        this.settings.setGameVolume(saveManager.get("vol"));
        System.out.println("Game volume loaded: " + saveManager.get("vol") + "(It doesnt work because sound is re-loaded instantly when a new map is loaded or something, i dont know Hanna made that feature : i just wanna demonstrate that you can save misc stuff.)");

        this.level = saveManager.get("lvl");
        System.out.println("Player level loaded: "+ level);


    }
    /**
     * create a new thread which will run on program shutdown, which saves the current player details to the disk
     */
    private void createSaveHook(){

        Thread shutdownHook = new Thread("Proximity-Shutdown-Save-Logic" ) {
            public void run() {
                System.out.println("Starting save...");
                saveManager.write("exp", (float) experiencePoints);
                System.out.println("Saved exp: " + experiencePoints);

                saveManager.write("lvl", (float) level);
                System.out.println("Saved level: " + level);

                saveManager.write("vol", settings.getGameVolume());
                System.out.println("Saved game volume: " + settings.getGameVolume() + "(It doesnt work because sound is re-loaded instantly when a new map is loaded or something, i dont know Hanna made that feature : i just wanna demonstrate that you can save misc stuff.)");

                saveManager.save(1);
                System.out.println("Save Complete!");
            }
        };
        Runtime.getRuntime().addShutdownHook( shutdownHook );

    }

    /**
     * Change the players faction
     * @param f the player's new faction
     */
    public void setFacton(Faction f){
        this.faction=f;
    }

    /**
     * Get the player's current faction
     * @return the player's faction
     */
    public Faction getFaction() {
        return faction;
    }

    /**
     * Adds xp to the player. this method in turn handles leveling up
     */
    public void addExperiencePoints(int xp) {
        experiencePoints += xp;
        level = (Math.sqrt(100 * (2 * experiencePoints + 25)) + 50)/100;
    }

    /**
     * Get the player's instance of Resources
     * @return the player's Resources
     */
    public Resources getResources() {
        return resources;
    }

    //Helper method to check if the player can afford an item
    public boolean canPlayerAfford(Resources res) {
        boolean result = resources.getPoints() >= res.getPoints() && resources.getLines() >= res.getLines() && resources.getPolygons() >= res.getPolygons();
        return result;
    }

    //Returns XP
    public int getExperience() {
        return experiencePoints;
    }
    public double getLevel() {
        return level;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}
