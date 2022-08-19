package tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class PlayerOne extends Tank{
	
	
	public PlayerOne(String img, int x, int y, GamePanel gamePanel, String upimg, String downimg, String leftimg,
			String rightimg) {
		super(img, x, y, gamePanel, upimg, downimg, leftimg, rightimg);
		
		// TODO Auto-generated constructor stub
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_A: 
			left = true;
			break;
		case KeyEvent.VK_S: 
			down = true;
			break;
		case KeyEvent.VK_D: 
			right = true;
			break;
		case KeyEvent.VK_W: 
			up = true;
			break;
		case KeyEvent.VK_SPACE:
			//another way to stop player to fire when die
			if(gamePanel.playerList.contains(gamePanel.playerOne)) {
				attack();
			}
			break;
		default:
			break;
		}
	}
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_A: 
			left = false;
			break;
		case KeyEvent.VK_S: 
			down = false;
			break;
		case KeyEvent.VK_D: 
			right = false;
			break;
		case KeyEvent.VK_W: 
			up = false;
			break;
		default:
			break;
		}
	}
	
	public void move() {
		if(left) {
			leftward();
		}
		else if(right) {
			rightward();
		}
		else if(up) {
			upward();
		}
		else if(down) {
			downward();
		}
	}
	
	@Override
	public void paintSelf(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(img, x,y,null);
		move();
		
	}

	@Override
	public Rectangle getRec() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,width,height);
	}

}
