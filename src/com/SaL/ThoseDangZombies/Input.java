package com.SaL.ThoseDangZombies;

import java.awt.event.KeyEvent;

public class Input {
	 public static final int UP = 0;
	    public static final int DOWN = 1;
	    public static final int LEFT = 2;
	    public static final int RIGHT = 3;
	    public static final int ATTACK = 7;

	    public static final int JUMP = 4;
	    public static final int ACTION = 5;

	    public static final int ESCAPE = 6;

	    public boolean[] buttons = new boolean[64];
	    public boolean[] oldButtons = new boolean[64];

	    public void set(int key, boolean down) {
	        int button = -1;

	        if (key == KeyEvent.VK_UP ) button = UP;
	        if (key == KeyEvent.VK_LEFT) button = LEFT;
	        if (key == KeyEvent.VK_DOWN) button = DOWN;
	        if (key == KeyEvent.VK_RIGHT) button = RIGHT;

	        if (key == KeyEvent.VK_W) button = UP;
	        if (key == KeyEvent.VK_A) button = LEFT;
	        if (key == KeyEvent.VK_S) button = DOWN;
	        if (key == KeyEvent.VK_D) button = RIGHT;

	        if (key == KeyEvent.VK_SPACE) button = ATTACK;
	        if (key == KeyEvent.VK_X) button = ACTION;
	        if (key == KeyEvent.VK_C) button = JUMP;
	        if (key == KeyEvent.VK_E) button = ACTION;

	        if (key == KeyEvent.VK_ESCAPE) button = ESCAPE;

	        if (button >= 0) {
	            buttons[button] = down;
	        }
	    }

	    public void tick() {
	        for (int i = 0; i < buttons.length; i++) {
	            oldButtons[i] = buttons[i];
	        }
	    }


	    public void releaseAllKeys() {
	        for (int i = 0; i < buttons.length; i++) {
	            buttons[i] = false;
	        }
	    }
}
