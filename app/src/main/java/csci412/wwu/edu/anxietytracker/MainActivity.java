package csci412.wwu.edu.anxietytracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private static int TIMEOUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(MainActivity.this, MainMenu.class);
                startActivity(mainIntent);
                finish();
            }
        }, TIMEOUT);
    }
}
