package csci412.wwu.edu.anxietytracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

public class JournalActivity extends AppCompatActivity {
    private JournalDatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new JournalDatabaseManager(this);
        setContentView(R.layout.activity_journal);
    }

    public void insert(View v) {
        EditText entryET = (EditText) findViewById(R.id.journalET);
        SeekBar moodSB = (SeekBar) findViewById(R.id.moodSeekBar);
        String date = "thisisthedate";
        //Get date from device? Or input. Probs get date.

        String entry = entryET.getText().toString();
        int mood = moodSB.getProgress();
        Toast.makeText(this, "mood is " + mood, Toast.LENGTH_SHORT).show();

        Journal tjournal = new Journal(0, date, entry, mood);
        Log.w("MainActivity", "journal = " + tjournal.toString());
        dbManager.insert(tjournal);

        //clear info?

    }

    public void goBack(View v) { this.finish();}
}