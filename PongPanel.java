import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;



public class PongPanel extends JPanel implements ActionListener, KeyListener {
	
	// sets background to black, final, cannot be changed after application runs.	
	private final static Color PANEL_COLOR = Color.black;
	private final static int TIMER_DELAY = 5;
	boolean gameInitialised = false;
	
	
	// Ball object
	Ball ball;				
	// Player variable enum
	Paddle player_one, player_two;		
	
	
	GameState gameState = GameState.Initialising;
	
	public void createObjects() {
		ball = new Ball(getWidth(), getHeight());
		player_one = new Paddle(Player.one, getWidth(), getHeight());
		player_two = new Paddle(Player.two, getWidth(), getHeight());
	}
	
	
	public void paintSprit(Graphics g, Sprite sprite) {
		g.setColor(sprite.getColour());
		g.fillRect(sprite.getXPostion(), sprite.getYPostion(), sprite.getWidth(), sprite.getHeight());
		
	}
	
	

	public PongPanel() {
		
		// extends JPanel
		setBackground(PANEL_COLOR);
		Timer timer = new Timer(TIMER_DELAY, this);
		timer.start();
	}
	
	
	@Override
	public void keyPressed(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		update();
		repaint();
		
	}
	
	
	// my methods 
	
	private void update() {
		/*if(!gameInitialised) {
            createObjects();
            gameInitialised = true;
       }*/
		
		
	switch(gameState) {
       case Initialising: {
           createObjects();
           gameState = GameState.Playing;
           break;
       }
       case Playing: {
           break;
       }
       case GameOver: {
           break;
       }
   }
}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintDottedLine(g);
		if (gameState != GameState.Initialising) {
		paintSprit(g, ball);
		paintSprit(g, player_one);
		paintSprit(g, player_two);
		}
		
	}
	
	
	// method to paint dotted centerLine
	private void paintDottedLine(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
			Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] {9}, 0);
			g2d.setStroke(dashed);
			g2d.setPaint(Color.WHITE);
			g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
			g2d.dispose();
	}

	
	
}
