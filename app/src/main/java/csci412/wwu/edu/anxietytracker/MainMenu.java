package csci412.wwu.edu.anxietytracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
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
}