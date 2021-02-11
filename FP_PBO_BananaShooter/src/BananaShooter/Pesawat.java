package BananaShooter;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Pesawat extends Controller {

	private int dx;
    private int dy;
    private List<Peluru> pelurus;

    Sound sound = new Sound();
    String filepath2 = "sound/dor.wav";
    String filepath = "sound/fc.wav";
    
    public Pesawat(int x, int y) {
        super(x, y);

        initCraft();
    }

    private void initCraft() {
        
        pelurus = new ArrayList<>();
        loadImage("img/pesawat.png");
        getImageDimensions();
    }

    public void move() {

        x += dx;
        y += dy;

        if (x < 1) {
            x = 1;
        }

        if (y < 1) {
            y = 1;
        }
    }

    public List<Peluru> getPelurus() {
        return pelurus;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fire();
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -2;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 2;
        }
    }

    public void fire() {
        pelurus.add(new Peluru(x + width, y + height / 25));
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
        
        if(key == KeyEvent.VK_SPACE) {
        	sound.play(filepath2);
        }
        
        if(key == KeyEvent.VK_F1) {
        	sound.play(filepath);
        	
        }
        
    }
	
}
