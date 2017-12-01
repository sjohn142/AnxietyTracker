package csci412.wwu.edu.anxietytracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class JournalActivity extends AppCompatActivity {
    private JournalDatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new JournalDatabaseManager(this);
        setContentView(R.layout.activity_journal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_log:
                Log.w("MainActivity", "Open Snapshots");
                Intent snapIntent = new Intent(this, LogASnapshotActivity.class);
                this.startActivity(snapIntent);
                return true;
            case R.id.action_jour:
                Log.w("MainActivity", "Open Journal");
                Intent journalIntent = new Intent(this, JournalActivity.class);
                this.startActivity(journalIntent);
                return true;
            case R.id.action_med:
                Log.w("MainActivity", "Open Meditation");
                Intent medIntent = new Intent(this, MeditationActivity.class);
                this.startActivity(medIntent);
                return true;
            case R.id.action_vis:
                Log.w("MainActivity", "Open Visualizations");
                Intent visIntent = new Intent(this, VisualizationsActivity.class);
                this.startActivity(visIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
  
    public void insert(View v) {
        EditText entryET = (EditText) findViewById(R.id.journalET);
        SeekBar moodSB = (SeekBar) findViewById(R.id.moodSeekBar);
        //String date = "thisisthedate";
        String date = DateFormat.getDateInstance().format(new Date());
        //Get date from device? Or input. Probs get date.

        String entry = entryET.getText().toString();
        int mood = moodSB.getProgress();
        Toast.makeText(this, "date is " + date, Toast.LENGTH_SHORT).show();

        Journal tjournal = new Journal(0, date, entry, mood);
        Log.w("MainActivity", "journal = " + tjournal.toString());
        dbManager.insert(tjournal);
    }

    public void viewEntries(View v) {
        Intent viewJournals = new Intent(this, ViewJournalActivity.class);
        this.startActivity(viewJournals);
        finish();
    }

    public void goBack(View v) { this.finish();}
}