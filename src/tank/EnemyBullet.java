package tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import tank.Tank.AttackCD;

public class EnemyBullet extends Bullet{
	

	public EnemyBullet(String img, int x, int y, GamePanel gamePanel, Direction direction) {
		super(img, x, y, gamePanel, direction);
		// TODO Auto-generated constructor stub
	}
	public void hitPlayer() {
		ArrayList<Tank> players = this.gamePanel.playerList;
		for(Tank player:players) {
			//intersects method
			if(this.getRec().intersects(player.getRec())) {
				this.gamePanel.explosionList.add(new Explosion("", player.x, player.y, this.gamePanel));
				this.gamePanel.playerList.remove(player);
				this.gamePanel.removeList.add(this);
				//set player not alive
				player.alive =false;
				break;
			}
		}
		
	}
	
	public void paintSelf(Graphics g) {
		g.drawImage(img, x,y,null);
		this.go();
		this.hitPlayer();
	}

	
	public Rectangle getRec() {
		
		return new Rectangle(x,y,width,height);
	}

}
