package edu.chl.proximity.Utilities;

/**
 * @author Johan
 * @date 2015-05-10.
 *
 * A class which describes a position
 */
public class ProximityVector {
    public float x;
    public float y;

    /**
     * create a new vector with the given x and y coordiantes
     * @param x what x coordinate to use
     * @param y what y coordinate to use
     */
    public ProximityVector(float x, float y){
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean equals(Object o){

        if (o != null){
            if (o instanceof ProximityVector){
                ProximityVector test = (ProximityVector)o;
                if (test.x == this.x && test.y == this.y){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * create a new vector which is identical to the supplied vector
     * Clone constructor
     * @param clone What vector to create a duplicate of
     */
    public ProximityVector(ProximityVector clone){
        this.x = clone.x;
        this.y = clone.y;
    }

    /**
     * Add a vector to this vector
     * @param addVector What vector should be added to this vector
     */
    public void add(ProximityVector addVector){
        this.x += addVector.x;
        this.y += addVector.y;
    }

    /**
     * add coordinaates to this vector
     * @param x what x coordinate to add
     * @param y what y coordinate to add
     */
    public void add(float x, float y){
        this.x += x;
        this.y += y;
    }


    /**
     * Subtract a vector from this vector
     * @param subVector What vector should be used to subtract from this vector
     */
    public void sub(ProximityVector subVector){
        this.x -= subVector.x;
        this.y -= subVector.y;
    }

    /**
     * inverts a vector, making it point in the opposite direction of before the method was run
     * @return The inverted vector
     */
    public void invert(){
        this.x = -this.x;
        this.y = -this.y;

    }
}
