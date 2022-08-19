package tank;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Explosion extends GameObject{
	 
	static Image[] imgs = new Image[4];
	int explosionCount =0;
	
	static {
		for(int i =0;i<4;i++) {
			imgs[i]= Toolkit.getDefaultToolkit().getImage("source/explosion" + (i+1) + ".png" );
		}
	}
	
	public Explosion(String img, int x, int y, GamePanel gamePanel) {
		super(img, x, y, gamePanel);
		
	}

	@Override
	public void paintSelf(Graphics g) {
		if(explosionCount<4) {
			g.drawImage(imgs[explosionCount], x,y,null);
			explosionCount++;
		}
		
	}

	@Override
	public Rectangle getRec() {
		// TODO Auto-generated method stub
		return null;
	}

}
