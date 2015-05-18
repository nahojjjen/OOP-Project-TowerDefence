package edu.chl.proximity.Models.Player.PersistentSave;

import java.io.*;
import java.util.HashMap;

/**
 * @author Johan
 * @date 2015-05-09.
 * A class which can load and save a hashmap to disk
 * This is used for saving the player game data to the harddrive
 *
 * For instance, "exp" with "123": saveManager.save)("exp",123);
 * One can then call saveManager.get("exp") to get 123
 *
 * You need to specify which savefile to load and save from.
 */
public class SaveManager {
    private HashMap<String, Float> saveMap = new HashMap<String, Float>();


    /**
     * Place a value in the saveMap !!Does not actually save value to disk until you call save(x)!!
     * @param input What string to connect the value to
     * @param value What value  the word should be connected to
     */
    public void write(String input, Float value){
        if (input != null && value != null){
            //remove already existing value
            if (saveMap.containsKey(input)){
                saveMap.remove(input);
            }
            //put the value
            saveMap.put(input, value);
        }

    }

    /**
     * get a saved value
     *
     * If the saved value does not exist, creates an input with the value 0.
     * @param input What do you want to get? example: get exp
     * @return An Float corresponding to a previous save value, null if no value exists
     */
    public Float get(String input){
        if(saveMap.containsKey(input)){
            return saveMap.get(input);
        }
        else{
            System.out.println("Loaded no value from " + "\"" + input + "\"" + ", no value on savefile" );
            return null;
        }


    }


    /**
     * Check if a savefile exists with the corresponding number
     */
    public boolean doesSaveExist(int number) {
        File saveFile = new File("ProximitySave"+number +".data");
        return(saveFile.exists());
    }



    /**
     * Writes the object to the harddrive
     * @param saveNumber what number the file should be called
     */
    public void save(int saveNumber){
        ObjectOutputStream obj_out = null;
        try {
            // Enable writing data to the harddrive
            FileOutputStream fileOutputStream = new FileOutputStream("ProximitySave"+saveNumber +".data");

            // Enable writing an object to the harddrive, using the data connection

            obj_out = new ObjectOutputStream(fileOutputStream);

            // Write out the saveMap to the harddrive
            obj_out.writeObject ( saveMap);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        }
    }


    /**
     * load a previous savefile from disk & loads it as current save hashMap
     * @param number what number the savefile you load should have (loads the file with the number name)
     */
    public void loadSave(int number){
        // Enable loading data from the harddrive
        FileInputStream fileInputStream = null;

        if (!doesSaveExist(1)){
           save(number); //create an empty save-file
            System.out.println("creating file for first time");
        }


        try {
            fileInputStream = new FileInputStream("ProximitySave" + number + ".data");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Enable loading objects from the harddrive
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Load the object
        Object readObject = null;
        try {
            readObject = objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Make the object a hashmap && load it as current saveFile
        if (readObject instanceof HashMap){
            HashMap<String, Float> loadedSave = (HashMap)readObject;
            saveMap = loadedSave;
        }

        try {
            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
