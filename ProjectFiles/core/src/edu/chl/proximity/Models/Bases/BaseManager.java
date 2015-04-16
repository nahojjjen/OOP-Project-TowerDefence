package edu.chl.proximity.Models.Bases;


/**
 * Created by Johan on 2015-04-16.
 */
public class BaseManager {

    /**
     * get a base
     * @param ID which ID the base has
     * @return A base corresponding the base ID
     */
    public Base get(int ID){
        switch (ID){
            case(1): return getShardBase();
        }
        return null;
    }

    public Base getShardBase(){
        return new ShardBase();
    }
}
