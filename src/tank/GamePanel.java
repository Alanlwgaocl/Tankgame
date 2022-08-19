package tank;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;



public class GamePanel extends JFrame implements KeyListener{
	
	//
	Image offScreenImage = null;
	
	//window
	int width = 800;
	int height = 610;
	//pointer image
	Image select= Toolkit.getDefaultToolkit().getImage("source/tank.png");
	//ImageIcon tank = new ImageIcon("source/tank.png");
	int y=170;
	//set a game mode, 1 single ,2 two player, 5 win ,4 fail, 3 pause
	int state = 0;
	int a =1;
	int count = 0;
	int enemycount =0;
	int[][] mymap= {{1,0,1},{0,1,0},{1,0,1}};
	//public boolean failed = false;
	//bullet array
	ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
	ArrayList<Bot> botList = new ArrayList<Bot>();
	ArrayList<Bullet> removeList = new ArrayList<Bullet>();
	ArrayList<Tank> playerList = new ArrayList<Tank>();
	ArrayList<Wall> wallList = new ArrayList<Wall>();
	ArrayList<StaticWall> staticWallList = new ArrayList<StaticWall>();
	ArrayList<Base> baseList = new ArrayList<Base>();
	ArrayList<Explosion> explosionList = new ArrayList<Explosion>();
	
	
	//player one
	PlayerOne playerOne = new PlayerOne("source/tank1up.png", 125, 510, this,
			"source/tank1up.png","source/tank1down.png",
			"source/tank1left.png","source/tank1right.png");
	PlayerTwo playerTwo = new PlayerTwo("source/tank1up.png", 625, 510, this,
			"source/tank1up.png","source/tank1down.png",
			"source/tank1left.png","source/tank1right.png");
	Bot bot = new Bot("source/botup.png", 200, 200, this,
			"source/botup.png","source/botdown.png",
			"source/botleft.png","source/botright.png");
	Base base = new Base("source/eagle.png", 365, 540, this);
	
