package tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;



public class Bullet extends GameObject {
	int width = 24;
	int height = 24;
	int speed = 7;
	Direction direction;

	public Bullet(String img, int x, int y, GamePanel gamePanel,Direction direction) {
		super(img, x, y, gamePanel);
		this.direction = direction;
		
	}
	
	public void leftward() {
		if(!hitStaticWall(x-speed,y)){
			x-=speed;
		}
		
		
	}
	public void rightward() {
		if(!hitStaticWall(x+speed,y)){
			x+=speed;
		}
		x+=speed;
	}
	
	public void upward() {
		if(!hitStaticWall(x,y-speed)){
			y-=speed;
		}
				
	}
	public void downward() {
		if(!hitStaticWall(x,y+speed)){
			y+=speed;
		}
			
	}
	 
	public void go() {
		switch (direction) {
		case LEFT: 
			leftward();
			break;
		case RIGHT: 
			rightward();
			break;
		case UP: 
			upward();
			break;
		case DOWN:
			downward();
			break;
	
		}
		//put here so enemyBullet can use it too
		this.hitWall();
		this.moveToBorder();
		this.hitBase();
		
	}
	
	public void hitBot() {
		ArrayList<Bot> bots = this.gamePanel.botList;
		for(Bot bot:bots) {
			//intersects method
			if(this.getRec().intersects(bot.getRec())) {
				this.gamePanel.explosionList.add(new Explosion("", bot.x, bot.y, this.gamePanel));
				this.gamePanel.botList.remove(bot);
				this.gamePanel.removeList.add(this);
				break;
			}
		}
		
	}
	
	public void hitBase() {
		ArrayList<Base> bases = this.gamePanel.baseList;
		for(Base base:bases) {
			//intersects method
			if(this.getRec().intersects(base.getRec())) {
				this.gamePanel.baseList.remove(base);
				this.gamePanel.removeList.add(this);
				//game failed 
				//this.gamePanel.failed =true;
				break;
			}
		}
		
	}
	
	public void hitWall() {
		//wall arraylist
		ArrayList<Wall> walls= this.gamePanel.wallList;
		//loop the wallList
		for(Wall wall:walls) {
			if(this.getRec().intersects(wall.getRec())) {
				//remove walls and bullet
				gamePanel.explosionList.add(new Explosion("", wall.x, wall.y, this.gamePanel));
				this.gamePanel.wallList.remove(wall);
				this.gamePanel.removeList.add(this);
				
				break;
				
			}
			
		}
		
	}
	public boolean hitStaticWall(int x,int y) {
		//wallList 
		ArrayList<StaticWall> staticWalls = this.gamePanel.staticWallList;
		//tank next hit box
		Rectangle next = new Rectangle(x,y,width,height); 
		for(StaticWall staticWall : staticWalls) {
			if(next.intersects(staticWall.getRec())) {
				//gamePanel.explosionList.add(new Explosion("", staticWall.x, staticWall.y, this.gamePanel));
				this.gamePanel.removeList.add(this);
				return true;
			}
			
			
		}
		return false;
	}
	public void moveToBorder() {
		if(x<50 ||x+width>this.gamePanel.getWidth()) {
			this.gamePanel.removeList.add(this);
			
		}
		if(y<0 ||y+height>this.gamePanel.getHeight()) {
			this.gamePanel.removeList.add(this);
			
		}
		
	}
	@Override
	public void paintSelf(Graphics g) {
		g.drawImage(img, x,y,null);
		go();
		hitBot();
		
	}

	@Override
	public Rectangle getRec() {
		
		return new Rectangle(x,y,width,height);
	}

}
