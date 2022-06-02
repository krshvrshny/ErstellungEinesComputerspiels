package pack1;
	
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
//Runnable = interface
		
		public static final int WIDTH = 270;
		public static final int HEIGHT = 270/14*10;
		public static final int SCALE = 4;
		public static final String TITLE = "Mario";
		
		private Thread thread;
		private boolean running = false;
		
		private synchronized void start() //Synchronized = protects the thread from other thread interferences
		{
			if(running) return;
			running = true; 
			thread = new Thread(this, "Thread");
			thread.start();
		}
		
		private synchronized void stop()
		{
			if(!running)return;
			running = false; 
			try {
				thread.join(); //risky code

			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		public void run()
		{
			while(running) {
				render();
				tick();
			}
		}
		
		public void render() {
			
		}
		
		public void tick() {
			// tick = update
		}
				
		public Game()
		{
			Dimension size = new Dimension(WIDTH*SCALE,HEIGHT*SCALE);
			setPreferredSize(size);
			setMaximumSize(size);
			setMinimumSize(size);
		}
		
		public static void main (String []args)
		{
			Game game = new Game(); // Objekt game wird von der Klasse game erstellt
			JFrame frame = new JFrame(TITLE);
			frame.add(game);
			// die sachen die im Konstruktor stehen (siehe oben), werden dem Frame hinzugefügt Objekt game repräsentiert den Konstruktor
			frame.pack();
			frame.setResizable(false); // Beim Frame kann die Größe nicht geändert werden
			frame.setLocationRelativeTo(null); // Der Frame wird in der Mitte vom Fenster eingefügt
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			game.start();
		}

	
}
