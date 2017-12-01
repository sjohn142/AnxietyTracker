package csci412.wwu.edu.anxietytracker;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class  VisualizationsActivity extends AppCompatActivity {
    private VisualView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        Resources res = getResources();
        int statusBarHeight = 0;
        int statusBarId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (statusBarId > 0) {
            statusBarHeight = res.getDimensionPixelSize(statusBarId);
        }
        int height = displaymetrics.heightPixels - statusBarHeight;
        int width = displaymetrics.widthPixels;
        view = new VisualView(this, width, height);
        setContentView(view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        view.invalidate();
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