package csci412.wwu.edu.anxietytracker;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class  VisualizationsActivity extends AppCompatActivity {
    private VisualView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout layout = new RelativeLayout(this);

        RelativeLayout drawL = new RelativeLayout(this);
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

        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
        linearParams.setMargins(0, 0, 0, height/16);
        layout.setLayoutParams(linearParams);
        layout.requestLayout();

        RelativeLayout.LayoutParams a = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        a.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        drawL.setLayoutParams(a);
        view = new VisualView(this, width, height);
        drawL.addView(view);

        RelativeLayout buttonL = new RelativeLayout(this);
        RadioGroup group = new RadioGroup(this);
        RadioButton rb1 = new RadioButton(this);
        rb1.setId(1);
        rb1.setText("JOURNAL LOG");
        rb1.setChecked(true);
        RadioButton rb2 = new RadioButton(this);
        rb2.setId(2);
        rb2.setText("SNAPSHOT LOG");
        group.addView(rb1);
        group.addView(rb2);
        group.setOrientation(LinearLayout.HORIZONTAL);
        RadioButtonHandler rbh = new RadioButtonHandler();
        group.setOnCheckedChangeListener(rbh);

        RelativeLayout.LayoutParams b = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        b.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        b.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonL.setLayoutParams(b);
        buttonL.addView(group);

        layout.addView(drawL);
        layout.addView(buttonL);
        setContentView(layout);
    }

    private class RadioButtonHandler implements RadioGroup.OnCheckedChangeListener {
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == 1) {
                view.selectJ();
                view.invalidate();
            } else {
                view.deselectJ();
                view.invalidate();
            }
        }
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