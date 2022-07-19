package com.company.mario;

import java.util.LinkedList;
import java.awt.Graphics;

import com.company.mario.entity.Entity;
import com.company.mario.tile.Tile;
import com.company.mario.tile.Wall;

public class Handler { //Diese Klasse wird benutzt um alle Objekte des Spiels durchzuführen, diese Objekte werden in einer Liste gespeichert. (Effizienter) statt eben jedes Objekte einzeln 

	public LinkedList<Entity> entity = new LinkedList<Entity>(); //<Datentyp> --> was in der Liste gespeichert werden soll
	public LinkedList<Tile> tile = new LinkedList<Tile>();

public Handler() {
	createLevel();
}
	
public void render(Graphics g)
{
	for(Entity en : entity)
	{
		en.render(g);
	}
	
	for(Tile ti : tile)
	{
		ti.render(g);
	}
}

public void tick()
{
	for(Entity en : entity)
	{
		en.tick();
	}
	
	for(Tile ti : tile)
	{
		ti.tick();
	}
}

public void addEntity(Entity en)
{
	entity.add(en);
}

public void removeEntity(Entity en)
{
	entity.remove(en);
}

public void addTile(Tile ti)
{
	tile.add(ti);
}

public void removeTile(Tile ti)
{
	tile.remove(ti);
}

public void createLevel() {	//diese Methode erzeugt den Untergrund (Boden).
	for (int i=0;i<Game.WIDTH*Game.SCALE/64+1;i++) {	// durch 64 because the width of the tile is 64
		addTile(new Wall(i*64,Game.HEIGHT*Game.SCALE-64,64,64,true,Id.wall,this));
		if(i!=0 && i!=1 && i!=15 && i!=16 && i!=17) 	addTile(new Wall(i*64,300,64,64,true,Id.wall,this));

	}
}

}