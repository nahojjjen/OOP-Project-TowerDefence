package edu.chl.proximity.Models.ResourceSystem;

/**
 * Created by Hanna on 2015-04-15.
 */
public class Resources {
    private int points;
    private int lines;
    private int polygons;

    public Resources(int startPoints, int startLines, int startPolygons ){
        points=startPoints;
        lines=startLines;
        polygons=startPolygons;
    }

    public int getPoints() {
        return points;
    }
    public int getLines() {
        return lines;
    }
    public int getPolygons() {
        return polygons;
    }

    public void addPoints(int p){
        points += p;
    }
    public void addLines(int l){
        lines += l;
    }
    public void addPolygons(int p){
        polygons += p;
    }

    public void removePoints(int p){
        points -= p;
    }
    public void removeLines(int l){
        lines -= l;
    }
    public void removePolygons(int p){
        polygons -=p;
    }
}
