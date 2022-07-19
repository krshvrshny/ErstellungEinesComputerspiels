package com.company.mario.entity;

import java.awt.Graphics;
import java.awt.Color;

import com.company.mario.Game;
import com.company.mario.Handler;
import com.company.mario.Id;
import com.company.mario.tile.Tile;

public class Player extends Entity {

	private int frame = 0;
	private int frameDelay = 0;
	
	private boolean animate = false;
	
	public Player(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
		//setVelX(5); //Zahl gibt die Geschwindigkeit an, bei dem sich das Objekt bewegt
	}
	

	public void render(Graphics g) {
		if(facing==0) {
		g.drawImage(Game.player[frame+5].getBufferedImage(), x, y, width, height, null);
		} else if (facing==1) {
			g.drawImage(Game.player[frame].getBufferedImage(), x, y, width, height, null);

		}
		}

	public void tick() {
		x+=velX;
		y+=velY;
		if(y+height>=771) y= 771-height;// 771 (siehe Klasse Game)
		//die If-Clauses drücken aus, dass der Spieler sich nur im Frame bewegen kann bzw. maximal die Kanten berühren kann.
		if(velX!=0) animate = true;
		else animate = false;
		for(Tile t : handler.tile) {
			if(!t.solid) break; // wenn t NICHT solid dann hört die Schleife auf (break).
				if(t.getId()==Id.wall) {
					if(getBoundsTop().intersects(t.getBounds())) {	//intersects == collides
						setVelY(0);
						if(jumping) {
							jumping = false;
							gravity = 0.0;
							falling = true;
						}
						
					}
					if(getBoundsBottom().intersects(t.getBounds())) {	
						setVelY(0);
						if(falling) falling = false;
					}	else {
							if(!falling && !jumping) {
								gravity = 0.8;
								falling = true;
							}
						}
				
					
					if(getBoundsLeft().intersects(t.getBounds())) {	
						setVelX(0);
						x = t.getX()+t.width;
			}
					
					if(getBoundsRight().intersects(t.getBounds())) {	
						setVelX(0);
						x = t.getX()-t.width;
			}
		}
	}
		
		if(jumping) {
			
			gravity-=0.1;
			setVelY((int)-gravity); //setVelY = int gravity = double; somit setzt man vor gravity ein int
			if(gravity<=0.0) {
				jumping = false;
				falling = true;
			}
		}
		if(falling) {
			gravity+=0.1;
			setVelY((int)gravity);
		}
		
		if(animate) {
			frameDelay++;
			if(frameDelay>=3) {
				frame++;
				if(frame>=5) {
					frame = 0;
				}
				frameDelay = 0;
			}
		
		
	
}
	
}
}

	

