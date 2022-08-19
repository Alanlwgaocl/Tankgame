package tank;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall extends GameObject{
	int length = 32;

	public Wall(String img, int x, int y, GamePanel gamePanel) {
		super(img, x, y, gamePanel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paintSelf(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(img, x, y, gamePanel);
		
	}

	@Override
	public Rectangle getRec() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,length,length);
	}
	

}
