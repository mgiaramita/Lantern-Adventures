import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

public class Map {
	private String map[][];
	private int maxX;
	private int maxY;
	private int numEnemy;
	
	private Player p1;
	//Array of enemies
	int enemyPos[][];
	
	//Default constructor, Makes level 1
	//Hard coded level built into game
	public Map(){
		maxX = 50;
		maxY = 20;
		map = new String[50][20];
		
		numEnemy = 1;
		enemyPos = new int[1][2];
		enemyPos[0][0] = 48;
		enemyPos[0][1] = 18;
		
		p1 = new Player(1, 1);
		
		String row0 = "##################################################"; String row10 = "#            #      # # #   #     #  ### #   ### #";
		String row1 = "#@    #    #     #   #          #                #"; String row11 = "####### # ####      # #     #     # ##   # # ### #";
		String row2 = "#   # # ## # ##### # # ######## # # #### ####### #"; String row12 = "#       # ######### # ######### ### #  #   # ### #";
		String row3 = "# ###   ##   #   # # # ######## # #      #       #"; String row13 = "# ####### ####    # #       ### ### # #### # ### #";
		String row4 = "#     ###### #     # #        # # #    # #     # #"; String row14 = "#      #  #### ## ### #     ### ###   #### #   # #";
		String row5 = "# ### ######     ### ### #### # # #### # #     # #"; String row15 = "# ###### #####      # #     ### ### # #### ### # #";
		String row6 = "# ### #    # #   #   #      # #      #   ### ### #"; String row16 = "# #    # #####      # #     ### ### #        # # #";
		String row7 = "# ###      # ##### # #      # #### # # ###   #   #"; String row17 = "# #### # ######## ### ###   #     # ######## # # #";
		String row8 = "# ### #    #       #   ###### #    # # #   ### # #"; String row18 = "#                 ###                        #  E#";
		String row9 = "# ### ###### ## #####   #     ######   # ###   # #"; String row19 = "##################################################"; 
		for(int i = 0; i < 50; i++){
			map[i][0] = String.valueOf(row0.charAt(i)); map[i][10] = String.valueOf(row10.charAt(i));
			map[i][1] = String.valueOf(row1.charAt(i)); map[i][11] = String.valueOf(row11.charAt(i));
			map[i][2] = String.valueOf(row2.charAt(i)); map[i][12] = String.valueOf(row12.charAt(i));
			map[i][3] = String.valueOf(row3.charAt(i)); map[i][13] = String.valueOf(row13.charAt(i));
			map[i][4] = String.valueOf(row4.charAt(i)); map[i][14] = String.valueOf(row14.charAt(i));
			map[i][5] = String.valueOf(row5.charAt(i)); map[i][15] = String.valueOf(row15.charAt(i));
			map[i][6] = String.valueOf(row6.charAt(i)); map[i][16] = String.valueOf(row16.charAt(i));
			map[i][7] = String.valueOf(row7.charAt(i)); map[i][17] = String.valueOf(row17.charAt(i));
			map[i][8] = String.valueOf(row8.charAt(i)); map[i][18] = String.valueOf(row18.charAt(i));
			map[i][9] = String.valueOf(row9.charAt(i)); map[i][19] = String.valueOf(row19.charAt(i));
		}
	}
	
	//Allows creation of multiple levels, specify with level number > 1
	//Will read custom levels from text file
	public Map(int levelNo){
		//TODO
	}
	
	//Print characters from map array to terminal using screen
	public void PrintMap(Screen screen){
		int pX = p1.getX();
		int pY = p1.getY();
		
		screen.clear();
		
		//DONT ACCESS ARRAY AT [i][j] its backwards do [j][i]
		for(int i = 0; i < maxY; i++){
			for(int j = 0; j < maxX; j++){
				//Print player character yellow
				if(map[j][i].equals("@")){ 
					screen.putString(j + j, i, map[j][i], Terminal.Color.YELLOW, Terminal.Color.BLACK);
					screen.putString(j + j + 1, i, map[j][i], Terminal.Color.YELLOW, Terminal.Color.BLACK);
				} 
				else{
					if(Math.abs(j - pX) < 6 && Math.abs(i - pY) < 6){
						//Print enemies red EE
						if(map[j][i].equals("E")){
							screen.putString(j + j, i, map[j][i], Terminal.Color.RED, Terminal.Color.BLACK);
							screen.putString(j + j + 1, i, map[j][i], Terminal.Color.RED, Terminal.Color.BLACK);
						}
						//Print walls white
						else{
							screen.putString(j + j, i, map[j][i], Terminal.Color.WHITE, Terminal.Color.BLACK);
							screen.putString(j + j + 1, i, map[j][i], Terminal.Color.WHITE, Terminal.Color.BLACK);
						}
					}
					//Only show blocks that are within 5 space of the character. Simulates using a lantern in a dark room (CYAN = dark, WHITE = illuminated)
					else{
						screen.putString(j + j, i, "#", Terminal.Color.CYAN, Terminal.Color.BLACK);
						screen.putString(j + j + 1, i, "#", Terminal.Color.CYAN, Terminal.Color.BLACK);
					}
				}
			}
			//The double print statements are used to make the spacing between the x and y-axis even
		}
		printPlayerHUD(screen, 100, 30, 10);//Should be changed to be dynamic
		screen.refresh();
	}
	
