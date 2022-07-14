package com.company.mario;
	
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.Color;

import javax.swing.JFrame;

import com.company.input.KeyInput;
import com.company.mario.entity.Player;
import com.company.mario.tile.Wall;

public class Game extends Canvas implements Runnable {
//Runnable = interface
//Canvas = Eine Canvas-Komponente stellt einen leeren rechteckigen Bereich des Bildschirms dar, auf den die Anwendung zeichnen kann oder von dem aus die Anwendung Eingabeereignisse des Benutzers abfangen kann.
//Eine Anwendung muss die Canvas-Klasse unterklassifizieren, um nützliche Funktionen wie die Erstellung einer benutzerdefinierten Komponente zu erhalten.		
		public static final int WIDTH = 270;
		public static final int HEIGHT = 270/14*10;
		public static final int SCALE = 4;
		public static final String TITLE = "Mario";
		
		private Thread thread;	//A thread in Java is the direction or path that is taken while a program is being executed.
		//Generally, all the programs have at least one thread, known as the main thread, that is provided by the JVM or Java Virtual Machine at the starting of the program’s execution.
		private boolean running = false;
		public static Handler handler; //Objekt aus der Klasse Handler wird mit dem Namen handler erstellt
		
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
			init();
			requestFocus(); //Frame wird im Fokus gestellt beim Runnen
			long lastTime = System.nanoTime();
			/* long = Datentyp wie Integer; long kann größere Zahlen speichern.
			 * long lastTime = System.nanoTime(); --> Zeit wird in Nanosekunden umgewandelt.
			 */
			long timer = System.currentTimeMillis();
			// aktuelle (current) Zeit wird in Millisekunden umgewandelt.
			double delta = 0.0;
			/*
			 * delta = Datentyp wie Integer; delta kann Dezimalzahlen speichern.
			 */
			double ns = 1000000000.0/60.0; // 1s = 1000000000ns
			int frames = 0;
			int ticks = 0;
			while(running) {
				long now = System.nanoTime();
				delta+=(now-lastTime)/ns;
				lastTime = now;
				while(delta>=1)
				{
					tick();
					ticks++;
					delta--;
				}
				render();
				frames++;
				if(System.currentTimeMillis()-timer>1000)
				{
					timer+=1000;
					System.out.println(frames + " Frames Per Second " + ticks + "Update Per Second ");
					frames = 0;
					ticks = 0;
				}
			}
		}
		
		public void render() {
			BufferStrategy bs = getBufferStrategy();
			if(bs==null)
			{
				createBufferStrategy(3); // Hier werden 3 BuffetStrategies erstellt
				return;
			}
			Graphics g = bs.getDrawGraphics();
			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight()); //Graphik (Zeile 85-87) wurde erstellt
			handler.render(g);
			g.dispose(); //Graphik wird angezeigt
			bs.show(); //BuffetStrategies werden im Frame angezeigt
		}
		
		public void tick() {
			// tick = update
			handler.tick();
		}
				
		public Game()
		{
			Dimension size = new Dimension(WIDTH*SCALE,HEIGHT*SCALE);
			setPreferredSize(size);
			setMaximumSize(size);
			setMinimumSize(size);
		}
		
		private void init()
		{
			handler = new Handler();
			
			addKeyListener(new KeyInput());
			
			handler.addEntity(new Player(200, 300, 64, 64, true, Id.player, handler));
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
