package csci412.wwu.edu.anxietytracker;

/**
 * Created by Nityj on 11/15/2017.
 */

public class Journal {

    private String date;
    private String journalEntry;
    private int moodLevel;
    private int id;

    public Journal(int curId, String curDate, String curJournalEntry, int curMoodLevel) {
        setId(curId);
        setDate(curDate);
        setMood(curMoodLevel);
        setJournal(curJournalEntry);
    }

    //Potentially create a toString?

    void setId(int curId) {
        id = curId;
    }

    void setDate(String curDate) {
        date = curDate;
    }

    void setMood(int curMood) {
        moodLevel = curMood;
    }

    void setJournal(String curJournal) {
        journalEntry = curJournal;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getJournalEntry() {
        return journalEntry;
    }

    public int getMoodLevel() {
        return moodLevel;
    }

}