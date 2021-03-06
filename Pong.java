import javax.swing.JFrame;

public class Pong extends JFrame {
	
	// private final static, cannot be changed once application starts
	// this sets the title, width and height of the application window
	private final static String WINDOW_TITLE = "Pong";
	private final static int WINDOW_WIDTH = 800;
	private final static int WINDOW_HEIGHT = 600;

	public Pong() {
			
		// meaning in the name		
		setTitle(WINDOW_TITLE);
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// adds panel to the frame
		add(new PongPanel());
				
	}
	
	
	public static void main(String[] args) {
		
		new Pong();
		

	}

}
