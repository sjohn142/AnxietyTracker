package csci412.wwu.edu.anxietytracker;

/**
 * Created by Nityj on 11/16/2017.
 */

public class Snapshot {

    private float lat;
    private float longi;
    private int id;
    private int mood;

    public Snapshot(int id, float lat, float longi,int mood) {
        setId(id);
        setLat(lat);
        setLong(longi);
        setMood(mood);
    }

    public void setId(int newId) {
        id = newId;
    }

    public void setLat(float newLat) {
        lat = newLat;
    }

    public void setLong(float newLong) {
        longi = newLong;
    }

    public void setMood(int newMood) {
        mood = newMood;
    }

    public int getId() {
        return id;
    }

    public float getLat() {
        return lat;
    }

    public float getLongi() {
        return longi;
    }

    public int getMood() {
        return mood;
    }


}
