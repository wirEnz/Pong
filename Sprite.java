import java.awt.Color;
import java.awt.Rectangle;

public class Sprite {
	
	
	
	private int xPosition, yPosition;
	private int xVelocity, yVelocity;
	private int width, height;
	private int initialXPosition, initialYPosition;
	private Color colour = Color.WHITE;
	
	// getter methods
	public int getXPostion() {return xPosition;}
	public int getYPostion() {return yPosition;}
	
	public int getXVelocity() {return xVelocity;}
	public int getYVelocity() {return yVelocity;}
	
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	
	public Color getColour() {return colour;}
	public Rectangle getRectangle() {return new Rectangle(getXPostion(), getYPostion(), getWidth(), getHeight());}
	
	// setter methods
	 public void setXPosition(int newX) {
         xPosition = newX;
     }
     public void setYPosition(int newY) {
         yPosition = newY;
     }
     
     public void setXVelocity(int newXVelocity) {
         xVelocity = newXVelocity;
     }
	
		
	
	public void setXvelocity(int newXVelocity) {
		xVelocity = newXVelocity;
	}
	
	public void setYvelocity(int newYVelocity) {
		yVelocity = newYVelocity;
	}
	
	public void setWidth(int newWidth) {
		width = newWidth;
	}
	
	public void setheight(int newHeight) {
		height = newHeight;
	}
	
	public void setColour(Color newColour) {
		colour = newColour;
	}
	
	public void setInitialPosition(int initialX, int initialY) {
		initialXPosition = initialX;
		initialYPosition = initialY;
	}
	
	public void resetInitialPostion() {
		setXPosition(initialXPosition);
		setYPosition(initialYPosition);
	}
	
	// overloading methods
	public void setXPostion(int newX, int panelWidth) {
		xPosition = newX;
		
		if (xPosition < 0) {
			xPosition = 0;
		}else if (xPosition + width > panelWidth) {
			xPosition = panelWidth - width;
		}
	}
	
	public void setyPostion(int newY, int panelHeight) {
		yPosition = newY;
		if (yPosition < 0) {
			yPosition = 0;
		}else if (yPosition + height > panelHeight) {
			xPosition = panelHeight - height;
		}
	}
	
	
	
	

}
