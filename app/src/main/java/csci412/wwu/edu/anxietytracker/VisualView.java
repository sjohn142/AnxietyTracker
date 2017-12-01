package csci412.wwu.edu.anxietytracker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;

public class VisualView extends View {

    private JournalDatabaseManager dbManager;
    private Paint paint;
    private Paint linePaint;
    private int height;
    private int width;

    public VisualView(Context context, int w, int h) {
        super(context);
        this.height = h;
        this.width = w;
        dbManager = new JournalDatabaseManager(context);
        paint = new Paint();
        paint.setColor(0xFF000000);
        paint.setAntiAlias(true);
        paint.setTextSize(60);
        linePaint = new Paint();
        linePaint.setColor(0xFF0000FF);
        linePaint.setStrokeWidth(10.0f);
    }

    public void onDraw(Canvas canvas) {
        ArrayList<Journal> journalList = dbManager.selectAll();
        int spacing = (this.width-100)/(journalList.size()+1);
        drawGraph(canvas, spacing);
        drawJournals(canvas, spacing, journalList);
        super.onDraw(canvas);
    }

    public void drawGraph(Canvas canvas, int x) {
        int start = x;
        canvas.drawText("100", x-20, 100+this.height/3-500, paint);
        canvas.drawLine(x + 100, 100+this.height/3-500, this.width-60, 100+this.height/3-500, paint);
        canvas.drawText("90", x, 100+this.height/3-400, paint);
        canvas.drawLine(x + 100, 100+this.height/3-400, this.width-60, 100+this.height/3-400, paint);
        canvas.drawText("80", x, 100+this.height/3-300, paint);
        canvas.drawLine(x + 100, 100+this.height/3-300, this.width-60, 100+this.height/3-300, paint);
        canvas.drawText("70", x, 100+this.height/3-200, paint);
        canvas.drawLine(x + 100, 100+this.height/3-200, this.width-60, 100+this.height/3-200, paint);
        canvas.drawText("60", x, 100+this.height/3-100, paint);
        canvas.drawLine(x + 100, 100+this.height/3-100, this.width-60, 100+this.height/3-100, paint);
        canvas.drawText("50", x, 100+this.height/3, paint);
        canvas.drawLine(x + 100, 100+this.height/3, this.width-60, 100+this.height/3, paint);
        canvas.drawText("40", x, 100+this.height/3+100, paint);
        canvas.drawLine(x + 100, 100+this.height/3+100, this.width-60, 100+this.height/3+100, paint);
        canvas.drawText("30", x, 100+this.height/3+200, paint);
        canvas.drawLine(x + 100, 100+this.height/3+200, this.width-60, 100+this.height/3+200, paint);
        canvas.drawText("20", x, 100+this.height/3+300, paint);
        canvas.drawLine(x + 100, 100+this.height/3+300, this.width-60, 100+this.height/3+300, paint);
        canvas.drawText("10", x, 100+this.height/3+400, paint);
        canvas.drawLine(x + 100, 100+this.height/3+400, this.width-60, 100+this.height/3+400, paint);
        canvas.drawText("0", x+20, 100+this.height/3+500, paint);
        canvas.drawLine(x + 100, 100+this.height/3+500, this.width-60, 100+this.height/3+500, paint);
    }

    public void drawJournals(Canvas canvas, int spacing, ArrayList<Journal> journalList) {
        int x = spacing+100;
        for (int i=0; i<journalList.size()-1; i++) {
            int mood = journalList.get(i).getMoodLevel();
            int mood2 = journalList.get(i+1).getMoodLevel();
            int y1;
            int y2;
            if (mood >= 50) {
                y1 = (this.height / 3) - ((mood-50)*10);
            } else {
                y1 = (this.height / 3) + (50-mood)*10;
            }
            if (mood2 >= 50) {
                y2 = (this.height / 3) - ((mood2-50)*10);
            } else{
                y2 = (this.height / 3) + (50-mood2)*10;
            }
            canvas.drawLine(x, 100+y1, x+spacing, 100+y2, linePaint);
            x += spacing;
        }
    }
}