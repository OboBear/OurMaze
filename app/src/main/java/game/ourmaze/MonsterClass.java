package game.ourmaze;

public class MonsterClass {
    static class Monster {
        public int x, y;
        public int _toolid;
        public int wisedom, blood, defence, beat; // property

        public Monster(int sx, int sy, int mon_id) {
            x = sx;
            y = sy;
            wisedom = Data.mon_pro[mon_id][0] * (ManClass.man.level + 1);
            blood = Data.mon_pro[mon_id][1] * (ManClass.man.level + 1);
            defence = Data.mon_pro[mon_id][2] * (ManClass.man.level + 1);
            beat = Data.mon_pro[mon_id][3] * (ManClass.man.level + 1);
            if (wisedom > Data.Wisedom) wisedom = Data.Wisedom;
            if (blood > Data.Blood) blood = Data.Blood;
            if (defence > Data.Defence) defence = Data.Defence;
            if (beat > Data.Beat) beat = Data.Beat;
            int tool_id_;
            if (mon_id == 0) tool_id_ = 0;
            else {
                tool_id_ = Init.createRandom(2, 9);
                while (tool_id_ == 2 || tool_id_ == 7)
                    tool_id_ = Init.createRandom(2, 9);
            }
            _toolid = tool_id_;
        }
    }

    ;
    public static Monster monster[] = new Monster[16];
}