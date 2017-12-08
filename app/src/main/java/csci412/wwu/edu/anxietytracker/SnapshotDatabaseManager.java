package csci412.wwu.edu.anxietytracker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Nityj on 11/16/2017.
 */

public class SnapshotDatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "snapshotDB2";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SNAPSHOT = "snapshot";
    private static final String ID = "id";
    private static final String LAT = "lat";
    private static final String LONGI = "longi";
    private static final String MOOD = "mood";
    private static final String DATE = "thedate";

    public SnapshotDatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "create table " + TABLE_SNAPSHOT + "( " + ID;
        sqlCreate += " integer primary key autoincrement, " + LAT + " real, ";
        //sqlCreate += LONGI + " real, " + MOOD + " integer )";
        sqlCreate += LONGI + " real, " + MOOD + " integer, " + DATE + " text )";//changed mood to text instead of integer ??? Has ramifications
        Log.w("MainActivity", "SOMETHINGBSY"+sqlCreate);
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_SNAPSHOT);
        onCreate(db);
    }

    public void insert (Snapshot snapshot) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_SNAPSHOT;
        sqlInsert += " values( null, '" + snapshot.getLat() + "', '" + snapshot.getLongi();
        //sqlInsert += "', '" + snapshot.getMood() + "' )";
        sqlInsert += "', '" + snapshot.getMood() + "', '" + snapshot.getDate() + "' )";
        db.execSQL(sqlInsert);
        Log.w("MainActivity", "SOMETHINGBSY"+snapshot.toString());
        db.close();
    }

    public void deleteById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from " + TABLE_SNAPSHOT;
        sqlDelete += " where " + ID + " = " + id;
        db.execSQL(sqlDelete);
        db.close();
    }

    public ArrayList<Snapshot> selectAll() {
        String sqlQuery = "select * from " + TABLE_SNAPSHOT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        ArrayList<Snapshot> snapshots = new ArrayList<>();

        while(cursor.moveToNext()) {
            Snapshot curSS = new Snapshot(cursor.getInt(0), cursor.getFloat(1),
                cursor.getFloat(2), cursor.getInt(3), cursor.getString(4));
            snapshots.add(curSS);
        }
        db.close();
        return snapshots;
    }

    public Snapshot selectById(int id) {
        String sqlQuery = "select * from " + TABLE_SNAPSHOT;
        sqlQuery += " where " + ID + " = " + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        Snapshot ss = null;
        /*if (cursor.moveToFirst()) {
            ss = new Snapshot(cursor.getInt(0), cursor.getFloat(1),
                    cursor.getFloat(2), cursor.getInt(3), cursor.getString(4));
        }*/
        if (cursor.moveToFirst()) {
            ss = new Snapshot(cursor.getInt(0), cursor.getFloat(1),
                    cursor.getFloat(2), cursor.getInt(3), cursor.getString(4));
        }
        db.close();
        return ss;
    }
}
