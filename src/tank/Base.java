package tank;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Base extends GameObject{
	
	int length =32;
	public Base(String img, int x, int y, GamePanel gamePanel) {
		super(img, x, y, gamePanel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paintSelf(Graphics g) {
		g.drawImage(img, x, y, null);
		
	}

	@Override
	public Rectangle getRec() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,length,length);
	}

}
