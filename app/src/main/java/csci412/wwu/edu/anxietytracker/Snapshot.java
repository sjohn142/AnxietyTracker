package csci412.wwu.edu.anxietytracker;

/**
 * Created by Nityj on 11/16/2017.
 */

public class Snapshot {

    private double lat;
    private double longi;
    private int id;
    private int mood;

    public Snapshot(int id, double lat, double longi,int mood) {
        setId(id);
        setLat(lat);
        setLong(longi);
        setMood(mood);
    }

    public void setId(int newId) {
        id = newId;
    }

    public void setLat(double newLat) {
        lat = newLat;
    }

    public void setLong(double newLong) {
        longi = newLong;
    }

    public void setMood(int newMood) {
        mood = newMood;
    }

    public int getId() {
        return id;
    }

    public double getLat() {
        return lat;
    }

    public double getLongi() {
        return longi;
    }

    public int getMood() {
        return mood;
    }

}