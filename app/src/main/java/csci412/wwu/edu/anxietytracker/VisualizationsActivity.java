package csci412.wwu.edu.anxietytracker;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

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
}