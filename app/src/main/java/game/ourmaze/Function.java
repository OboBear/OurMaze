package game.ourmaze;

import android.graphics.Color;

import game.ourmaze.activities.GameActivity;

public class Function {

    public static float distance(float x1, float y1, float x2, float y2) {
        double dis = 0;
        dis = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
        dis = Math.sqrt(dis);

        return (float) dis;
    }

    public static float fabs(float a, float b) {
        float c = 0;
        c = a - b;
        if (c < 0) c = -c;
        return c;
    }

    public static int fab(int a) {
        if (a > 0) return a;
        else return -a;
    }

    public static void newpoint(float x1, float y1, float x2, float y2, int dis) {
        if (x1 == x2) {
            if (y1 > y2) {
                Data.x_button = (int) x1;
                Data.y_button = (int) (y2 + dis);

            } else {
                Data.x_button = (int) x1;
                Data.y_button = (int) (y2 - dis);

            }
        } else if (y1 == y2) {
            if (x1 > x2) {
                Data.x_button = (int) (x2 + dis);
                Data.y_button = (int) y1;
            } else {
                Data.x_button = (int) (x2 - dis);
                Data.y_button = (int) y1;
            }
        } else {
            float l1;
            float p;
            l1 = distance(x1, y1, x2, y2);
            p = dis / l1;
            Data.x_button = (int) (x2 + (x1 - x2) * p);
            Data.y_button = (int) (y2 + (y1 - y2) * p);
        }
    }


    // fight
    public static boolean fight_flag = true;

    public static void fight(int mon_id) {
        int d_man = MonsterClass.monster[mon_id].beat - ManClass.man.defence;
        int d_mon = ManClass.man.beat - MonsterClass.monster[mon_id].defence;
        if (d_man < 0 && MonsterClass.monster[mon_id].beat < ManClass.man.defence / 2) d_man = 0;
        else if (d_man < 0) d_man = 1;
        if (d_mon < 0 && ManClass.man.beat < MonsterClass.monster[mon_id].defence / 2) d_mon = 0;
        else if (d_mon < 0) d_mon = 1;
        while (ManClass.man.blood != 0 && MonsterClass.monster[mon_id].blood != 0) {
            if (fight_flag)
                ManClass.man.blood -= d_man;
            MonsterClass.monster[mon_id].blood -= d_mon;
            if (ManClass.man.blood < 0) ManClass.man.blood = 0;
            if (MonsterClass.monster[mon_id].blood < 0) MonsterClass.monster[mon_id].blood = 0;
            fight_flag = false;
            new sleep(500, 2).start();

        }
        // 灏忔�琚墦璐�
        if (MonsterClass.monster[mon_id].blood == 0) {
            // 灏忔�娑堝け
            Data.mon[MonsterClass.monster[mon_id].x][MonsterClass.monster[mon_id].y] = -1;
            // 寰楀埌灏忔�鐨勯亾鍏�

            Tool.get_tool(MonsterClass.monster[mon_id]._toolid);
            ManClass.man.wisedom += 5;
        }
    }


    //////screen move
    public static void screen_move(int time) {
        //Data.x_man_move_time_state;
        //	Data.y_man_move_time_state;
        if (Data.place_y > 0) Data.place_y = 0;
        if (Data.place_x > 0) Data.place_x = 0;
        if (Data.place_x < -((Data.maze_a) * Data.unit_l - Data.scr_width))
            Data.place_x = -((Data.maze_a) * Data.unit_l - (Data.scr_width));
        if (Data.place_y < -((Data.maze_b) * Data.unit_l - Data.scr_height))
            Data.place_y = -((Data.maze_b) * Data.unit_l - Data.scr_height);


        ////man place
        if (Data.place_y < -(ManClass.man.y - 2) * Data.unit_l)
            Data.place_y = -(ManClass.man.y - 2) * Data.unit_l;
        if (Data.place_x < -(ManClass.man.x - 2) * Data.unit_l)
            Data.place_x = -(ManClass.man.x - 2) * Data.unit_l;
        if (Data.place_y > Data.scr_height - (ManClass.man.y + 1) * Data.unit_l)
            Data.place_y = Data.scr_height - (ManClass.man.y + 1) * Data.unit_l;
        if (Data.place_x > Data.scr_width - (ManClass.man.x + 1) * Data.unit_l)
            Data.place_x = Data.scr_width - (ManClass.man.x + 1) * Data.unit_l;

    }


    public static void pass() {

        Data.paint.setColor(Color.GRAY);
        Init.bar(Data.scr_width / 4, Data.scr_height / 4, Data.scr_width * 3 / 4, Data.scr_height * 3 / 4);
        Data.stop_event = false;
        new sleep(2000, 1).start();

        //GameActivity.gameView.postInvalidate();

    }


}

class sleep extends Thread {
    private int sleep_time;
    private int flag;

    public sleep(int st, int flag) {
        sleep_time = st;
        this.flag = flag;
    }

    public void run() {
        if (flag == 1) {
            try {
                sleep(sleep_time);
                Data.stop_event = true;
                ManClass.man.level++;
                Data.Blood = Data.MaxBlood[ManClass.man.level];
                Init.init_data();
                Data.num = 0;
                Data.choose_num = 0;
                Data.unit_l = (int) (Data.scr_width / 10); // the size of bar ( pixel )
                GameActivity.gameView.postInvalidate();


            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Data.using_tool = false;
            Data.stop_event = true;
            Data.pass = false;
            GameActivity.gameView.postInvalidate();
        } else {
            try {
                sleep(sleep_time);
                Data.stop_event = true;

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Function.fight_flag = true;
            GameActivity.gameView.postInvalidate();
        }

    }
}

