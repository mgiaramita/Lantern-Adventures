
public class Player {
	private int posX;
	private int posY;
	private int health;
	
	public Player(int posX, int posY){
		this.posX = posX;
		this.posY = posY;
		health = 100;
	}
	
	//Standard get and set methods for player attributes
	public int getX(){
		return posX;
	}
	public int getY(){
		return posY;
	}
	
	public void setPosX(int x){
		posX = x;
	}
	public void setPosy(int y){
		posY = y;
	}
	
	public int getHealth(){
		return health;
	}
	
	public void setHealth(int change){
		//To decrease health call method with a negative integer, increase with positive
		health += change;
	}
}
