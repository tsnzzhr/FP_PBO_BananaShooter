package BananaShooter;

public class Peluru extends Controller {

	private final int DISPLAY_WIDTH = 390;
    private final int PELURU_SPEED = 2;

    public Peluru(int x, int y) {
        super(x, y);

        initPeluru();
    }
    
    private void initPeluru() {
        
        loadImage("img/peluru.png");
        getImageDimensions();        
    }

    public void move() {
        
        x += PELURU_SPEED;
        
        if (x > DISPLAY_WIDTH)
            visible = false;
    }
	
}
