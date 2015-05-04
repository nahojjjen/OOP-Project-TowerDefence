package edu.chl.proximity.Models.Utils;

import com.badlogic.gdx.Gdx;
import edu.chl.proximity.Models.Map.Maps.StandardMap;
import edu.chl.proximity.Models.Player.Factions.ConcreteFactions.Planes;
import edu.chl.proximity.Models.Player.Holdables.Hand;
import edu.chl.proximity.Models.Map.Maps.Map;
import edu.chl.proximity.Models.MenuModels.MainMenu;
import edu.chl.proximity.Models.Player.Players.Player;
import edu.chl.proximity.Proximity;

/**
 * @author Linda Evaldsson and Johan Swanberg
 * @date 2015-04-08
 *
 * This class keeps track of the collected data of this current game-instance,
 * A collection of the current map, played factions, gamespeed and misc information.
 *
 * 23/04 edited by Hanna R�mer. Added ButtonPanel, PropertiesPanel and sound volume of game.
 * 24/04 edited by Hanna R�mer. Added Proximity and MainMenu + their setters and getters
 * 29/04 edited by Hanna R�mer. Removed ButtonPanel and PropertiesButton.
 */
public class GameData {

    private static GameData gameData;
    private Map map;
    private Player player;
    private Hand hand = new Hand();
    private MainMenu mainMenu;

    private int gameSpeed = 1;

    private Proximity proximity;

    public static float VOLUME= 0.1f;


    private GameData() {}


    public void setGame(Proximity proximity){
        this.proximity=proximity;
    }
    public Proximity getGame(){
        return proximity;
    }

    /**
     * set the speed in which the game logic updates
     * The method does nothing if input speedMultiplier is less than 0.
     * @param speedMultiplier how fast the game logic should update
     */
    public void setGameSpeed(int speedMultiplier){
        if(speedMultiplier >= 0){
            gameSpeed = speedMultiplier;
        }

    }

    /**
     * get the current game-speed multiplier
     * @return the current game speed multiplier
     */
    public int getGameSpeed(){return gameSpeed;}

    /**
     * get the current running game information
     * @return
     */
    public static GameData getInstance() {
        if(gameData == null) {
            gameData = new GameData();
        }
        return gameData;
    }

    /**
     * set the map of the current game
     * @param map what map to se the game to
     */
    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * get which map is currently active
     * @return (Map) which hold the current game-field data.
     */
    public Map getMap() {
        return map;
    }

    public Player getPlayer(){return player;}
    public void setPlayer(Player inputPlayer){player = inputPlayer;}

    public MainMenu getMainMenu(){ return mainMenu;}
    public void setMainMenu(MainMenu mainMenu){ this.mainMenu=mainMenu;}


    public Hand getHand() { return hand; }

    /**
     * This method creates a dummy data field with all options defaulted - dont call this method
     * before an actual game, since it resets a lot of data, It's simply for jUnit testing.
     * @return
     */
    public static GameData createTestDataEnviroment(){
        GameData testData = new GameData();
        testData.setGameSpeed(1);
        testData.setGame(null);
        testData.setMap(new StandardMap());
        testData.setPlayer(new Player(new Planes()));
        testData.setMainMenu(new MainMenu());
        gameData = testData;
        return testData;
    }
}
