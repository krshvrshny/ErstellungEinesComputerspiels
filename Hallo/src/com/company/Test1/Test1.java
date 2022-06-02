package com.company.Test1;
/**
 * 
 * @author ianie
 * @version 1.0
 */

public class Test1 {
	
	private String name;
	private int laenge;
	
/**
 * 
 * @param args Argumente
 */
	public static void main(String[] args)
	{
		Test1 Figur1 = new Test1();
		Figur1.laenge = 2;
		System.out.println(Figur1.getLaenge());
	}
	
/**
 * 
 * @param name1 Der Name ist gleich Name1
 */
	public void setName (String name1)
	{
		name = name1;
	}
/**
 * 	
 * @return Die Länge wird zurückgegeben
 */
	public int getLaenge()
	{
		return laenge;
	}
}

