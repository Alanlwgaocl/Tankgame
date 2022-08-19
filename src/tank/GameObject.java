package tank;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public abstract class GameObject {
	//picture
	public Image img;
	
	//location
	public int x,y;
	//panel
	public GamePanel gamePanel;
	
	
	
	//constructor
	public GameObject(String img, int x, int y,GamePanel gamePanel) {
		this.img= Toolkit.getDefaultToolkit().getImage(img);
		this.x = x;
		this.y = y;
		this.gamePanel = gamePanel;
	}
	
	public abstract void paintSelf(Graphics g);
	public abstract Rectangle getRec();
}
