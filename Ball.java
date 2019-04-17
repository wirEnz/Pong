import java.awt.Color;

public class Ball extends Sprite {
	
	private final static int ballWidth = 25;
	private final static int ballHeight = 25;
	private final static Color ballColour = Color.WHITE;

	public Ball(int panelWidth, int panelHeight) {
		setWidth(ballWidth);
		setheight(ballHeight);
		setColour(ballColour);
		
		setInitialPosition(panelWidth / 2 - (getWidth() / 2), panelHeight / 2 - (getHeight() / 2));
		resetInitialPostion();
		
	}
	

}
