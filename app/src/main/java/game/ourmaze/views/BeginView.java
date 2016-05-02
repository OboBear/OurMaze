package game.ourmaze.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import game.ourmaze.Data;
import game.ourmaze.R;
import game.ourmaze.activities.BeginActivity;

public class BeginView extends View {

    private Bitmap maze;
    private Bitmap man;
    private Paint paint = new Paint();
    private Bitmap button_start1;
    private Bitmap button_start2;
    private Bitmap button_quit1;
    private Bitmap button_quit2;
    private Bitmap cover;

    private void initDatas() {

        Matrix matrix1 = new Matrix();
        Matrix matrix2 = new Matrix();
        Matrix matrix3 = new Matrix();
        Matrix matrix4 = new Matrix();
        int width = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.maze)).getWidth();
        int height = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.maze)).getHeight();
        float scaleWidth = ((float) Data.scr_width * 3 / 4) / width;
        float scaleHeight = ((float) Data.scr_height * 3 / 4) / height;
        matrix1.postScale(scaleWidth, scaleHeight);
        maze = Bitmap.createBitmap(BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.maze)), 0, 0, width, height, matrix1, true);
        width = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.man)).getWidth();
        height = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.man)).getHeight();
        scaleWidth = ((float) Data.scr_width / 4) / width;
        scaleHeight = ((float) Data.scr_height / 4 * 11 / 6) / height;
        matrix2.postScale(scaleWidth, scaleHeight);
        man = Bitmap.createBitmap(BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.man)), 0, 0, width, height, matrix2, true);

        ///////////////////////
        //button
        width = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.start1)).getWidth();
        height = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.start1)).getHeight();
        scaleWidth = ((float) Data.scr_width / 5) / width;
        scaleHeight = ((float) Data.scr_height / 8) / height;
        matrix3.postScale(scaleWidth, scaleHeight);
        button_start1 = Bitmap.createBitmap(BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.start1)), 0, 0, width, height, matrix3, true);
        button_start2 = Bitmap.createBitmap(BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.start2)), 0, 0, width, height, matrix3, true);
        button_quit1 = Bitmap.createBitmap(BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.quit1)), 0, 0, width, height, matrix3, true);
        button_quit2 = Bitmap.createBitmap(BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.quit2)), 0, 0, width, height, matrix3, true);
        width = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.cover)).getWidth();
        height = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.cover)).getHeight();
        scaleWidth = ((float) Data.scr_width) / width;
        scaleHeight = ((float) Data.scr_height) / height;
        matrix4.postScale(scaleWidth, scaleHeight);
        cover = Bitmap.createBitmap(BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.cover)), 0, 0, width, height, matrix4, true);
        new animation(10, 1).start();
        x1 = Data.scr_width / 12 / 2;
        y1 = Data.scr_height * 4 / 5;
        x2 = Data.scr_width / 8 * 6;
        y2 = Data.scr_height * 4 / 5;
        man_x = Data.scr_width / 3 * 10 / 9;
    }

    public BeginView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDatas();
    }

    public BeginView(Context context) {
        super(context);
        initDatas();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (key_choose)
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (x >= x1 && x <= x1 + Data.scr_width / 5 && y >= y1 && y <= y1 + Data.scr_height / 8) {
                        choose_1 = true;
                    }
                    if (x > x2 && x <= x2 + Data.scr_width / 5 && y >= y2 && y <= y2 + Data.scr_height / 8) {
                        choose_2 = true;
                    }
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    if (x >= x1 && x <= x1 + Data.scr_width / 5 && y >= y1 && y <= y1 + Data.scr_height / 8) {
                        if (choose_1 = true) {
                            new animation(10, 2).start();
                        }
                    }

                    if (x > x2 && x <= x2 + Data.scr_width / 5 && y >= y2 && y <= y2 + Data.scr_height / 8) {
                        if (choose_2 = true) BeginActivity.choose = 2;

                    }
                    choose_1 = false;
                    choose_2 = false;

                    invalidate();
                    break;
            }
        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawBitmap(maze, Data.scr_width / 8, Data.scr_height / 12, paint);
        canvas.drawBitmap(man, right_man, Data.scr_height / 2 * 23 / 24, paint);
        if (isManDown) {
            canvas.drawBitmap(man, man_x, down, paint);
        }

        canvas.drawBitmap(man, left_man, Data.scr_height / 2 * 23 / 24, paint);

        if (appear_button1 && choose_1) {
            canvas.drawBitmap(button_start2, x1, y1, paint);
        } else if (appear_button1) {
            canvas.drawBitmap(button_start1, x1, y1, paint);
        }
        if (appear_button2 && choose_2) {
            canvas.drawBitmap(button_quit2, x2, y2, paint);
        } else if (appear_button2) {
            canvas.drawBitmap(button_quit1, x2, y2, paint);
        }
        canvas.drawBitmap(cover, left_to_right, 0, paint);
    }

    private int down = 0;
    private int left_to_right = 0;

    private int left_man = - 400;
    private int right_man = Data.scr_width;
    private boolean isManDown = false;
    private boolean appear_button1 = false;
    private boolean appear_button2 = false;
    private boolean choose_1 = false;
    private boolean choose_2 = false;
    private boolean key_choose = false;
    private int x1, y1, x2, y2;
    private int man_x;

    class animation extends Thread {
        private int sleep_time;
        private int choose_animation;

        public animation(int st, int choose_anim) {
            sleep_time = st;
            this.choose_animation = choose_anim;
        }

        public void run() {
            if (choose_animation == 1) {
                try {

                    while (left_to_right <= Data.scr_width) {
                        sleep(sleep_time);
                        left_to_right += 10;
                        //	down+=10;
                        postInvalidate();
                    }
                    isManDown = true;
                    while (down < Data.scr_height / 2 * 23 / 24) {
                        sleep(sleep_time);
                        down += 10;
                        postInvalidate();
                    }
                    down = Data.scr_height / 2 * 23 / 24;
                    while (left_man < man_x - 50) {
                        sleep(sleep_time / 5);
                        left_man += 20;
                        postInvalidate();
                    }

                    while (right_man > man_x + 50) {
                        sleep(sleep_time / 5);
                        right_man -= 20;
                        postInvalidate();
                    }
                    while (right_man > man_x || left_man < man_x) {
                        sleep(sleep_time);
                        right_man -= 1;
                        left_man += 1;
                        if (right_man <= man_x) right_man = man_x;
                        if (left_man >= man_x) left_man = man_x;
                        postInvalidate();
                    }
                    sleep(sleep_time * 50);
                    appear_button1 = true;
                    postInvalidate();
                    sleep(sleep_time * 50);
                    appear_button2 = true;
                    postInvalidate();
                    key_choose = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Data.x_man_move_time_state = 0;
                Data.y_man_move_time_state = 0;
                Data.button_man_move = true;
            } else if (choose_animation == 2)
                try {
                    while (right_man < Data.scr_width || left_man > -400 || down > -400) {
                        sleep(sleep_time);
                        right_man += 20;
                        left_man -= 20;
                        down -= 20;
                        if (right_man >= Data.scr_width) right_man = Data.scr_width;
                        if (left_man <= -400) left_man = -400;
                        if (down <= -400) down = -400;
                        postInvalidate();
                    }
                    BeginActivity.choose = 1;
                    sleep(500);
                    right_man = man_x;
                    left_man = man_x;
                    down = Data.scr_height / 2 * 23 / 24;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }
}






