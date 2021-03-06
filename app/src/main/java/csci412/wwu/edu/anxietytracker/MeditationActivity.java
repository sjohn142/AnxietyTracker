package csci412.wwu.edu.anxietytracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MeditationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);

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


    public void updateView() {
    }

    public void viewSupport(View v) {
        Intent myIntent = new Intent(this, support.class);
        this.startActivity(myIntent);
        //overridePendingTransition(R.anim.diagonal_out,R.anim.diagonal_in);
    }
    public void viewBelly(View v) {
        Intent myIntent = new Intent(this, BBreathing.class);
        this.startActivity(myIntent);
        //overridePendingTransition(R.anim.diagonal_out,R.anim.diagonal_in);
    }
    public void viewMindful(View v) {
        Intent myIntent = new Intent(this, MBreathing.class);
        this.startActivity(myIntent);
        //overridePendingTransition(R.anim.diagonal_out,R.anim.diagonal_in);
    }
    public void viewTension(View v) {
        Intent myIntent = new Intent(this, TBreathing.class);
        this.startActivity(myIntent);
        //overridePendingTransition(R.anim.diagonal_out,R.anim.diagonal_in);
    }

}