	//The max size that the terminal can display
	public int getMaxX(){
		return maxX;
	}
	public int getMaxY(){
		return maxY;
	}
	
	//movePlayerXXXXX() move player +- 1 space in the desired direction
	//updates the data in the player class and on the map
	public void movePlayerRight(){
		int pX = p1.getX();
		int pY = p1.getY();
		p1.setPosX(pX + 1);
		map[pX][pY] = " ";
		map[pX + 1][pY] = "@";
	}
	public void movePlayerLeft(){
		int pX = p1.getX();
		int pY = p1.getY();
		p1.setPosX(pX - 1);
		map[pX][pY] = " ";
		map[pX - 1][pY] = "@";
	}
	public void movePlayerDown(){
		int pX = p1.getX();
		int pY = p1.getY();
		p1.setPosy(pY + 1);
		map[pX][pY] = " ";
		map[pX][pY + 1] = "@";
	}
	public void movePlayerUp(){
		int pX = p1.getX();
		int pY = p1.getY();
		p1.setPosy(pY - 1);
		map[pX][pY] = " ";
		map[pX][pY - 1] = "@";
	}
	
	//Current player x and y coordinate
	public int getPlayerX(){
		return p1.getX();
	}
	public int getPlayerY(){
		return p1.getY();
	}
	
	//String held in the map array at a given coordinate pair
	public String getStringAt(int x, int y){
		return map[x][y];
	}
	
	//Creates the HUD that keeps the user updated on the player's data
	//screenX is the width of the terminal and screenY is the height
	//leftUnderMap is the height that is available under the map after it is printed
	public void printPlayerHUD(Screen screen, int screenX, int screenY, int leftUnderMap){
		for(int i = 0; i < screenX; i++){
			//Print bottom line
			screen.putString(i, screenY - 1, "#", Terminal.Color.WHITE, Terminal.Color.BLACK);
			screen.putString(i, screenY - leftUnderMap, "#", Terminal.Color.WHITE, Terminal.Color.BLACK);
		}
		for(int i = 1; i <= leftUnderMap; i++){
			//Print side lines and divider
			screen.putString(0, screenY - i, "#", Terminal.Color.WHITE, Terminal.Color.BLACK);
			screen.putString(screenX - 1, screenY - i, "#", Terminal.Color.WHITE, Terminal.Color.BLACK);
			screen.putString(screenX / 5, screenY - i, "#", Terminal.Color.WHITE, Terminal.Color.BLACK);
		}
		for(int i = 3; i < (screenX -(screenX / 5) - 4); i++){
			//Print health bar container
			//its a mess, make better later?
			//depends it I lock the terminal size or allow user to specify its size
			screen.putString((screenX / 5) + i, (screenY - (leftUnderMap / 2)) + 1, "#", Terminal.Color.WHITE, Terminal.Color.BLACK);
			screen.putString((screenX / 5) + i, (screenY - (leftUnderMap / 2)) - 1, "#", Terminal.Color.WHITE, Terminal.Color.BLACK);
			screen.putString((screenX / 5) + 3, (screenY - (leftUnderMap / 2)), "#", Terminal.Color.WHITE, Terminal.Color.BLACK);
			screen.putString((screenX / 5) + (screenX -(screenX / 5) - 5), (screenY - (leftUnderMap / 2)), "#", Terminal.Color.WHITE, Terminal.Color.BLACK);
			screen.putString((screenX / 5) + 3, (screenY - (leftUnderMap / 2)) - 2, "Health", Terminal.Color.WHITE, Terminal.Color.BLACK);
		}
		double ratio = ((double)p1.getHealth()) / 100;
		for(int i = 4; i < ((screenX -(screenX / 5) - 5) * ratio) ; i++){
			//Print health colored bar
			screen.putString((screenX / 5) + i, (screenY - (leftUnderMap / 2)), "#", Terminal.Color.RED, Terminal.Color.BLACK);
		}
	}
	
	//returns the number of enemies that the map contains
	public int getNumEnemy(){
		return numEnemy;
	}
	
	//Set the string held at a given map coordinate
	public void setString(String set, int x, int y){
		map[x][y] = set;
	}
}
