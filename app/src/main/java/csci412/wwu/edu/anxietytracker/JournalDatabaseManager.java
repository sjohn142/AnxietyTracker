package csci412.wwu.edu.anxietytracker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Nityj on 11/15/2017.
 */


public class JournalDatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "journalDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_JOURNAL = "journal";
    private static final String ID = "id";
    private static final String ENTRY = "entry";
    private static final String DATE = "date";
    private static final String MOOD = "mood";

    public JournalDatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "create table " + TABLE_JOURNAL + "( " + ID;
        sqlCreate += " integer primary key autoincrement, " + DATE + " text, ";
        sqlCreate += ENTRY + " text," + MOOD + "text )";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_JOURNAL);
        onCreate(db);
    }

    public void insert(Journal journal) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_JOURNAL;
        sqlInsert += " values( null, '" + journal.getDate() + "', '";
        sqlInsert += journal.getJournalEntry() + "', '" +journal.getMoodLevel() + "' )";
        db.execSQL(sqlInsert);
        db.close();
    }

    public void deleteById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from " + TABLE_JOURNAL;
        sqlDelete += " where " + ID + " = " + id;
        db.execSQL(sqlDelete);
        db.close();
    }

    public void updateById(int id, String date, String entry, int mood) {
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlUpdate = "update " + TABLE_JOURNAL;
        sqlUpdate += " set " + DATE + " = '" + date + "', " + ENTRY;
        sqlUpdate += " = '" + entry + "', " + MOOD + " = '" + mood;
        sqlUpdate += "' where " + ID + " = " + id;

        db.execSQL(sqlUpdate);
        db.close();
    }

    public ArrayList<Journal> selectAll() {
        String sqlQuery = "select * from " + TABLE_JOURNAL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Journal> journals = new ArrayList<>();

        while (cursor.moveToNext()) {
            Journal currentJournal = new Journal(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                    cursor.getString(2), Integer.parseInt(cursor.getString(3)));
            journals.add(currentJournal);
        }
        db.close();
        return journals;
    }

    public ArrayList<Journal> selectByDate(String date) {
        String sqlQuery = "select * from " + TABLE_JOURNAL;
        sqlQuery += " where " +DATE + " = " + date;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        ArrayList<Journal> journals = new ArrayList<>();

        while (cursor.moveToNext()) {
            Journal currentJournal = new Journal(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                    cursor.getString(2), Integer.parseInt(cursor.getString(3)));
            journals.add(currentJournal);
        }
        db.close();
        return journals;
    }

    public Journal selectById(int id) {
        String sqlQuery = "select * from " + TABLE_JOURNAL;
        sqlQuery += " where " + ID + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        Journal journal = null;

        if (cursor.moveToNext()) {
            journal= new Journal(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                    cursor.getString(2), Integer.parseInt(cursor.getString(3)));
        }
        db.close();
        return journal;
    }

}
