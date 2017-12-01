package csci412.wwu.edu.anxietytracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
/*
Created by ES on 11/20/2017
 */

public class support extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);


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

}