	public void initgame() {
		
		botList.removeAll(botList);
		bulletList.removeAll(bulletList);
		explosionList.removeAll(explosionList);
		wallList.removeAll(wallList);
		playerList.removeAll(playerList);
		
		enemycount=0;
		count = 0;
		
		playerOne = new PlayerOne("source/tank1up.png", 125, 510, this,
				"source/tank1up.png","source/tank1down.png",
				"source/tank1left.png","source/tank1right.png");
		
		playerTwo = new PlayerTwo("source/tank1up.png", 625, 510, this,
					"source/tank1up.png","source/tank1down.png",
					"source/tank1left.png","source/tank1right.png");
		
		for(int i =0;i<14;i++) {
			wallList.add(new Wall("source/wall.png", 150+i*32, 150, this));
		}
		for(int i =0;i<800/32;i++) {
			staticWallList.add(new StaticWall("source/wall2.png", i*32, 32, this));
			staticWallList.add(new StaticWall("source/wall2.png", i*32, 610-40, this));
		}
		for(int i =0;i<610/32;i++) {
			staticWallList.add(new StaticWall("source/wall2.png", 0, 32*i, this));
			staticWallList.add(new StaticWall("source/wall2.png", 768, 32*i, this));
		}
		
		staticWallList.add(new StaticWall("source/wall2.png", 180, 232, this));
		staticWallList.add(new StaticWall("source/wall2.png", 180, 264, this));
		staticWallList.add(new StaticWall("source/wall2.png", 180, 296, this));
		staticWallList.add(new StaticWall("source/wall2.png", 180, 328, this));
		
		
		staticWallList.add(new StaticWall("source/wall2.png", 540, 232, this));
		staticWallList.add(new StaticWall("source/wall2.png", 540, 264, this));
		staticWallList.add(new StaticWall("source/wall2.png", 540, 296, this));
		staticWallList.add(new StaticWall("source/wall2.png", 540, 328, this));
		
		staticWallList.add(new StaticWall("source/wall2.png", 300, 264, this));
		staticWallList.add(new StaticWall("source/wall2.png", 332, 264, this));
		staticWallList.add(new StaticWall("source/wall2.png", 364, 264, this));
		staticWallList.add(new StaticWall("source/wall2.png", 396, 264, this));
		staticWallList.add(new StaticWall("source/wall2.png", 428, 264, this));
		
		wallList.add(new Wall("source/wall.png", 335, 540, this));
		wallList.add(new Wall("source/wall.png", 335, 510, this));
		wallList.add(new Wall("source/wall.png", 365, 510, this));
		wallList.add(new Wall("source/wall.png", 395, 510, this));
		wallList.add(new Wall("source/wall.png", 395, 540, this));
		baseList.add(base);
		
		for(int i =0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(mymap[i][j] ==1) {
					wallList.add(new Wall("source/wall.png", 330+j*32, 330+i*32, this));
				}
			}
			
		}
		
		
		
	}
	//launch game
	public void launch() {
		
		setTitle("tank war");
		setSize(width, height);
		//center the window
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		//this.addKeyListener(new GamePanel.KeyMonitor());
		this.addKeyListener(this);
		for(int i =0;i<14;i++) {
			wallList.add(new Wall("source/wall.png", 150+i*32, 150, this));
		}
		for(int i =0;i<800/32;i++) {
			staticWallList.add(new StaticWall("source/wall2.png", i*32, 32, this));
			staticWallList.add(new StaticWall("source/wall2.png", i*32, 610-40, this));
		}
		for(int i =0;i<610/32;i++) {
			staticWallList.add(new StaticWall("source/wall2.png", 0, 32*i, this));
			staticWallList.add(new StaticWall("source/wall2.png", 768, 32*i, this));
		}
		
		staticWallList.add(new StaticWall("source/wall2.png", 180, 232, this));
		staticWallList.add(new StaticWall("source/wall2.png", 180, 264, this));
		staticWallList.add(new StaticWall("source/wall2.png", 180, 296, this));
		staticWallList.add(new StaticWall("source/wall2.png", 180, 328, this));
		
		
		staticWallList.add(new StaticWall("source/wall2.png", 540, 232, this));
		staticWallList.add(new StaticWall("source/wall2.png", 540, 264, this));
		staticWallList.add(new StaticWall("source/wall2.png", 540, 296, this));
		staticWallList.add(new StaticWall("source/wall2.png", 540, 328, this));
		
		staticWallList.add(new StaticWall("source/wall2.png", 300, 264, this));
		staticWallList.add(new StaticWall("source/wall2.png", 332, 264, this));
		staticWallList.add(new StaticWall("source/wall2.png", 364, 264, this));
		staticWallList.add(new StaticWall("source/wall2.png", 396, 264, this));
		staticWallList.add(new StaticWall("source/wall2.png", 428, 264, this));
		
		wallList.add(new Wall("source/wall.png", 335, 540, this));
		wallList.add(new Wall("source/wall.png", 335, 510, this));
		wallList.add(new Wall("source/wall.png", 365, 510, this));
		wallList.add(new Wall("source/wall.png", 395, 510, this));
		wallList.add(new Wall("source/wall.png", 395, 540, this));
		baseList.add(base);
		//use 2d array to draw map
		for(int i =0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(mymap[i][j] ==1) {
					wallList.add(new Wall("source/wall.png", 330+j*32, 330+i*32, this));
				}
			}
		}
		
		while(true) {
			if(botList.size()==0 && enemycount ==10) {
				state = 5;
			}
			if(playerList.size()== 0 && (state ==1 ||state ==2)|| baseList.size()==0){
				state =4;
			}
			
			//add bot tank 
			if(count%40 ==1 &&enemycount<10 &&( state==1||state==2)) {
				Random random = new Random();
				int rxcood = random.nextInt(600)+50;
				botList.add(bot = new Bot("source/botup.png", rxcood, 200, this,
				"source/botup.png","source/botdown.png",
				"source/botleft.png","source/botright.png"));
				enemycount++;
			}
			repaint();
			
			try{
				Thread.sleep(25);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
	}
	

	//paint method
	@Override
	public void paint(Graphics g) {
		//create image
		if(offScreenImage ==null) {
			offScreenImage = this.createImage(width,height);
		}
		//get image
		Graphics gImage = offScreenImage.getGraphics();		
		
		gImage.setColor(Color.gray);
		gImage.fillRect(0, 0,width, height);
		
		gImage.setColor(Color.blue);
		gImage.setFont(new Font("sans-serif",Font.BOLD,50));
		if(state ==0) {
			gImage.drawString("Choose game mode ",220, 100);
			gImage.drawString("Single player mode ",220, 200);
			gImage.drawString("Multi  player mode ",220, 300);
			gImage.setColor(Color.white);
			gImage.setFont(new Font("sans-serif",Font.BOLD,25));
			gImage.drawString("Press 1 or 2 to choose mode,press enter to confirm ",180, 350);
			gImage.setColor(Color.blue);
			gImage.setFont(new Font("sans-serif",Font.BOLD,50));
			gImage.drawImage(select,160,y,null);
			//tank.paintIcon(this, g, 160, y);
		}else if(state == 1 || state ==2) {
			
			gImage.setFont(new Font("sans-serif",Font.BOLD,15));
			gImage.drawString("Enemy alive: "+botList.size(), 50, 80);
			
			if(state == 1) {
				gImage.drawString("Single player mode ",220, 200);
				//gImage.drawImage(select,160,170,null);
			}else if(state ==2){
				gImage.drawString("Multi  player mode ",220, 200);
				//gImage.drawImage(select,160,170,null);
			}
			for(Tank player:playerList) {
				player.paintSelf(gImage);
			}
			//playerOne.paintSelf(gImage);
			
			
			for(Bullet bullet : bulletList) {
				bullet.paintSelf(gImage);
				
			}
			
			for(Bot bot :botList) {
				bot.paintSelf(gImage);
			}
			for(Wall wall:wallList) {
				wall.paintSelf(gImage);
			}
			for(StaticWall staticWall:staticWallList) {
				staticWall.paintSelf(gImage);
			}
			for(Base base:baseList) {
				base.paintSelf(gImage);
			}
			for(Explosion explosion:explosionList) {
				explosion.paintSelf(gImage);
			}
			bulletList.removeAll(removeList);
			count++;
			
		}else if(state == 5) {
			gImage.setColor(Color.red);
			gImage.setFont(new Font("sans-serif",Font.BOLD,30));
			gImage.drawString(" you won,Press R back to menu", 300, 300);
		}
		else if(state == 4) {
			gImage.setColor(Color.yellow);
			gImage.setFont(new Font("sans-serif",Font.BOLD,30));
			gImage.drawString(" you lost ,Press R back to menu", 100, 300);
		}
		else if(state == 3) {
			gImage.setColor(Color.blue);
			gImage.setFont(new Font("sans-serif",Font.BOLD,30));
			gImage.drawString(" Game paused, press P to play", 100, 300);
		}
		//store gImage in the container
		g.drawImage(offScreenImage, 0, 0, null);
		
		
		
	}
//	public class  KeyMonitor extends KeyAdapter{
//		@Override
//		public void keyPressed(KeyEvent e) {
//			System.out.println(e.getKeyChar());
//		}
//	}
//	 
	
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_1: 
			a= 1;
			y= 170;
			break;
		case KeyEvent.VK_2: 
			a=2;
			y=270;
			break;
		case KeyEvent.VK_ENTER: 
			if(state ==0) {
				state = a;
				playerList.add(playerOne);
				//set player alive
				playerOne.alive =true;
				if(state==2) {
					playerList.add(playerTwo);
					playerTwo.alive=true;
				}
			}
			
			break;
		case KeyEvent.VK_P: 
			if(state !=3) {
				a = state;
				state =3;
			}else {
				state =a;
				if(a==0) {
					a=1;
				}
			}
			//restart the game
		case KeyEvent.VK_R: 
			if(state ==4 ||state==5) {
				state=0;
				initgame();
			}
			
		default:
			playerOne.keyPressed(e);
			playerTwo.keyPressed(e);
		
		}
		//output key pressed
		System.out.println(e.getKeyChar());
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		playerOne.keyReleased(e);
		playerTwo.keyReleased(e);
		
	}
	//main 
		public static void main(String []args){
			
			GamePanel gp = new GamePanel();
			gp.launch();
			
			
		}
}
