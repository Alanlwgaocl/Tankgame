package tank;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;

public abstract class Tank extends GameObject{
	
	//size
	public int width =32;
	public int height = 32;
	private int speed = 3;
	
	private Direction direction = Direction.UP;
	private String upimg;
	private String downimg;
	private String leftimg;
	private String rightimg;
	public boolean left;
	public boolean right;
	public boolean up;
	public boolean down;
	public boolean alive = false;
	
	
	public boolean cooldown = true;
	private int cooldownTime = 1000;

	public Tank(String img, int x, int y, GamePanel gamePanel,String upimg,String downimg,String leftimg,String rightimg) {
		super(img, x, y, gamePanel);
		this.upimg = upimg;
		this.downimg = downimg;
		this.leftimg = leftimg;
		this.rightimg = rightimg;
		this.downimg = downimg;
	}
	//tank direction methods
	public void leftward() {
		
		setImg(leftimg);
		direction = Direction.LEFT;
		if(!hitWall(x-speed, y)&&!hitStaticWall(x-speed, y)) {
			this.x-=speed;
		}
		
	}
	public void rightward() {
		
		setImg(rightimg);
		direction = Direction.RIGHT;
		if(!hitWall(x+speed, y)&&!hitStaticWall(x+speed, y)) {
			x+=speed;
		}
	}
	
	public void upward() {
		
		setImg(upimg);
		direction = Direction.UP;
		if(!hitWall(x, y-speed)&&!hitStaticWall(x,y-speed)) {
			y-=speed;
		}
	}
	public void downward() {
		
		setImg(downimg);
		direction = Direction.DOWN;
		if(!hitWall(x, y+speed)&&!hitStaticWall(x,y+speed)) {
			y+=speed;
		}
		
	}
	
	public void attack() {
		
		if(cooldown &&alive) {
			Point p = this.getHeadPoint();
			Bullet bullet = new Bullet("source/bullet5.png", p.x, p.y,this.gamePanel,direction);
			this.gamePanel.bulletList.add(bullet);
			new AttackCD().start();
		}
	
	}
		
	
	//new thread
	class AttackCD extends Thread{
		public void run() {
			cooldown = false;
			try {
				Thread.sleep(cooldownTime); 
			}catch (Exception e) {
				e.printStackTrace(); 
			}
			cooldown = true;
			this.stop();
			
		}
	}
	
	public Point getHeadPoint() {
		switch(direction) {
		case LEFT:
			return new Point(x, y+5);
		case RIGHT:
			return new Point(x+20, y+5);
		case UP:
			return new Point(x+5, y);
		case DOWN:
			return new Point(x+5, y+20);
		default:
			return null;
		}
	}
	
	private void setImg(String img) {
		this.img = Toolkit.getDefaultToolkit().getImage(img);
		
	}
	//check if tank hits wall
	public boolean hitWall(int x,int y) {
		//wallList 
		ArrayList<Wall> walls = this.gamePanel.wallList;
		//tank next hit box
		Rectangle next = new Rectangle(x,y,width,height); 
		for(Wall wall : walls) {
			if(next.intersects(wall.getRec())) {
				return true;
			}
		}
		return false;
	}
	public boolean hitStaticWall(int x,int y) {
		//wallList 
		ArrayList<StaticWall> staticWalls = this.gamePanel.staticWallList;
		//tank next hit box
		Rectangle next = new Rectangle(x,y,width,height); 
		for(StaticWall staticWall : staticWalls) {
			if(next.intersects(staticWall.getRec())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public abstract void paintSelf(Graphics g);

	@Override
	public abstract Rectangle getRec();
}
