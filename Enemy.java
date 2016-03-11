import com.googlecode.lanterna.screen.Screen;


public class Enemy extends Thread{
	private int posX;
	private int posY;
	private Map map;
	private Screen screen;
	
	public Enemy(int x, int y, Map map, Screen sc){
		posX = x;
		posY = y;
		this.map = map;
		screen = sc;
	}
	
	public void run(){
		map.PrintMap(screen);
		while(true){
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
	
	//The move methods check if the move is valid and they update the map and enemy location data
	public void moveUp(){
		if(posY > 0 && !map.getStringAt(posX, posY - 1).equals("#")){
			map.setString(" ", posX, posY);
			posY -= 1;
			map.setString("E", posX, posY);
		}
	}
	
	public void moveDown(){
		if(posY + 1 < map.getMaxY() && !map.getStringAt(posX, posY + 1).equals("#")){
			map.setString(" ", posX, posY);
			posY += 1;
			map.setString("E", posX, posY);
		}
	}
	
	public void moveLeft(){
		if(posX > 0 && !map.getStringAt(posX - 1, posY).equals("#")){
			map.setString(" ", posX, posY);
			posX -= 1;
			map.setString("E", posX, posY);
		}
	}
	
	public void moveRight(){
		if(posX + 1 < map.getMaxX() && !map.getStringAt(posX + 1, posY).equals("#")){
			map.setString(" ", posX, posY);
			posX += 1;
			map.setString("E", posX, posY);
		}
	}
}
