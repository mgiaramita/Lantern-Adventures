import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;

public class Game {
	//class initializes the level/map and handles player keyboard inputs
	private static Map level;
	public static void startGame(Screen screen){
		//Load level
		level = new Map();
		
		//Start the enemy threads and pass them the map to manipulate
		int num = level.getNumEnemy();
		Enemy eList[] = new Enemy[num];
		for(int i = 0; i < num; i++){
			int x = level.enemyPos[i][0];
			int y = level.enemyPos[i][1];
			eList[i] = new Enemy(x, y, level, screen);
			eList[i].start();
		}
		
		level.PrintMap(screen);
		
		//Keep reading input until user quits the game
		boolean stop = false;
		while(!stop){
			Key key = screen.readInput();
			while(key == null){
				key = screen.readInput();
			}
			//Move around with arrow keys in normal map view escape closes the application
			switch(key.getKind()){
				case Escape:
					stop = true;
					break;
				case ArrowRight:
					if(validMove("right")){
						level.movePlayerRight();
					}
					level.PrintMap(screen);
					break;
					
				case ArrowLeft:
					if(validMove("left")){
						level.movePlayerLeft();
					}
					level.PrintMap(screen);
					break;
					
				case ArrowDown:
					if(validMove("down")){
						level.movePlayerDown();
					}
					level.PrintMap(screen);
					break;
					
				case ArrowUp:
					if(validMove("up")){
						level.movePlayerUp();
					}
					level.PrintMap(screen);
					break;
			}
			
			for(Enemy e: eList){
				//after a player moves all the enemies try to attack
				//if the player is on the same space as any of them the player will be hurt
				e.attack();
			}
			//After the attacks check the player to see if the game is over
			if(level.getPlayerHealth() <= 0){
				stop = true;
			}
		}
		
		//stop all enemy threads so that final screen can be shown
		for(Enemy e: eList){
			e.interrupt();
		}
		//wait to guarantee that all threads have stopped before printing death screen
		try { Thread.sleep(200);
		} catch (InterruptedException e) {}
		
	}
	
	//Checks to see if a given movement can be made by the player
	//No walking through walls and no going out of bounds
	public static boolean validMove(String move){
		int posX = level.getPlayerX();
		int posY = level.getPlayerY();
		int maxX = level.getMaxX();
		int maxY = level.getMaxY();
		
		if(move.equals("right")){
			if(posX < maxX && !level.getStringAt(posX + 1, posY).equals("#")){
				return true;
			}
			return false;
		}
		else if(move.equals("left")){
			if(posX > 0 && !level.getStringAt(posX - 1, posY).equals("#")){
				return true;
			}
			return false;
		}
		else if(move.equals("down")){
			if(posY < maxY && !level.getStringAt(posX, posY + 1).equals("#")){
				return true;
			}
			return false;
		}
		else{//move.equals("up")
			if(posY > 0 && !level.getStringAt(posX, posY - 1).equals("#")){
				return true;
			}
			return false;
		}
	}
}
