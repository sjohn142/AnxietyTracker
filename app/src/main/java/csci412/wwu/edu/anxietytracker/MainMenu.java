package csci412.wwu.edu.anxietytracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/**
 * Created by e6440 on 11/2/2017.
 */

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        TextView maintext = (TextView) findViewById(R.id.mainmenutext);
        maintext.setTypeface(Typeface.createFromAsset(getAssets(), "DancingScript-Regular.ttf"));
    }

    public void onStart() {
        super.onStart();
        //updateView();
    }

    public void updateView() {
    }

    public void logSnap(View v) {
        Intent myIntent = new Intent(this, LogASnapshotActivity.class);
        this.startActivity(myIntent);
        //overridePendingTransition(R.anim.diagonal_out,R.anim.diagonal_in);
    }

    public void startJournal(View v) {
        Intent myIntent = new Intent(this, JournalActivity.class);
        this.startActivity(myIntent);
        //overridePendingTransition(R.anim.diagonal_out,R.anim.diagonal_in);
    }

    public void startMed(View v) {
        Intent myIntent = new Intent(this, MeditationActivity.class);
        this.startActivity(myIntent);
        //overridePendingTransition(R.anim.diagonal_out,R.anim.diagonal_in);
    }

    public void startVis(View v) {
        Intent myIntent = new Intent(this, VisualizationsActivity.class);
        this.startActivity(myIntent);
        //overridePendingTransition(R.anim.diagonal_out,R.anim.diagonal_in);
    }
}