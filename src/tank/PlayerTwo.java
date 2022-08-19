package tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class PlayerTwo extends Tank{


	public PlayerTwo(String img, int x, int y, GamePanel gamePanel, String upimg, String downimg, String leftimg,
			String rightimg) {
		super(img, x, y, gamePanel, upimg, downimg, leftimg, rightimg);
		// TODO Auto-generated constructor stub
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_LEFT: 
			left = true;
			break;
		case KeyEvent.VK_DOWN: 
			down = true;
			break;
		case KeyEvent.VK_RIGHT: 
			right = true;
			break;
		case KeyEvent.VK_UP: 
			up = true;
			break;
		case KeyEvent.VK_K: 
			if(gamePanel.playerList.contains(gamePanel.playerTwo)) {
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
		case KeyEvent.VK_LEFT: 
			left = false;
			break;
		case KeyEvent.VK_DOWN: 
			down = false;
			break;
		case KeyEvent.VK_RIGHT: 
			right = false;
			break;
		case KeyEvent.VK_UP: 
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