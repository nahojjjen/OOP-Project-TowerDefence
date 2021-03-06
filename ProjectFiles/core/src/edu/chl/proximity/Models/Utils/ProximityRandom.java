package edu.chl.proximity.Models.Utils;

import com.badlogic.gdx.math.RandomXS128;

/**
 * @author Johan Swanberg
 * @date 2015-05-08.
 *
 * This is a service class for random implementation
 * Because of the requirements of this application, the random algorithm this service depends on
 * has a higher requirement on speed than random quality.
 *
 */
public class ProximityRandom {
    private static RandomXS128 rndGenerator = new RandomXS128();

    /**
     * get a double between 0 and 1
     * @return a double between 0 and 1, choosen in a seemingly random way
     */
    public static double getRandomDouble(){
        return rndGenerator.nextDouble();
    }


    /**
     * get a float between 0 and 1
     * @return a float between 0 and 1, choosen in a seemingly random way
     */
    public static float getRandomFloat(){
        return rndGenerator.nextFloat();
    }

    /**
     * get a random double between two bounds
     * @param lowest the lowest allowed return value
     * @param highest the highest allowed return value
     * @return a "random" value between or equals to the specified bounds, if lower input is
     * higher than highest input, illegal argument exvception is thrown. (for instance the argument 5,2)
     */
    public static double getRandomDoubleBetween(double lowest, double highest){
        if (lowest < highest){
            return (rndGenerator.nextDouble()*(highest-lowest))+lowest;
        } if (lowest == highest ){
            return lowest; //only possible outcome
        }
        else{
            throw new IllegalArgumentException("Lower bound has to be lower than higher bound, or no numbers work");
        }

    }
}
