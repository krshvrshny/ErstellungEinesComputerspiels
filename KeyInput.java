package com.company.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.company.mario.Game;
import com.company.mario.entity.Entity;

public class KeyInput implements KeyListener {

	public void keyTyped(KeyEvent e) {
		//not using --> nicht relevant
	}

	
	public void keyPressed(KeyEvent e) {	//Methode wird aufgerufen wenn eine Taste gedrückt wurde
		int key = e.getKeyCode(); //Der buchstabe der eingegeben wird wird dann ausgegeben(int key)
		for(Entity en : Game.handler.entity) {
		switch (key) {
		case KeyEvent.VK_W:  //Es wird geprüft ob die Taste die gedrückt wurde (key) dem KeyEvent entspricht; hier: W
			if(!en.jumping) {
				en.jumping = true;
				en.gravity = 10.0;
			}
			break;	
		case KeyEvent.VK_A:  
			en.setVelX(-5);
			en.facing = 0;
			break;
		case KeyEvent.VK_D:  
			en.setVelX(5);
			en.facing = 1;
			break;
		}
	}
	}

	
	public void keyReleased(KeyEvent e) { //Methode wird aufgerufen wenn eine Taste vom Finger gelöst wurde
		int key = e.getKeyCode();
		for(Entity en : Game.handler.entity) {
			switch (key) {
			case KeyEvent.VK_W:  //Es wird geprüft ob die Taste die gedrückt wurde (key) dem KeyEvent entspricht; hier: W
				en.setVelY(0);
				break;	
			case KeyEvent.VK_S: 
				en.setVelY(0);
				break;
			case KeyEvent.VK_A:  
				en.setVelX(0);
				break;
			case KeyEvent.VK_D:  
				en.setVelX(0);
				break;  //Die Null in den Klammern bedeutet, dass wenn die Taste losgelassen wird, bleibt der Spieler stehen.
			}
		}
	}

}
