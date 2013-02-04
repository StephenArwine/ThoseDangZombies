package com.SaL.ThoseDangZombies.screen;

import java.awt.Graphics;

import com.SaL.ThoseDangZombies.Draw;
import com.SaL.ThoseDangZombies.Input;


public class TitleScreen extends Screen {
    private int time = 0;
    private String msg = "PRESS X TO START";

	

    public void render(Graphics g) {
        g.drawImage(Draw.bg, 0, 0, null);
        if (time>100){
        WordsYo(msg, g, 160 - msg.length() * 3, 140 - 3 - (int) (Math.abs(Math.sin(time * 0.05) * 10)));
        }
    }
    
	public void update(Input input) {
		time++;
        if (time > 100) {
            if (input.buttons[Input.SHOOT] && !input.oldButtons[Input.SHOOT]) {
            	msg = "Thanks!";
                setScreen(new GameScreen());
                input.releaseAllKeys();
            }
	}
	}}
