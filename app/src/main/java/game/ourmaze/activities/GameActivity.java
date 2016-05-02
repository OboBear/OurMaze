package game.ourmaze.activities;

import game.ourmaze.Data;
import game.ourmaze.Function;
import game.ourmaze.views.GameView;
import game.ourmaze.Init;
import game.ourmaze.ManClass;
import game.ourmaze.ManClass.Man;
import game.ourmaze.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends Activity {
    /////////////////////////
    public static int direction;
    public static GameView gameView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HiddenMenu();
        Data.maze_a = Data.maze_size[0][0];
        Data.maze_b = Data.maze_size[0][1];
        Init.init_data();
        init();
        setContentView(R.layout.gameactive);
        findViews();
        new monster_move(1000).start();
        new Invalidate(5).start();

    }

    ////
    //�����»��˵�
    private void HiddenMenu() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    private void findViews() {
        gameView = (GameView) findViewById(R.id.gameview);
    }

    private final int MENU_SHOP = Menu.FIRST;
    private final int MENU_SAVE = Menu.FIRST + 1;
    private final int MENU_RETURN = Menu.FIRST + 2;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.add(0, MENU_SHOP, 0, R.string.shop_menu);
        menu.add(0, MENU_SAVE, 0, R.string.save_menu);
        menu.add(0, MENU_RETURN, 0, R.string.return_menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        switch (item.getItemId()) {
            case MENU_SHOP:
                builder.setTitle("��ʾ��").setMessage("������δ���ţ������ڴ�").setPositiveButton("ȷ��", null).show();
                break;
            case MENU_SAVE:
                builder.setTitle("��ʾ��").setMessage("������δ���ţ������ڴ�").setPositiveButton("ȷ��", null).show();
                break;
            case MENU_RETURN:

                new AlertDialog.Builder(this).setTitle("").setMessage("��ȷ��Ҫ�˳���Ϸ��").setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        setResult(RESULT_OK);//ȷ����ť�¼�
                        Data.init();
                        ManClass.man.level = 0;
                        ManClass.man = new Man(Data.maze_start[ManClass.man.level][0], Data.maze_start[ManClass.man.level][1]);
                        Data.deep_word = 20;
                        GameActivity.this.finish();
                    }
                }).setNegativeButton("ȡ��", null).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    ///////���ؼ�
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // ����Ƿ��ؼ�,ֱ�ӷ��ص�����
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {

            new AlertDialog.Builder(this).setTitle("").setMessage("��ȷ��Ҫ�˳���Ϸ��").setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    setResult(RESULT_OK);//ȷ����ť�¼�
                    Data.init();
                    ManClass.man.level = 0;
                    ManClass.man = new Man(Data.maze_start[ManClass.man.level][0], Data.maze_start[ManClass.man.level][1]);
                    Data.deep_word = 20;
                    GameActivity.this.finish();
                }
            }).setNegativeButton("ȡ��", null).show();
            //	GameActivity.this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }


    //////////////�߳�
    class gameThread implements Runnable {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            //��ǰ�߳������е�ʱ�򣬾�����Ϊÿ��0.1�������̶߳����������һ���̡߳�
            while (!Thread.currentThread().interrupted()) {
                gameView.postInvalidate();//ÿ��ִ���߳�ʱ��ˢ��һ��GameView�������档
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    ////////
    private void init()//�������
    {

        //   Init.init_data();

        Data.num = 0;
        Data.choose_num = 0;
        Data.unit_l = (int) (Data.scr_width / 10); // the size of bar ( pixel )
        Data.maze_l = Data.maze_a * Data.unit_l;
        Data.maze_h = Data.maze_b * Data.unit_l;   // the size of maze ( pixel )


    }


    class buttontime extends Thread {
        private int sleep_time;

        public buttontime(int st) {
            sleep_time = st;
        }

        public void run() {
            while (true) {

                try {
                    sleep(sleep_time);
                    Data.button_man_move = true;

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    }

    class monster_move extends Thread {
        private int sleep_time;

        public monster_move(int st) {
            sleep_time = st;
        }

        public void run() {
            while (true) {


                try {
                    sleep(sleep_time / 6);
                    Data.monster_move = 0;
                    //	gameView.postInvalidate();
                    sleep(sleep_time / 6);
                    Data.monster_move = 1;
                    //	gameView.postInvalidate();
                    sleep(sleep_time / 6);
                    Data.monster_move = 0;
                    sleep(sleep_time / 6);
                    //	gameView.postInvalidate();
                    sleep(sleep_time);
                    Data.monster_move = 1;
                    //	gameView.postInvalidate();

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    }

    class Invalidate extends Thread  //�Զ�ˢ�½���
    {
        private int sleep_time;

        public Invalidate(int st) {
            sleep_time = st;
        }

        public void run() {
            int a = 0;
            while (true) {


                try {

                    if (a == 0 && Data.pass) {
                        a++;
                        Function.pass();
                    }
                    if (Data.pass == false) a = 0;
                    //	if()
                    if (ManClass.man.blood == 0)  //����Ѫ��Ϊ0
                    {
                        if (ManClass.man.man_tool[4] == 0) {
                            Data.death_flag = true;
                            Data.stop_event = false;

                            sleep(4000);
                            Data.death_flag = false;
                            Data.stop_event = true;
                            Data.init();
                            ManClass.man.level = 0;
                            Data.stop_event = true;
                            GameActivity.this.finish();

                        } else {
                            while (ManClass.man.blood <= 20) {
                                sleep(10);
                                gameView.postInvalidate();
                                ManClass.man.blood += 1;

                            }
                            ManClass.man.man_tool[4]--;
                            if (ManClass.man.man_tool[4] <= 0) ManClass.man.man_tool[4] = 0;
                        }
                    }


                    if (ManClass.man.level == 10)  //����Ѫ��Ϊ0
                    {

                        Data.AK = true;
                        Data.stop_event = false;
                        sleep(4000);
                        Data.AK = false;
                        Data.stop_event = true;
                        Data.init();
                        ManClass.man.level = 0;
                        Data.stop_event = true;
                        GameActivity.this.finish();

                    }

                    if (Data.maze[ManClass.man.x][ManClass.man.y] == 4 && ManClass.man.man_tool[0] > 0)
                        Data.flag_arrive = true;
                    else Data.flag_arrive = false;
                    sleep(sleep_time); //ˢ������
                    gameView.postInvalidate();

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    }


}
