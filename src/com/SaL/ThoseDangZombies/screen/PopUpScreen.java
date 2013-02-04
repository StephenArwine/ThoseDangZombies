package com.SaL.ThoseDangZombies.screen;

import java.awt.Graphics;

import com.SaL.ThoseDangZombies.Draw;
import com.SaL.ThoseDangZombies.Input;


public class PopUpScreen extends Screen {
    private Screen parent;
	
    private String[][] popups = {
    		{
    			
    		},{
    			"FLYING",
                "LEAVE A ROOM THROUGH ANY",
    		},
    };
    
    private int delay = 15;
    private int id;
    public PopUpScreen(Screen parent, int id) {
        this.parent = parent;
        this.id = id;
    }
	 public void render(Graphics g) {
	        parent.render(g);
	        int xs = 0;
	        int ys = popups[id].length+3;
	        for (int y=0; y<popups[id].length; y++) {
	            int s = popups[id][y].length();
	            if (s>xs) xs = s;
	        }
	        int xp = 160-xs*3;
	        int yp = 120-ys*3;
	        for (int x=0-1; x<xs+1; x++) {
	            for (int y=0-1; y<ys+1; y++) {
	                int xf = 1;
	                int yf = 3;
	                if (x<0) xf--;
	                if (y<0) yf--;
	                if (x>=xs) xf++;
	                if (y>=ys) yf++;
	                g.drawImage(Draw.string[xf][yf], xp+x*6, yp+y*6, null);
	            }
	        }
	        for (int y=0; y<popups[id].length; y++) {
	            WordsYo(popups[id][y], g, xp, yp+y*6);
	        }
	        if (delay==0)
	        WordsYo("PRESS X", g, xp+(xs-8)*6, yp+(popups[id].length+2)*6);
	    }
		public void update(Input input) {
    if (!input.oldButtons[Input.ESCAPE] && input.buttons[Input.ESCAPE]) {
        setScreen(parent);
        return;
    }
    if (delay>0) delay--;
    if (delay==0 && input.buttons[Input.SHOOT] && !input.oldButtons[Input.SHOOT]) {
        setScreen(parent);
    }
}
}
