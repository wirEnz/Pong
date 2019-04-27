import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Font;
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
	private final static int BALL_MOVEMENT_SPEED = 2;
	private final static int POINTS_TO_WIN = 3;
	int player1Score = 0, player2Score = 0;
	private final static int SCORE_TEXT_X = 100;
	private final static int SCORE_TEXT_Y = 100;
	private final static int WINNER_TEXT_X = 200;
	private final static int WIINER_TEXT_Y = 200;
	private final static int SCORE_FONT_SIZE = 50;
	private final static String SCORE_FONT_FAMILY = "Arial";
	private final static String winner = "WINNER!";
	Player gameWinner;
	
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
		g.fillRect(sprite.getXPosition(), sprite.getYPosition(), sprite.getWidth(), sprite.getHeight());
		
	}
	
	

	public PongPanel() {
		
		// extends JPanel
		setBackground(PANEL_COLOR);
		Timer timer = new Timer(TIMER_DELAY, this);
		timer.start();
		addKeyListener(this);
		setFocusable(true);
	}
	
	
	@Override
	public void keyPressed(KeyEvent event) {
		// moves player_two paddle up and down		
		if (event.getKeyCode() == KeyEvent.VK_W) {
			player_one.setYVelocity(-1);
		} else if (event.getKeyCode() == KeyEvent.VK_S) {
			player_one.setYVelocity(1);
		}
		// moves player_one paddle up and down
		if (event.getKeyCode() == KeyEvent.VK_UP) {
			player_two.setYVelocity(-1);
		} else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
			player_two.setYVelocity(1);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent event) {
		// stops player_two paddle once key is released 
		if (event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_DOWN ) {
			player_two.setYVelocity(0);
		}
		// stops player_one paddle once key is released 
		if (event.getKeyCode() == KeyEvent.VK_W || event.getKeyCode() == KeyEvent.VK_S ) {
			player_one.setYVelocity(0);
		}
		
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
		switch(gameState) {
		      case Initialising: {
		          createObjects();
		          gameState = GameState.Playing;
		          ball.setXVelocity(BALL_MOVEMENT_SPEED);
		          ball.setYVelocity(BALL_MOVEMENT_SPEED);
		          break;
		      }
		      case Playing: {
		    	 // move player paddles
		    	 moveObject(player_one);
		    	 moveObject(player_two);
		    	 // move ball 
		    	 moveObject(ball);
		    	 // check for wall bounce
		    	 checkWallBounce();
		    	 checkPaddleBounce();
		    	 checkWin();
		    	 break;
		      }
		      case GameOver: {
		          break;
		      }
		  }
}
	
	private void checkPaddleBounce() {
		// this is saying if the ball is moving, by seeing if velocity is not 0(not moving) and checks if the ball
		// Intersects with paddle, if it does  velocity is switched(ball movement)
		if (ball.getXVelocity() < 0 && ball.getRectangle().intersects(player_one.getRectangle())) {
			ball.setXVelocity(BALL_MOVEMENT_SPEED);
		}
		if (ball.getXVelocity() > 0 && ball.getRectangle().intersects(player_two.getRectangle())) {
			ball.setXVelocity(-BALL_MOVEMENT_SPEED);
		}
		
	}


	private void checkWallBounce() {
		if (ball.getXPosition() <= 0) {
			ball.setXVelocity(-ball.getXVelocity());
			// hit the left side of window
			addScore(Player.two);
			resetBall();
		}else if (ball.getXPosition() >= getWidth() - ball.getWidth()) {
			 ball.setXVelocity(-ball.getXVelocity());
			// hit right side of window
			addScore(Player.one);
			resetBall();
		}
		if (ball.getYPosition() <= 0 || ball.getYPosition() >= getHeight() - ball.getHeight()) {
			// hit top or bottom of window
			ball.setYVelocity(-ball.getYVelocity());
		}
	
	}
	
	// method resets ball to center
	private void resetBall() {
		ball.resetInitialPostion();
	}


	private void moveObject(Sprite object) {
		// increases the x and y position of given object by the x and y velocity
		object.setXPosition(object.getXPosition() + object.getXVelocity(),getWidth());
		object.setYPosition(object.getYPosition() + object.getYVelocity(),getHeight());
	 }


	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintDottedLine(g);
		if (gameState != GameState.Initialising) {
		paintSprit(g, ball);
		paintSprit(g, player_one);
		paintSprit(g, player_two);
		printScores(g);
		playerWin(g);
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
	
	private void addScore(Player player) {
		// adds 1 to player if ball hits wall behind.
		if (player == Player.one) {
			player1Score++;
		}else if (player == Player.two) {
			player2Score++;
		}
	}
	
	private void checkWin() {
		// checks players score, first player to hit that score, set by POINTS_TO_WIN 
		//gameState changed to gameover and that ends game in switch update()		
		if (player1Score >= POINTS_TO_WIN) {
			gameWinner = Player.one;
			gameState = GameState.GameOver;
		}else if (player2Score >= POINTS_TO_WIN) {
			gameWinner = Player.two;
			gameState = GameState.GameOver;
		}
		
	}
	// prints scores on screen
	private void printScores(Graphics g) {
		
		Font scoreFont = new Font(SCORE_FONT_FAMILY, Font.BOLD, SCORE_FONT_SIZE);
		
		String leftScore = Integer.toString(player1Score);
		String rightScore = Integer.toString(player2Score);
		
		g.setFont(scoreFont);
		g.drawString(leftScore, SCORE_TEXT_X, SCORE_TEXT_Y);
		g.drawString(rightScore, getWidth()-SCORE_TEXT_X, SCORE_TEXT_Y);
		
	}
	
	private void playerWin(Graphics g) {
		
		Font playerWin = new Font(SCORE_FONT_FAMILY, Font.BOLD, SCORE_FONT_SIZE);
		int xPosition = getWidth() / 2;
		
		g.setFont(playerWin);
		
		if (player1Score == POINTS_TO_WIN) {
			xPosition -= WINNER_TEXT_X;
			g.drawString(winner, xPosition, WIINER_TEXT_Y);
		} else if (player2Score == POINTS_TO_WIN) {
			xPosition += WINNER_TEXT_X;
			g.drawString(winner, xPosition, WIINER_TEXT_Y);
		}
		
		
		// another way to display winner
		/**
		 * if(gameWinner != null) {
               Font winnerFont = new Font(WINNER_FONT_FAMILY, Font.BOLD, WINNER_FONT_SIZE);
              g.setFont(winnerFont);
              int xPosition = getWidth() / 2;
              if(gameWinner == Player.One) {
                  xPosition -= WINNER_TEXT_X;
              } else if(gameWinner == Player.Two) {
                  xPosition += WINNER_TEXT_X;
              }
              g.drawString(WINNER_TEXT, xPosition, WINNER_TEXT_Y);
		 * 
		 */
	}

	
	
}
