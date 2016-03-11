import com.googlecode.lanterna.screen.Screen;

public class Enemy extends Thread{
	private int posX;
	private int posY;
	private Map map;
	private Screen screen;
	private boolean interrupted;
	
	public Enemy(int x, int y, Map map, Screen sc){
		posX = x;
		posY = y;
		this.map = map;
		screen = sc;
		interrupted = false;
	}
	
	public void run(){
		map.PrintMap(screen);
		while(!interrupted){
			double rand = 0;
			
			//Enemies are threads to allow them to move around the map freely
			//Random number is generated every 200ms and the enemy tries to move in that direction
			try { Thread.sleep(200);
			} catch (InterruptedException e) {}
			
			rand = Math.random();
			if(rand > .75){
				moveUp();
			}
			else if(rand > .5){
				moveDown();
			}
			else if(rand > .25){
				moveLeft();
			}
			else{//rand >= 0
				moveRight();
			}
			map.PrintMap(screen);
		}
	}
	
	public void interrupt(){
		interrupted = true;
	}
	
	//The move methods check if the move is valid and they update the map and enemy location data
	//Player is hurt any time that they touch the enemy check this condition after move
	public void moveUp(){
		if(posY > 0 && !map.getStringAt(posX, posY - 1).equals("#")){
			map.setString(" ", posX, posY);
			posY -= 1;
			map.setString("E", posX, posY);
		}
		attack();
	}
	
	public void moveDown(){
		if(posY + 1 < map.getMaxY() && !map.getStringAt(posX, posY + 1).equals("#")){
			map.setString(" ", posX, posY);
			posY += 1;
			map.setString("E", posX, posY);
		}
		attack();
	}
	
	public void moveLeft(){
		if(posX > 0 && !map.getStringAt(posX - 1, posY).equals("#")){
			map.setString(" ", posX, posY);
			posX -= 1;
			map.setString("E", posX, posY);
		}
		attack();
	}
	
	public void moveRight(){
		if(posX + 1 < map.getMaxX() && !map.getStringAt(posX + 1, posY).equals("#")){
			map.setString(" ", posX, posY);
			posX += 1;
			map.setString("E", posX, posY);
		}
		attack();
	}
	
	//if enemy is on the same space as the player attack them
	public void attack(){
		if(map.getPlayerX() == posX && map.getPlayerY() == posY){
			map.hurtPlayer();
		}
	}
}
