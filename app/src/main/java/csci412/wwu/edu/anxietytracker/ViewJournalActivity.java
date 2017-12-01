package csci412.wwu.edu.anxietytracker;

/* This code is modified from code provided in Professor Ahmed's Chapter 5 slides
    in CSCI 412, Fall 2017.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

import csci412.wwu.edu.anxietytracker.Journal;
import csci412.wwu.edu.anxietytracker.JournalDatabaseManager;

public class ViewJournalActivity extends AppCompatActivity {
    private JournalDatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new JournalDatabaseManager(this);
        updateView();
    }

    public void updateView() {
        ArrayList<Journal> journalList = dbManager.selectAll();
        RelativeLayout layout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);
        RadioGroup group = new RadioGroup(this);
        ButtonHandler bh = new ButtonHandler();
        for (Journal entry : journalList) {
            Button rb = new Button(this);
            //rb.setWidth();
            rb.setId(entry.getId());
            rb.setOnClickListener(bh);
            rb.setText("DATE: " + entry.getDate() + " MOOD: " + entry.getMoodLevel());
            group.addView(rb);
        }

        scrollView.addView(group);
        layout.addView(scrollView);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0, 0, 0, 50);
        setContentView(layout);
    }

    private class RadioButtonHandler implements RadioGroup.OnCheckedChangeListener {
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            updateView();
        }
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            int id = v.getId();
            dbManager.deleteById(id);
            updateView();
            Toast.makeText(ViewJournalActivity.this, id + " removed successfully.", Toast.LENGTH_LONG).show();
        }
    }
}