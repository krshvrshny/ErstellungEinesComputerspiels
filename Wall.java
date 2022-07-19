package com.company.mario.tile;

import java.awt.Graphics;
import java.awt.Color;

import com.company.mario.Game;
import com.company.mario.Handler;
import com.company.mario.Id;

public class Wall extends Tile {

	

	public Wall(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
	}


	public void render(Graphics g) {
		
		g.drawImage(Game.grass.getBufferedImage(), x, y, width, height, null);

	}

	
	public void tick() {
		
	}

}
