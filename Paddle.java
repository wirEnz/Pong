import java.awt.Color;

public class Paddle extends Sprite {
	
	private final static int paddleWidth = 10;
	private final static int paddleHeight = 100;
	private final static Color PADDLE_COLOUR = Color.white;
	private static final int DISTANCE_FROM_EDGE = 40;
	
	public Paddle(Player player, int panelWidth, int panelHeight) {
		setWidth(paddleWidth);
		setheight(paddleHeight);
		setColour(PADDLE_COLOUR);
		int xPos;
		if (player == Player.one) {
			xPos = DISTANCE_FROM_EDGE;
		} else {
            xPos = panelWidth - DISTANCE_FROM_EDGE - getWidth();
        }
        setInitialPosition(xPos, panelHeight / 2 - (getHeight() / 2));
        resetInitialPostion();
				
		
	}

}

