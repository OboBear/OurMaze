package game.ourmaze;

public class ManClass {


	/*---------------------- Man-------------------------*/
	public static class Man {
		/* charcter */
		public final static int Man1=1201;
		public final static int Man2=1202;
		public final static int Man3=1203;
		/* members */
		public int x,y;  // coordination
		public int view,direct,win,level;
		public int character;
		public int wisedom,blood,defence,beat; // property
		public int man_tool[]=new int[15];  // the quantity of each tool	
		public Man(int sx,int sy) { 
			x=sx; y=sy;
		    view=3; direct=Data.man_front; win=0; level=0;
		    character=Man1;
		    wisedom=Data.wisedom_t; blood=Data.MaxBlood[level]; beat=Data.beat_t; defence=Data.defence_t;
		    Data.Blood=Data.MaxBlood[level]; 
			// the initial quantity of each tool is 0
			for(int i=0;i<15;i++){
				if(i==7|i==2)
				man_tool[i]=0;
				else man_tool[i]=1;
			}
		}
		public void load_character()
		{
			switch(character)
			{
			case Man1:
				break;
			case Man2:
				break;
			case Man3:
				break;
			}
		}
		public boolean move(int _direct){ 
			if(Data.maze[ x+Data.drct[_direct][0] ][ y+Data.drct[_direct][1] ]!=0){
				//clear_monpro();
				Data.num++;
				x+=Data.drct[_direct][0]; y+=Data.drct[_direct][1]; direct=_direct;// update x,y,direct
				Init.disfog(x,y,view);
				//Init.repaint(Data.maze_a,Data.maze_b,_direct,true); // repaint the maze
				if(Data.mon[x][y]!=-1){ // fight with monster
					//paint_monpro(mon[x][y]);
					Data.stop_event=true;
					Function.fight(Data.mon[x][y]);
				}

				return true;
			}
			return false;
		}
	};
	public static Man man=new Man(2,2);

}
