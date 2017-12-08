package csci412.wwu.edu.anxietytracker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;

public class VisualView extends View {

    private JournalDatabaseManager dbManager;
    private SnapshotDatabaseManager sdbManager;
    private Paint paint;
    private Paint tinypaint;
    private Paint linePaint;
    private Paint linePaint2;
    private int height;
    private int width;
    private double scale;
    private int spacing1;
    private int spacing2;
    private ArrayList<Journal> journalList;
    private ArrayList<Snapshot> snapList;
    private Canvas canvas;
    private boolean selectJ;

    public VisualView(Context context, int w, int h) {
        super(context);
        this.height = h;
        this.width = w;
        this.scale = this.height/17;
        dbManager = new JournalDatabaseManager(context);
        sdbManager = new SnapshotDatabaseManager(context);
        paint = new Paint();
        paint.setColor(0xFF000000);
        paint.setAntiAlias(true);
        paint.setTextSize(this.width/20);
        tinypaint = new Paint();
        tinypaint.setColor(0xFF000000);
        tinypaint.setAntiAlias(true);
        tinypaint.setTextSize(this.width/30);
        linePaint = new Paint();
        linePaint.setColor(0xFF0000FF);
        linePaint.setStrokeWidth(10.0f);
        linePaint2 = new Paint();
        linePaint2.setColor(0xFF2E8B57);
        linePaint2.setStrokeWidth(10.0f);
        selectJ = true;
    }

    public void onDraw(Canvas c) {
        journalList = dbManager.selectAll();
        snapList = sdbManager.selectAll();
        spacing1 = (this.width-100)/(journalList.size()+1);
        spacing2 = (this.width-100)/(snapList.size()+1);
        this.canvas = c;
        drawGraph();
        if (selectJ) {
            drawJournals();
        } else {
            drawSnaps();
        }
        super.onDraw(c);
    }

    public void drawGraph() {
        int start = (int)(this.width/5.5);
        //450, 30
        canvas.drawText("100", this.width/20, this.height/2-(int)(6*scale), paint);
        canvas.drawLine(start, this.height/2-(int)(6*scale), this.width-this.width/12, this.height/2-(int)(6*scale), paint);
        // 375
        canvas.drawText("90", this.width/12, this.height/2-(int)(5*scale), paint);
        canvas.drawLine(start, this.height/2-(int)(5*scale), this.width-this.width/12, this.height/2-(int)(5*scale), paint);
        // 300
        canvas.drawText("80", this.width/12, this.height/2-(int)(4*scale), paint);
        canvas.drawLine(start, this.height/2-(int)(4*scale), this.width-this.width/12, this.height/2-(int)(4*scale), paint);
        // 225
        canvas.drawText("70", this.width/12, this.height/2-(int)(3*scale), paint);
        canvas.drawLine(start, this.height/2-(int)(3*scale), this.width-this.width/12, this.height/2-(int)(3*scale), paint);
        // 150
        canvas.drawText("60", this.width/12, this.height/2-(int)(2*scale), paint);
        canvas.drawLine(start, this.height/2-(int)(2*scale), this.width-this.width/12, this.height/2-(int)(2*scale), paint);
        // 75
        canvas.drawText("50", this.width/12, this.height/2-(int)(scale), paint);
        canvas.drawLine(start, this.height/2-(int)(scale), this.width-this.width/12, this.height/2-(int)(scale), paint);
        // 0
        canvas.drawText("40", this.width/12, this.height/2, paint);
        canvas.drawLine(start, this.height/2, this.width-this.width/12, this.height/2, paint);
        // 75
        canvas.drawText("30", this.width/12, this.height/2+(int)(scale), paint);
        canvas.drawLine(start, this.height/2+(int)(scale), this.width-this.width/12, this.height/2+(int)(scale), paint);
        // 150
        canvas.drawText("20", this.width/12, this.height/2+(int)(scale*2), paint);
        canvas.drawLine(start, this.height/2+(int)(scale*2), this.width-this.width/12, this.height/2+(int)(scale*2), paint);
        // 225
        canvas.drawText("10", this.width/12, this.height/2+(int)(scale*3), paint);
        canvas.drawLine(start, this.height/2+(int)(scale*3), this.width-this.width/12, this.height/2+(int)(scale*3), paint);
        // 300
        canvas.drawText("0", this.width/10, this.height/2+(int)(scale*4), paint);
        canvas.drawLine(start, this.height/2+(int)(scale*4), this.width-this.width/12, this.height/2+(int)(scale*4), paint);
    }

    public void drawJournals() {
        int x = spacing1+100;
        int last = 0;
        for (int i=0; i<journalList.size()-1; i++) {
            int mood = journalList.get(i).getMoodLevel();
            int mood2 = journalList.get(i+1).getMoodLevel();
            int y1;
            int y2;
            String date1 = journalList.get(i).getDate();
            String[] d1 = date1.split(",");
            date1 = d1[0];
            if (mood >= 40) {
                y1 = (this.height/2) - ((int)((mood-40)*scale/10));
            } else {
                //y1 = (this.height/2) + (50-mood)*10;
                y1 = (this.height/2) + ((int)((40-mood)*scale/10));
            }
            if (mood2 >= 40) {
                y2 = (this.height/2) - ((int)((mood2-40)*scale/10));
            } else{
                y2 = (this.height/2) + ((int)((40-mood2)*scale/10));
            }
            canvas.drawLine(x, y1, x + spacing1, y2, linePaint);
            if (((x - last) > (this.width/12)) && (x < (this.width-this.width/10))) {
                canvas.drawText(date1, x, this.height / 2 + (int) (scale * 4.5), tinypaint);
                last = x;
            } else {
                last = (x - last);
            }
            x += spacing1;
        }
    }

    public void drawSnaps() {
        int x = spacing2+100;
        int last = 0;
        for (int i=0; i<snapList.size()-1; i++) {
            int mood = snapList.get(i).getMood();
            int mood2 = snapList.get(i+1).getMood();
            String date1 = journalList.get(i).getDate();
            String[] d1 = date1.split(",");
            date1 = d1[0];
            int y1;
            int y2;
            if (mood >= 40) {
                y1 = (this.height/2) - ((int)((mood-40)*scale/10));
            } else {
                y1 = (this.height/2) + ((int)((40-mood)*scale/10));
            }
            if (mood2 >= 40) {
                y2 = (this.height/2) - ((int)((mood2-40)*scale/10));
            } else{
                y2 = (this.height/2) + ((int)((40-mood2)*scale/10));
            }
            canvas.drawLine(x, y1, x + spacing2, y2, linePaint2);
            if (((x - last) > (this.width/12)) && (x < (this.width-this.width/10))) {
                canvas.drawText(date1, x, this.height / 2 + (int) (scale * 4.5), tinypaint);
                last = x;
            } else {
                last = (x - last);
            }x += spacing2;
        }
    }

    public void selectJ() {
        this.selectJ = true;
    }

    public void deselectJ() {
        this.selectJ = false;
    }
}