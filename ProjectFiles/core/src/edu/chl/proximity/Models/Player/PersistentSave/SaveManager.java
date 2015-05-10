package edu.chl.proximity.Models.Player.PersistentSave;

import com.sun.media.jfxmediaimpl.MediaUtils;
import com.sun.org.apache.xpath.internal.SourceTree;
import edu.chl.proximity.Models.Player.Players.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Johan
 * @date 2015-05-09.
 */
public class SaveManager {
    private Player player;
    private static final String saveFileName = "ProximitySave.txt";
    private List<String> saveFileRows = new ArrayList();
    public SaveManager(Player player){
        this.player = player;
        createShutdownHook();
        ensureFileExists();
        loadSaveFile();


    }

    private void loadSaveFile() {
        File saveFile = new File(saveFileName);
        try{
            Scanner scanner = new Scanner(saveFile);
            while(scanner.hasNextLine()){
                saveFileRows.add(scanner.nextLine());
            }

        }catch(FileNotFoundException e){
            System.out.println("File not found!");
        }



    }

    /**
     * if the saveFile does not exist, create it.
     */
    private void ensureFileExists() {
        File saveFile = new File(saveFileName);
        if (!saveFile.exists()){
            save(); //if the file doesnt exist, create an empty save file
        }
    }

    /**
     * ensure the save method will run when the program shuts down
     */
    private void createShutdownHook() {
        Thread shutdownHook = new Thread( "Proximity-Shutdown-Save-Logic" )
        {public void run()
            {save(); } };
        Runtime.getRuntime().addShutdownHook( shutdownHook );
    }

    /**
     * writes out the experience to a file
     */
    private void save(){
        try{
            PrintWriter writer = null;
            try{
                System.out.println("Starting save to savefile!");
                writer = new PrintWriter(saveFileName, "UTF-8");
                //all save data goes here
                writer.println("Exp:" +player.getExperience());
                System.out.println("Save complete!");

            }catch (FileNotFoundException e){
                System.out.println("File not found in saveManager!!");
            }finally{
                if (writer != null){
                    writer.close();
                }

            }
        }catch(UnsupportedEncodingException e){
            System.out.println("Unsupported encoding in save file!");
        }
    }

    /***
     * get the experience written down in the file
     * @return int of experiance recorded in the file
     */
    public int getExp(){
        for (String row: saveFileRows){
            if (row.contains("Exp:")){
                return Integer.parseInt(row.substring(4));
            }
        }
        System.out.println("Exp not found in savefile, resetting to 0");
        return 0;
    }

}
