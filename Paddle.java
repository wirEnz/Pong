import java.awt.Color;

public class Paddle extends Sprite {
	
	private final static int paddleWidth = 25;
	private final static int paddleHeight = 100;
	private final static Color paddleColour = Color.white;
	
	public Paddle(int panelWidth, int panelHeight, Player e) {
		setWidth(paddleWidth);
		setheight(paddleHeight);
		setColour(paddleColour);
		
		setInitialPosition(panelWidth / 3 - (getWidth() / 3), panelHeight / 3 - (getHeight() / 3));
		resetInitialPostion();
				
		
	}

}

