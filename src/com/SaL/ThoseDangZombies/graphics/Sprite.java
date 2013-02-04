package com.SaL.ThoseDangZombies.graphics;


public class Sprite {
	
	public final int SIZEY,SIZEX;
	private int x, y;
	public int[] pixels;

	public static Sprite VoidSprite = new Sprite(16, 0x000000);
	
	
	public Sprite(int size, int color) {

		SIZEY = size;
		SIZEX = size;
		
		pixels = new int[SIZEY * SIZEY];
		setColor(color);

	}

	private void setColor(int color) {

		for (int i = 0; i < SIZEY * SIZEY; i++) {
			pixels[i] = color;

		}
	}

}
