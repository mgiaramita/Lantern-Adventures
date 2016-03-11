
public class Player {
	private int posX;
	private int posY;
	private int health;
	
	public Player(int posX, int posY){
		this.posX = posX;
		this.posY = posY;
		health = 75;
	}
	
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
}
