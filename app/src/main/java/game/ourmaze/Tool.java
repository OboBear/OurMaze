package game.ourmaze;

import android.graphics.Color;

public class Tool {
    public static void get_tool(int tool_id) {
        ManClass.man.man_tool[tool_id]++;
    }

    public static boolean use_tool(int tool_id) {

        switch (Data.tools[tool_id]) {
            case 'k':
                if (ManClass.man.x != Data.maze_end[ManClass.man.level][0] || ManClass.man.y != Data.maze_end[ManClass.man.level][1]) {
                    return false;
                } else {
                    Data.pass = true;
                    ManClass.man.man_tool[tool_id]--;
                    return false;
                }

            case 'm':
                if (Data.using_tool) Data.using_tool = false;
                else Data.using_tool = true;
                Data.tool_choose = 'm';


                break;
            case 'j':
                return mirror();
            case 'f': {
                ManClass.man.man_tool[tool_id]--;
                return dis_fog();
            }
            case 'r':
                if (ManClass.man.blood > 0) {
                    return false;
                } else {
                    while (ManClass.man.blood < Data.blood_t && ManClass.man.blood < Data.Blood) {
                        ManClass.man.blood += 10;
                    }
                    ManClass.man.man_tool[tool_id]--;
                }
                break;
            case 'p':
                if (ManClass.man.blood == Data.Blood) {
                    return false;
                } else {
                    int t = ManClass.man.blood + 20;
                    while (ManClass.man.blood < t && ManClass.man.blood < Data.Blood) {
                        ManClass.man.blood++;
                    }
                    ManClass.man.man_tool[tool_id]--;
                }
                break;
            case 't':
                escape();
                ManClass.man.man_tool[tool_id]--;
                break;
            case 'a':
                paint_road(Data.maze_size[ManClass.man.level][0], Data.maze_size[ManClass.man.level][1]);
                break;
            case 'g':
                if (ManClass.man.beat == Data.Beat) {
                    return false;
                } else {
                    int t = ManClass.man.beat + 3;
                    while (ManClass.man.beat < t && ManClass.man.beat < Data.Beat) {
                        ManClass.man.beat++;
                    }
                    ManClass.man.man_tool[tool_id]--;
                }
                break;
            case 'd':
                if (ManClass.man.defence == Data.Defence) {
                    return false;
                } else {
                    int t = ManClass.man.defence + 2;
                    while (ManClass.man.defence < t && ManClass.man.defence < Data.Defence) {
                        ManClass.man.defence++;
                    }
                    ManClass.man.man_tool[tool_id]--;
                }
                break;
            default:
                return true;
        }
        return true;
    }

    public static void paint_map() {

        int size = Data.scr_height / 35;
        int place_y = (Data.scr_height - size * Data.maze_b) / 2;
        int place_x = (Data.scr_width - size * Data.maze_a) / 2;


        int i, j, ii, jj;
        j = 1;
        ii = 2 * Data.maze_a + 1;
        jj = 2 * Data.maze_b + 1;
        for (; j <= Data.maze_b; j++) {
            i = 1;
            for (; i <= Data.maze_a; i++) {

                if (Data.maze[i][j] == 0) Data.paint.setColor(Color.GRAY);

                if (Data.maze[i][j] == 1) Data.paint.setColor(Color.WHITE);
                if (Data.fog[i][j] == 1 && Data.maze[i][j] == 3) Data.paint.setColor(Color.LTGRAY);
                if (Data.fog[i][j] == 1 && Data.maze[i][j] == 4) Data.paint.setColor(Color.BLUE);
                if (Data.maze_start[ManClass.man.level][0] == i && Data.maze_start[ManClass.man.level][1] == j)
                    Data.paint.setColor(Color.RED);
                if (Data.maze_end[ManClass.man.level][0] == i && Data.maze_end[ManClass.man.level][1] == j)
                    Data.paint.setColor(Color.BLACK);
                Init.bar(place_x + size * (i - 1),
                        place_y + size * (j - 1),
                        place_x + size * i,
                        place_y + size * j);
            }
        }

    }

