package game.ourmaze.activities;

import game.ourmaze.views.BeginView;
import game.ourmaze.Data;
import game.ourmaze.Init;
import game.ourmaze.ManClass;
import game.ourmaze.ManClass.Man;
import game.ourmaze.R;

import android.os.Bundle;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ViewFlipper;

import android.content.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

public class BeginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HiddenMenu();
        init();
        setContentView(R.layout.beginview);
        new choose_button().start();
    }


    private void HiddenMenu() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    public ViewFlipper viewFlipper;
    boolean flag = true;

    class sleep extends Thread {
        private int sleep_time;

        public sleep(int st) {
            sleep_time = st;
        }

        public void run() {

            try {
                sleep(sleep_time);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            flag = true;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            event.startTracking();
            finish();
            Data.choose_num = 0;
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void init() {
        Data.init();
        Init.init_data();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Data.scr_height = dm.heightPixels;
        Data.scr_width = dm.widthPixels;
        Data.place_y = 0;
        Data.place_x = 0;
        ManClass.man = new Man(2, 2);
        Data.num = 0;
        Data.choose_num = 0;
        Data.start_move_or_not = false;
        Data.direct = 3;
        ////////////
        ManClass.man = new Man(2, 2);
        Data.num = 0;
        Data.choose_num = 0;
        Data.unit_l = (Data.scr_width / 10); // the size of bar ( pixel )
        Data.maze_l = Data.maze_a * Data.unit_l;
        Data.maze_h = Data.maze_b * Data.unit_l;   // the size of maze ( pixel )

        Data.bar_x = Data.scr_width * 3 / 4;
        Data.pro_l = Data.scr_width / 9 * 2;
        Data.pro_h = Data.scr_height / 40;
        Data.y_toolset = 0;
        Data.x_circlepoint = Data.scr_width - Data.scr_height / 4;
        Data.y_circlepoint = Data.scr_height * 3 / 4;
        Data.x_button = Data.x_circlepoint;
        Data.y_button = Data.y_circlepoint;
        Data.r_button = Data.unit_l * 5 / 4;
        ///man move state;
        Data.x_man_move_time_state = 0;
        Data.y_man_move_time_state = 0;

        ////////////////////////
        //load picture
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.floor));
        Data.width = Data.bitmap.getWidth();
        Data.height = Data.bitmap.getHeight();
        Data.scaleWidth = ((float) Data.unit_l) / Data.width;
        Data.scaleHeight = ((float) Data.unit_l) / Data.height;
        Matrix matrix = new Matrix();
        matrix.postScale(Data.scaleWidth, Data.scaleHeight);
        Data.matrix = matrix;
        Data.floor = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.wall));
        Data.wall = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.door));
        Data.door = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.start));
        Data.start = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.monst1));
        Data.monst1 = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.monst2));
        Data.monst2 = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        //////load man

        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.forw0));
        Data.forw[0] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.forw[2] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.forw[4] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.forw1));
        Data.forw[1] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.forw2));
        Data.forw[3] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);

        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.up0));
        Data.up[0] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.up[2] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.up[4] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.up1));
        Data.up[1] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.up2));
        Data.up[3] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);

        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.left0));
        Data.left[0] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.left[2] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.left[4] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.left1));
        Data.left[1] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.left2));
        Data.left[3] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);

        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.right0));
        Data.right[0] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.right[2] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.right[4] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.right1));
        Data.right[1] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.right2));
        Data.right[3] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);

        /////load tools picture
        Data.scaleWidth = ((float) Data.scr_width / 14) / Data.width;
        Data.scaleHeight = ((float) Data.scr_width / 14) / Data.height;
        matrix = new Matrix();
        matrix.postScale(Data.scaleWidth, Data.scaleHeight);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.k0));
        Data.tool0[0] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.m0));
        Data.tool0[1] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.j0));
        Data.tool0[2] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.f0));
        Data.tool0[3] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.r0));
        Data.tool0[4] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.p0));
        Data.tool0[5] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.t0));
        Data.tool0[6] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.a0));
        Data.tool0[7] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.g0));
        Data.tool0[8] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.d0));
        Data.tool0[9] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);

        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.k1));
        Data.tool1[0] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.m1));
        Data.tool1[1] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.j1));
        Data.tool1[2] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.f1));
        Data.tool1[3] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.r1));
        Data.tool1[4] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.p1));
        Data.tool1[5] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.t1));
        Data.tool1[6] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.a1));
        Data.tool1[7] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.g1));
        Data.tool1[8] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.d1));
        Data.tool1[9] = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);

        ////load tool box
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.toolbox));
        Data.width = Data.bitmap.getWidth();
        Data.height = Data.bitmap.getHeight();
        Data.scaleWidth = ((float) Data.scr_width / 12) / Data.width;
        Data.scaleHeight = ((float) Data.scr_height) / Data.height;
        matrix = new Matrix();
        matrix.postScale(Data.scaleWidth, Data.scaleHeight);
        Data.toolbox = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);

        ////////load buttonbox
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.circle));
        Data.width = Data.bitmap.getWidth();
        Data.height = Data.bitmap.getHeight();
        Data.scaleWidth = ((float) Data.scr_height / 2) / Data.width;
        Data.scaleHeight = ((float) Data.scr_height / 2) / Data.height;
        matrix = new Matrix();
        matrix.postScale(Data.scaleWidth, Data.scaleHeight);
        Data.circle = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);

        ////load button
        Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.button));
        Data.width = Data.bitmap.getWidth();
        Data.height = Data.bitmap.getHeight();
        Data.scaleWidth = ((float) Data.r_button) / Data.width;
        Data.scaleHeight = ((float) Data.r_button) / Data.height;
        matrix = new Matrix();
        matrix.postScale(Data.scaleWidth, Data.scaleHeight);
        Data.button = Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);

        //load death
      /*  Data.bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.death));
        Data.width = Data.bitmap.getWidth();
		Data.height = Data.bitmap.getHeight();
		    // �������ű���
		Data.scaleWidth = ((float) Data.scr_width/4) / Data.width;
		Data.scaleHeight = ((float) Data.scr_height/4) / Data.height;
		matrix = new Matrix();
	    matrix.postScale(Data.scaleWidth, Data.scaleHeight);
	    Data.death= Bitmap.createBitmap(Data.bitmap, 0, 0, Data.width, Data.height, matrix, true);*/


        /////
        //tool set
        for (int i = 0; i < 10; i++) {
            Data.l_tool[i] = Data.scr_width / 180;
        }
        for (int i = 0; i < 10; i++) {
            Data.h_tool[i] = Data.scr_height / 5 * i;
        }
    }


    private float x_for, y_for;
    private float x_n, y_n;
    private int buttonflag = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x_new = event.getX();
        float y_new = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x_for = x_new;
                break;

            case MotionEvent.ACTION_MOVE:
                x_for = x_n;
                x_n = x_new;
                break;
            case MotionEvent.ACTION_UP:
                if (x_for < x_n) {
                    if (flag)
                        if (Data.choose_num > 0) {
                            flag = false;
                            Data.choose_num--;
                            sleep sl = new sleep(700);
                            viewFlipper.setInAnimation(BeginActivity.this, R.anim.in_leftright);
                            viewFlipper.setOutAnimation(BeginActivity.this, R.anim.out_leftright);
                            viewFlipper.showPrevious();
                            sl.start();

                        } else {
                            flag = false;
                            Data.choose_num = 3;
                            sleep sl = new sleep(700);
                            viewFlipper.setInAnimation(BeginActivity.this, R.anim.in_leftright);
                            viewFlipper.setOutAnimation(BeginActivity.this, R.anim.out_leftright);
                            viewFlipper.showPrevious();
                            sl.start();

                        }
                } else {


                    if (flag)
                        if (Data.choose_num < 3) {
                            flag = false;
                            Data.choose_num++;
                            sleep sl = new sleep(700);
                            viewFlipper.setInAnimation(BeginActivity.this, R.anim.in_rightleft);
                            viewFlipper.setOutAnimation(BeginActivity.this, R.anim.out_rightleft);
                            viewFlipper.showNext();
                            sl.start();

                        } else {
                            flag = false;
                            Data.choose_num = 0;
                            sleep sl = new sleep(700);
                            viewFlipper.setInAnimation(BeginActivity.this, R.anim.in_rightleft);
                            viewFlipper.setOutAnimation(BeginActivity.this, R.anim.out_rightleft);
                            viewFlipper.showNext();
                            sl.start();


                        }
                }

                break;
        }
        return true;
    }


    public static int choose = 0;

    class choose_button extends Thread {

        public choose_button() {}

        public void run() {
            while (true) {
                try {
                    sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (choose == 1) {
                    choose = 0;
                    Intent intent = new Intent();
                    intent.setClass(BeginActivity.this, GameActivity.class);
                    startActivity(intent);
                }
                if (choose == 2) {
                    choose = 0;
                    BeginActivity.this.finish();
                }
            }
        }
    }
}
