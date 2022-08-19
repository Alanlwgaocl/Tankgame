package tank;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;



public class Bot extends Tank{
	
	private Direction direction = Direction.UP;
	private int moveTime;
	public Bot(String img, int x, int y, GamePanel gamePanel, String upimg, String downimg, String leftimg,
			String rightimg) {
		super(img, x, y, gamePanel, upimg, downimg, leftimg, rightimg);
		// TODO Auto-generated constructor stub
	}
	public Direction getRandomDirection() {
		Random random = new Random();
		int rnum = random.nextInt(4);
		
		switch (rnum) {
		case 0:
			return Direction.LEFT;
			
		case 1:
			return Direction.RIGHT;
			
		case 2:
			return Direction.UP;
		case 3:
			return Direction.DOWN;

		default:
			return null;
		}
		
	}
	public void go() {
		attack();
		
		if(moveTime>=20) {
			direction = getRandomDirection();
			moveTime = 0;
		}else {
			moveTime ++;
		}
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
		default:
			break;
		}
		
	}
	
	public void attack() { 
		Point p = this.getHeadPoint();
		Random random = new Random();
		//frequency of bot shooting
		int rnum =random.nextInt(400);
		if(rnum<4) {
			this.gamePanel.bulletList.add(new EnemyBullet("source/bullet4.png", p.x, p.y,this.gamePanel,direction));
		}
		
		
	}
	@Override
	public void paintSelf(Graphics g) {
		g.drawImage(img, x,y,null);
		go();
		
	}

	@Override
	public Rectangle getRec() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,width,height);
	}

}