    // 鐓у闀�
    public static boolean mirror() {
        /*while(true){
            if(_kbhit()) return false;
			if(MouseHit()){
				MOUSEMSG Mouse;
		        Mouse=GetMouseMsg();
		        switch(Mouse.uMsg){
			        case WM_LBUTTONDOWN:
						for(int i=0;i<=5;i++){
							int tl=(monster[i].x-1)*unit_l+place_x,tr=monster[i].x*unit_l+place_x,tu=(monster[i].y-1)*unit_l+place_y,td=monster[i].y*unit_l+place_y;
							if(Mouse.x>=tl && Mouse.x<=tr && Mouse.y>=tu && Mouse.y<=td && mon[monster[i].x][monster[i].y]!=-1 && fog[monster[i].x][monster[i].y]){
								paint_monpro(i);
								return true;
							}
						}
						for(int i=0;i<10;i++){
							// 濡傛灉鐐瑰嚮浜嗙i涓亾鍏�							if(Mouse.x>=l_tool[i] && Mouse.x<=l_tool[i]+tools_l && Mouse.y>=h_tool[i] && Mouse.y<=h_tool[i]+tools_l && man.man_tool[i] && Mouse.x>=left_x && Mouse.x<=right_x && Mouse.y<=bottom && Mouse.y>=up_y){
								// 濡傛灉璇ラ亾鍏峰彲浠ョ敤
								if(i!=2 && use_tool(i)){
									sub_tool(i);
									return false;
								}
							}
						}
				}
			}
		}*/
        return false;
    }

    public static boolean dis_fog() {
        for (int i = 0; i < Data.maxmaze; i++)
            for (int j = 0; j < Data.maxmaze; j++)
                Data.fog[i][j] = 1;
        return true;
    }

    public static void escape() {
        while (true) {
            int x = Init.createRandom(1, Data.maze_a);
            int y = Init.createRandom(1, Data.maze_b);
            if (Data.mon[x][y] == -1 && Data.maze[x][y] == 1 && ManClass.man.x != x && ManClass.man.y != y) {
                ManClass.man.x = x;
                ManClass.man.y = y;
                Init.disfog(x, y, ManClass.man.view);
                return;
            }
        }
    }

    // 地图绘制算法
    public static void paint_map(int map_size_x, int map_size_y) {
        int unit_t = 10;
        for (int j = 1; j <= (map_size_y + 1); j++) {
            for (int i = 1; i <= (map_size_x + 1); i++) {
                if (Data.maze[i][j] == 0 && Data.fog[i][j] == 1) {
                    Data.paint.setColor(Color.BLUE);
                    Init.bar(unit_t * (i - 1), unit_t * (j - 1), unit_t * i, unit_t * j);
                }
                if (Data.maze[i][j] == 1 && Data.fog[i][j] == 1) {
                    Data.paint.setColor(Color.DKGRAY);
                    Init.bar(unit_t * (i - 1), unit_t * (j - 1), unit_t * i, unit_t * j);
                }
                if (Data.maze[i][j] == 3 && Data.fog[i][j] == 1) {
                    Data.paint.setColor(Color.RED);
                    Init.bar(unit_t * (i - 1), unit_t * (j - 1), unit_t * i, unit_t * j);
                }
                if (Data.maze[i][j] == 4 && Data.fog[i][j] == 1) {
                    Data.paint.setColor(Color.YELLOW);
                    Init.bar(unit_t * (i - 1), unit_t * (j - 1), unit_t * i, unit_t * j);
                }
                if (ManClass.man.x == i && ManClass.man.y == j) {
                    Data.paint.setColor(Color.CYAN);
                    Init.bar(unit_t * (i - 1), unit_t * (j - 1), unit_t * i, unit_t * j);
                }
            }
        }
    }

    // 答案路绘制算法
    public static void paint_road(int map_size_x, int map_size_y) {
        int unit_t = 10;
        for (int j = 1; j <= (map_size_y + 1); j++) {
            for (int i = 1; i <= (map_size_x + 1); i++) {
                if (Data.vst[i][j] == 1) {
                    Data.paint.setColor(Color.BLUE);
                    Init.bar(unit_t * (i - 1), unit_t * (j - 1), unit_t * i, unit_t * j);
                }
                if (Data.maze[i][j] == 3) {
                    Data.paint.setColor(Color.RED);
                    Init.bar(unit_t * (i - 1), unit_t * (j - 1), unit_t * i, unit_t * j);
                }
                if (Data.maze[i][j] == 4) {
                    Data.paint.setColor(Color.YELLOW);
                    Init.bar(unit_t * (i - 1), unit_t * (j - 1), unit_t * i, unit_t * j);
                }
                if (ManClass.man.x == i && ManClass.man.y == j) {
                    Data.paint.setColor(Color.CYAN);
                    Init.bar(unit_t * (i - 1), unit_t * (j - 1), unit_t * i, unit_t * j);
                }
            }
        }
    }
}
